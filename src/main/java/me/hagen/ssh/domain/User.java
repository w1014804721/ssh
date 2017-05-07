package me.hagen.ssh.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String phoneNumber;
	
	private int CertifyType;
	private String QQNumber;
	private String wcNumber;
	private String wbNumber;
	private String userToken;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

	public int getCertifyType() {
		return CertifyType;
	}
	public void setCertifyType(int certifyType) {
		CertifyType = certifyType;
	}
	public String getQQNumber() {
		return QQNumber;
	}
	public void setQQNumber(String qQNumber) {
		QQNumber = qQNumber;
	}
	public String getWcNumber() {
		return wcNumber;
	}
	public void setWcNumber(String wcNumber) {
		this.wcNumber = wcNumber;
	}
	public String getWbNumber() {
		return wbNumber;
	}
	public void setWbNumber(String wbNumber) {
		this.wbNumber = wbNumber;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	
	
	
}
