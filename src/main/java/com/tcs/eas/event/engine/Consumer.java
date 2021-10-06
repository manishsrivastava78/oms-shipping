package com.tcs.eas.event.engine;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.eas.event.log.LoggingService;
import com.tcs.eas.event.model.Customer;
import com.tcs.eas.event.model.MailData;
import com.tcs.eas.event.model.Order;
import com.tcs.eas.event.model.Product;
import com.tcs.eas.event.model.Tracking;
import com.tcs.eas.event.utility.Utility;

/**
 * 
 * @author 44745
 *
 */
@Service
public class Consumer {

	@Value(value = "${CUSTOMER_SERVICE_HOST}")
	private String customerServiceHost;
	
	@Value(value = "${CUSTOMER_SERVICE_PORT}")
	private int customerServicePort; 
	
	@Value(value = "${PRODUCT_SERVICE_HOST}")
	private String productServiceHost; 
	
	@Value(value = "${PRODUCT_SERVICE_PORT}")
	private int productServicePort; 
	
	@Value(value = "${TRACKING_SERVICE_HOST}")
	private String trackingServiceHost; 
	
	@Value(value = "${TRACKING_SERVICE_PORT}")
	private int trackingServicePort;
	
	
	
	
	@Autowired
	private Producer producer;

	@Autowired
	LoggingService loggingService;

	private final Logger logger = LoggerFactory.getLogger(Producer.class);

	@KafkaListener(topics = "${KAFKA_SHIPPING_TOPIC}", groupId = "${KAFKA_SHIPPING_TOPIC_CLIENT_GROUP_ID}")
	public void consume(String message) throws IOException {
		
		logger.info("Inside in consumer...");
		ObjectMapper objectMapper = new ObjectMapper();
		Order order = objectMapper.readValue(message, Order.class);
		// System.out.println(
		// order.getCustomerid() + " " + order.getOrderid() + " " + order.getProductid()
		// + " " + order.getDop());
		logger.info(String.format("consumer 0#### -> Consumed message -> %s", message));
		// get customer details using customerid
		Customer customer = getCustomer(order.getCustomerid());
		logger.info("Customer object:"+customer);
		// get product details using productid
		Product product = getProduct(order.getProductid());
		logger.info("Product object:"+product);
		// out of scope
		// do shipping task and insert shipping data into shipping table
		//in scope
		// call post tracking api and store tacking number
		// String trakingId = Utility.getTrackingNumber();

		// Create message for mail service
		MailData data = new MailData();
		data.setCustomer(customer);
		data.setDop(order.getDop());
		//data.setMailTemplate(1);
		data.setOrderId(order.getOrderid());
		data.setProduct(product);
		data.setTrackingNumber(Utility.getTrackingNumber());
		
		logger.info("Tracking number....."+data.getTrackingNumber());
		//data.setDod(new Date(System.currentTimeMillis()));
		//producer.sendMessageToMailTopic(getMailDataInJson(data));
		data.setMailTemplate(2);
		producer.sendMessageToMailTopic(getMailDataInJson(data));
		createTrackingRecord(data.getTrackingNumber(), data.getOrderId(), customer.getCustomerid(), product.getProductid(), "Order is shipped");
		//data.setMailTemplate(3);
		//producer.sendMessageToMailTopic(getMailDataInJson(data));
	}

	/**
	 * 
	 * @param trackingNumber
	 * @param orderId
	 * @param customerId
	 * @param productId
	 * @param remarks
	 */
	private void createTrackingRecord(String trackingNumber,int orderId,int customerId,int productId,String remarks) {
		RestTemplate restTemplate = new RestTemplate();
		Tracking tracking = new Tracking();
		tracking.setCustomerid(customerId);
		tracking.setOrderid(orderId);
		tracking.setProductid(productId);
		tracking.setRemarks(remarks);
		tracking.setStatus(2);
		tracking.setTrackingnumber(trackingNumber);
		
		//String trackingUrl = "http://localhost:8080/apis/v1/trackings";
		String trackingUrl = "http://"+trackingServiceHost+":"+trackingServicePort+"/apis/v1/trackings";
		try {
			ResponseEntity<String> result = restTemplate.postForEntity(trackingUrl, tracking, String.class);
			logger.info("Result Status:"+result.getStatusCodeValue());
		} catch (Exception e) {
			loggingService.logError(e.getMessage());
		}
	}
	
	/*
	 * public static void main(String[] args) { Consumer c = new Consumer();
	 * c.createTrackingRecord("dasdasd", 123, 233, 33, "dsad"); }
	 */
	/**
	 *  
	 * @return
	 */
	private Customer getCustomer(int customerId) {
		Customer customer = null;
		RestTemplate restTemplate = new RestTemplate();
		String customerUrl = "http://"+customerServiceHost+":"+customerServicePort+"/apis/v1/customers/" + customerId;
		try {
			ResponseEntity<Customer> response = restTemplate.getForEntity(customerUrl, Customer.class);
			customer = response.getBody();
		} catch (Exception e) {
			loggingService.logError(e.getMessage());
		}
		return customer;

	}

	/**
	 * 
	 * @return
	 */
	private Product getProduct(int productId) {
		Product product = null;
		RestTemplate restTemplate = new RestTemplate();
		String productUrl = "http://"+productServiceHost+":"+productServicePort+"/apis/v1/products/" + productId;
		try {
			ResponseEntity<Product> response = restTemplate.getForEntity(productUrl, Product.class);
			product = response.getBody();
		} catch (Exception e) {
			loggingService.logError(e.getMessage());
		}
		return product;
	}

	/**
	 * 
	 * @param mailData
	 * @return
	 */
	private String getMailDataInJson(MailData mailData) {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		try {
			json = mapper.writeValueAsString(mailData);
			System.out.println("Manish Test ResultingJSONstring = " + json);
			// System.out.println(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
}
