package me.hagen.ssh.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coupon")
public class Coupon {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int UserId;
	private int Limit;
	private int Type;
	private int Status;
	private Timestamp FinishedTime;
	private int Decreased;
	
	public int getDecreased() {
		return Decreased;
	}
	public void setDecreased(int decreased) {
		Decreased = decreased;
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
	public int getLimit() {
		return Limit;
	}
	public void setLimit(int limit) {
		Limit = limit;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
	public Timestamp getFinishedTime() {
		return FinishedTime;
	}
	public void setFinishedTime(Timestamp finishedTime) {
		FinishedTime = finishedTime;
	}
	
}
