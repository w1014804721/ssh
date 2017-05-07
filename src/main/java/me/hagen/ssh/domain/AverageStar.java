package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "average_star")
public class AverageStar {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int UserId;
	private float PTPublisher;  //一位小数
	private float PTWorker;
	private float Runpublisher;
	private float RunWorker;
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
	public float getPTPublisher() {
		return PTPublisher;
	}
	public void setPTPublisher(float pTPublisher) {
		PTPublisher = pTPublisher;
	}
	public float getPTWorker() {
		return PTWorker;
	}
	public void setPTWorker(float pTWorker) {
		PTWorker = pTWorker;
	}
	public float getRunpublisher() {
		return Runpublisher;
	}
	public void setRunpublisher(float runpublisher) {
		Runpublisher = runpublisher;
	}
	public float getRunWorker() {
		return RunWorker;
	}
	public void setRunWorker(float runWorker) {
		RunWorker = runWorker;
	}
	

	
}
