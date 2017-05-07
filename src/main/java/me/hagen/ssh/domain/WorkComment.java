package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="work_comment")
public class WorkComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Content;
	private int WorkId;
	private int PublisherId; // 订单发布者的id
	private int UserId;   //工作者的id
	private int Type;   // 0 是发布者对工作者的评论  1 反之
	private int Star;
	
	public int getStar() {
		return Star;
	}
	public void setStar(int star) {
		Star = star;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public int getWorkId() {
		return WorkId;
	}
	public void setWorkId(int workId) {
		WorkId = workId;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getType() {
		return Type;
	}
	public void setType(int type) {
		Type = type;
	}
	public int getPublisherId() {
		return PublisherId;
	}
	public void setPublisherId(int publisherId) {
		PublisherId = publisherId;
	}
	
}
