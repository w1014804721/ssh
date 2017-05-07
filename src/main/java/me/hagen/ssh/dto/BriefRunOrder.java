package me.hagen.ssh.dto;

import java.sql.Timestamp;

public class BriefRunOrder {
	private int OrderId;
	private String Price;
	private String Name;
	private String Destination;
	private String FinishTime;
	private String content;
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}

	public String getFinishTime() {
		return FinishTime;
	}
	public void setFinishTime(String finishTime) {
		FinishTime = finishTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	
	
}
