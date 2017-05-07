package me.hagen.ssh.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "run_off_order")
public class RunOffOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Name;
	private Timestamp ReleaseTime;
	private String Destination;
	private float Longitude;
	private float Latitude;
	private Timestamp FinishTime;
	private String Detail;
	private String Pay;
	private int UserId;
	private int Status;
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



	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	
	
	public float getLongitude() {
		return Longitude;
	}

	public void setLongitude(float longitude) {
		Longitude = longitude;
	}

	public float getLatitude() {
		return Latitude;
	}

	public void setLatitude(float latitude) {
		Latitude = latitude;
	}

	public Timestamp getReleaseTime() {
		return ReleaseTime;
	}

	public void setReleaseTime(Timestamp releaseTime) {
		ReleaseTime = releaseTime;
	}

	public Timestamp getFinishTime() {
		return FinishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		FinishTime = finishTime;
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


	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public float getAverageStar() {
		return AverageStar;
	}

	public void setAverageStar(float averageStar) {
		AverageStar = averageStar;
	}

}
