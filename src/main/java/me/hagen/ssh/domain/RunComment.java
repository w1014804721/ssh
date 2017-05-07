package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "run_comment")
public class RunComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String Content;
	private int RunId;
	private int UserId;
	private int Type;
	private int Star;
	private int publisherId; 
	
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

	public int getRunId() {
		return RunId;
	}

	public void setRunId(int runId) {
		RunId = runId;
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
		return publisherId;
	}

	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
}
