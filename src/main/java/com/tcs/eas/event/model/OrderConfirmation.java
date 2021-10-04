package com.tcs.eas.event.model;

import java.io.Serializable;

public class OrderConfirmation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4610617068793437230L;

	private int orderId;

	private String firstName;
	
	private String lastName;

	private String dop;
	
	private String currency;
	
	

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the dop
	 */
	public String getDop() {
		return dop;
	}

	/**
	 * @param dop the dop to set
	 */
	public void setDop(String dop) {
		this.dop = dop;
	}

	public OrderConfirmation(int orderId, String firstName, String lastName, String dop,String currency) {
		super();
		this.orderId = orderId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.currency = currency;
		this.dop = dop;
	}

	public OrderConfirmation() {
		
	}
}
