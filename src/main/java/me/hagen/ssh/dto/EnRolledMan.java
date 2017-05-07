package me.hagen.ssh.dto;
//报名者
public class EnRolledMan {
	private int Hired;
	private int userid;
	private String trueName;
	private float AverageStar;
	private String HeadImg;
	private String phoneNumber;
	private int IfCommented;  //代表该报名者是否 被 订单发布者评价过    1没有  2评价过
	
	
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	
	public String getHeadImg() {
		return HeadImg;
	}
	public void setHeadImg(String headImg) {
		HeadImg = headImg;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public float getAverageStar() {
		return AverageStar;
	}
	public void setAverageStar(float averageStar) {
		AverageStar = averageStar;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getHired() {
		return Hired;
	}
	public void setHired(int hired) {
		Hired = hired;
	}
	public int getIfCommented() {
		return IfCommented;
	}
	public void setIfCommented(int ifCommented) {
		IfCommented = ifCommented;
	}
	
	
}
