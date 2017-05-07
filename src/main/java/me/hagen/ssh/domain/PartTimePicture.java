package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="part_time_picture")
public class PartTimePicture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int OrderId;
	private String ImgURL;
	private String ImgName;
	
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImgURL() {
		return ImgURL;
	}
	public void setImgURL(String imgURL) {
		ImgURL = imgURL;
	}
	public String getImgName() {
		return ImgName;
	}
	public void setImgName(String imgName) {
		ImgName = imgName;
	}
	
	
}
