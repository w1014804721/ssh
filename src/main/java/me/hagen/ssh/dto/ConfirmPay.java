package me.hagen.ssh.dto;

public class ConfirmPay {
	private String pay;	
	private int userid;   //每一个报名的人的id  也就是一会要拿钱的人的id
	private String TrueName;
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getTrueName() {
		return TrueName;
	}
	public void setTrueName(String trueName) {
		TrueName = trueName;
	}
	
	
}
