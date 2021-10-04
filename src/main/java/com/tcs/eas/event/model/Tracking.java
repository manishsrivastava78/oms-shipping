package com.tcs.eas.event.model;

import java.io.Serializable;

//import javax.persistence.Entity;

/**
 * 
 * @author 44745
 *
 */
//@Entity(name = "Tracking")
public class Tracking implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4735202356226217716L;

	/**
	 * Unique tracking id to check the status of the order
	 */
	private int trackingId;

	/**
	 * Unique order id to identify the order
	 */
	private int orderId;

	/**
	 * Unique customer id to identify the customer
	 */
	private int customerId;

	/**
	 * Unique product id to identify the product
	 */
	private int productId;

	/**
	 * Status of the order 1=Order Confirmation 2=Shipment Confirmation 3=Delivery
	 * Confirmation
	 */
	private int status;

	/**
	 * @return the trackingId
	 */
	public int getTrackingId() {
		return trackingId;
	}

	/**
	 * @param trackingId the trackingId to set
	 */
	public void setTrackingId(int trackingId) {
		this.trackingId = trackingId;
	}

	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the productId
	 */
	public int getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(int productId) {
		this.productId = productId;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
}
