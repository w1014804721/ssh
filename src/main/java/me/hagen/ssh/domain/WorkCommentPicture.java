package me.hagen.ssh.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="work_comment_picture")
public class WorkCommentPicture {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int WorkCommentId;
	private String ImgURL;
	private String ImgName;
	

	public int getWorkCommentId() {
		return WorkCommentId;
	}
	public void setWorkCommentId(int workCommentId) {
		WorkCommentId = workCommentId;
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
