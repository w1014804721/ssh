package me.hagen.ssh.dto;

public class ConfirmPay {
	private String pay;	
	private int userid;   //ÿһ���������˵�id  Ҳ����һ��Ҫ��Ǯ���˵�id
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
