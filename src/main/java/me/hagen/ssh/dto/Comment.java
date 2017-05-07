package me.hagen.ssh.dto;

import java.util.ArrayList;

public class Comment {
	
	private String name;
	private int star;
	private String content;
	private ArrayList<String> imgNameArrayList;
	private int UserId;   // 被评论人的id
	private int PublisherId;   //评论发布者的id
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ArrayList<String> getImgNameArrayList() {
		return imgNameArrayList;
	}
	public void setImgNameArrayList(ArrayList<String> imgNameArrayList) {
		this.imgNameArrayList = imgNameArrayList;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public int getPublisherId() {
		return PublisherId;
	}
	public void setPublisherId(int publisherId) {
		PublisherId = publisherId;
	}
	
	
	
}
