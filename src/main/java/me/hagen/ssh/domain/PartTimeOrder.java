package me.hagen.ssh.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "part_time_order")
public class PartTimeOrder implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6286364572010511246L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Name;
	private String Detail;
	private String Pay;
	private String Location;
	private Float Longitude;
	private Float Latitude;
	private Timestamp ReleaseTime;
	private Timestamp StartTime;
	private Timestamp CutoffTime;
	private Timestamp EndInTime;
	private int Gender;
	private String Age;
	private String Education;
	private String Occupation;
	private int RequiredNumber;
	private int CurrentNumber;
	private int Userid;
	private String Cid;
	private String SafeMoney;
	private int Status;
	private int Type;
	private float AverageStar;   //用户发布时候的信誉度 从averageStar表中读取那个字段 一位小数
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
	}
	public String getPay() {
		return Pay;
	}
	public void setPay(String pay) {
		Pay = pay;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	
	public Timestamp getEndInTime() {
		return EndInTime;
	}
	public void setEndInTime(Timestamp endInTime) {
		EndInTime = endInTime;
	}
	public float getAverageStar() {
		return AverageStar;
	}
	public void setAverageStar(float averageStar) {
		AverageStar = averageStar;
	}
	public Float getLongitude() {
		return Longitude;
	}
	public void setLongitude(Float longitude) {
		Longitude = longitude;
	}
	public Float getLatitude() {
		return Latitude;
	}
	public void setLatitude(Float latitude) {
		Latitude = latitude;
	}
	public Timestamp getReleaseTime() {
		return ReleaseTime;
	}
	public void setReleaseTime(Timestamp releaseTime) {
		ReleaseTime = releaseTime;
	}
	public Timestamp getStartTime() {
		return StartTime;
	}
	public void setStartTime(Timestamp startTime) {
		StartTime = startTime;
	}
	public Timestamp getCutoffTime() {
		return CutoffTime;
	}
	public void setCutoffTime(Timestamp cutoffTime) {
		CutoffTime = cutoffTime;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public String getAge() {
		return Age;
	}
	public void setAge(String age) {
		Age = age;
	}
	public String getEducation() {
		return Education;
	}
	public void setEducation(String education) {
		Education = education;
	}
	public String getOccupation() {
		return Occupation;
	}
	public void setOccupation(String occupation) {
		Occupation = occupation;
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
	public int getUserid() {
		return Userid;
	}
	public void setUserid(int userid) {
		Userid = userid;
	}
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	public String getSafeMoney() {
		return SafeMoney;
	}
	public void setSafeMoney(String safeMoney) {
		SafeMoney = safeMoney;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	
	
}
