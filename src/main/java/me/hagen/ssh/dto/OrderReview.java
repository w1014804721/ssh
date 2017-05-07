package me.hagen.ssh.dto;

import java.sql.Timestamp;





/**用于向客户端回传数据
 * 
 * 查看订单状态时候包含的信息
 * */
public class OrderReview {
	private int orderid;
	private String title;
	private String Pay;
	private String Detail;
	private int Status;
	private String EndInTime; //截至时间
	private int RequiredNumber;
	private int CurrentNumber;
	private int errcode;
	private int CommentToPublisher;  //这个值提供给工作者使用 用来判断该工作者是否对 发布者进行了评价
	
	
	
	
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPay() {
		return Pay;
	}
	public void setPay(String pay) {
		Pay = pay;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public String getEndInTime() {
		return EndInTime;
	}
	public void setEndInTime(String endInTime) {
		EndInTime = endInTime;
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
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public int getCommentToPublisher() {
		return CommentToPublisher;
	}
	public void setCommentToPublisher(int commentToPublisher) {
		CommentToPublisher = commentToPublisher;
	}
	
	
	
}
