package com.tcs.eas.event.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    @Value(value = "${KAFKA_SHIPPING_TOPIC}")
	private String shippingTopic;
    
    @Value(value = "${KAFKA_MAIL_TOPIC}")
	private String mailTopic;
    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 
     * @param message
     */
    public void sendMessageToShipmentTopic(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(shippingTopic, message);
    }
    
    /**
     * 
     * @param message
     */
    public void sendMessageToMailTopic(String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        this.kafkaTemplate.send(mailTopic, message);
    }
}
