package me.hagen.ssh.dto;

import java.sql.Timestamp;

/**用于向客户端回传数据  
 * */
public class BriefPartTimeOrder {
	//默认的图片 尝试是否可以存在客户端本地
	private int OrderId;
	private String Price;
	private String Name;
	private String StartTime;
	private String CutoffTime;
	private String Location;
	private int RequiredNumber;
	private int CurrentNumber;
	private String Detail;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getCutoffTime() {
		return CutoffTime;
	}
	public void setCutoffTime(String cutoffTime) {
		CutoffTime = cutoffTime;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}

	public int getRequiredNumber() {
		return RequiredNumber;
	}
	public void setRequiredNumber(int requiredNumber) {
		RequiredNumber = requiredNumber;
	}
	public int getCurrentNumber() {
		return CurrentNumber;
	}
	public void setCurrentNumber(int currentNumber) {
		CurrentNumber = currentNumber;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	
	
}
