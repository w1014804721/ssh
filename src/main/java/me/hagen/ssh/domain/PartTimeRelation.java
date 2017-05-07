package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="part_time_relation")
public class PartTimeRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	private int OrderId;
	private int UserId;
	private int Hired;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getHired() {
		return Hired;
	}
	public void setHired(int hired) {
		Hired = hired;
	}
	
	
}
