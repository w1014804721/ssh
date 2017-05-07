package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int UserId;
	private String IdentityNumber;
	private String TrueName;
	private String Password;
	private String BankNumber;
	private String Age;
	private String HeadImage;
	private int gender;
	private String Education;
	private String CreditAsBoss;
	private String CreditAsWorker;
	private float Balance;
	private String NickName;
	private String Occupation;
	private String PayPassword;
	private int checkin;

	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}

	public int getCheckin() {
		return checkin;
	}

	public String getOccupation() {
		return Occupation;
	}

	public void setOccupation(String occupation) {
		Occupation = occupation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getIdentityNumber() {
		return IdentityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		IdentityNumber = identityNumber;
	}

	public String getTrueName() {
		return TrueName;
	}

	public void setTrueName(String trueName) {
		TrueName = trueName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getBankNumber() {
		return BankNumber;
	}

	public void setBankNumber(String bankNumber) {
		BankNumber = bankNumber;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public String getHeadImage() {
		return HeadImage;
	}

	public void setHeadImage(String headImage) {
		HeadImage = headImage;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return Education;
	}

	public void setEducation(String education) {
		Education = education;
	}

	public String getCreditAsBoss() {
		return CreditAsBoss;
	}

	public void setCreditAsBoss(String creditAsBoss) {
		CreditAsBoss = creditAsBoss;
	}

	public String getCreditAsWorker() {
		return CreditAsWorker;
	}

	public void setCreditAsWorker(String creditAsWorker) {
		CreditAsWorker = creditAsWorker;
	}

	public float getBalance() {
		return Balance;
	}

	public void setBalance(float balance) {
		Balance = balance;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getPayPassword() {
		return PayPassword;
	}

	public void setPayPassword(String payPassword) {
		PayPassword = payPassword;
	}
	
}
