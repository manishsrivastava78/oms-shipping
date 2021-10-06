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
	 * Unique tracking number to check the status of the order
	 */
	private String trackingnumber;

	/**
	 * Unique order id to identify the order
	 */
	private int orderid;

	/**
	 * Unique customer id to identify the customer
	 */
	private int customerid;

	/**
	 * Unique product id to identify the product
	 */
	private int productid;

	/**
	 * Status of the order 1=Order Confirmation 2=Shipment Confirmation 3=Delivery
	 * Confirmation
	 */
	private int status;

	/**
	 * Remarks for any update
	 */
	private String remarks;

	/**
	 * @return the trackingnumber
	 */
	public String getTrackingnumber() {
		return trackingnumber;
	}

	/**
	 * @param trackingnumber the trackingnumber to set
	 */
	public void setTrackingnumber(String trackingnumber) {
		this.trackingnumber = trackingnumber;
	}

	/**
	 * @return the orderid
	 */
	public int getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid the orderid to set
	 */
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the customerid
	 */
	public int getCustomerid() {
		return customerid;
	}

	/**
	 * @param customerid the customerid to set
	 */
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}

	/**
	 * @param productid the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
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

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
}
