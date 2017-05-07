package me.hagen.ssh.dto;

public class youhui  {
	private int id; //就是数据库里面的id主键
	private int decreased;
	private int limit;
	private int type;
	private String FinishedTime;    //截止日期   用toString转
	private int Status;
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getDecreased() {
		return decreased;
	}
	public void setDecreased(int decreased) {
		this.decreased = decreased;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFinishedTime() {
		return FinishedTime;
	}
	public void setFinishedTime(String finishedTime) {
		FinishedTime = finishedTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
