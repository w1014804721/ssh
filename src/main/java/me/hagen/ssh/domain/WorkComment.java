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
	private int PublisherId; // ���������ߵ�id
	private int UserId;   //�����ߵ�id
	private int Type;   // 0 �Ƿ����߶Թ����ߵ�����  1 ��֮
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
