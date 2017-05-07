package me.hagen.ssh.service;

import me.hagen.ssh.dao.PartTimeCommentDao;
import me.hagen.ssh.dao.PartTimePictureDao;
import me.hagen.ssh.dao.RunCommentPictureDao;
import me.hagen.ssh.dao.RunOrderPictureDao;
import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.dao.WorkCommentPictureDao;
import me.hagen.ssh.domain.PartTimePicture;
import me.hagen.ssh.domain.RunCommentPicture;
import me.hagen.ssh.domain.Runpicture;
import me.hagen.ssh.domain.WorkCommentPicture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PictureService")   //出了问题恢复这里
@Transactional
public class PictureService {
	@Autowired
	private PartTimePictureDao ptpDao;
	@Autowired
	private RunOrderPictureDao rpDao;
	@Autowired
	private WorkCommentPictureDao workCommentPictureDao;
	@Autowired
	private RunCommentPictureDao runCommentPictureDao;
	@Autowired
	private UserDao userDao;
	
	/** 兼职发布   处理兼职图片进入数据库
	 * */
	public int UploadPartTimePicutre(String imgURL,int OrderId,String imgName){
		int result = -1;
		PartTimePicture ptp = new PartTimePicture();
		ptp.setImgURL(imgURL);
		ptp.setOrderId(OrderId);
		ptp.setImgName(imgName);
		ptpDao.create(ptp);
		result = 1;
		return result;
	}
	
	  
	/** 跑腿订单发布  处理图片进入数据库  通过单元测试
	 * */
	public int UploadRunPicture(String imgName,int orderId){
		int result = -1;
		Runpicture rp =new Runpicture();
		rp.setImgName(imgName);
		rp.setOrderId(orderId);
		rpDao.create(rp);
		result = 1;
		return result;
	}
	
	
	/**  兼职评论发布 处理图片进入数据库 
	 *   通过单元测试
	 * */
	public int UploadWorkCommentPicutre(String imgURL,int WorkCommentId,String imgName){
		int result = -1;
		WorkCommentPicture workCommentPicture = new WorkCommentPicture();
		workCommentPicture.setImgName(imgName);
		workCommentPicture.setImgURL(imgURL);
		workCommentPicture.setWorkCommentId(WorkCommentId);
		workCommentPictureDao.create(workCommentPicture);
		result = 1;
		return result;
	}
	
	/**   跑腿评论发布 处理图片进入数据库
	 * 通过单元测试
	 * */
	public int UploadRunCommentPicture(String imgName,int commentId){
		int result = -1;
		RunCommentPicture  rcp = new RunCommentPicture();
		rcp.setCommentId(commentId);
		rcp.setImgName(imgName);
		runCommentPictureDao.create(rcp);
		result = 1;
		return result;
	}
	
	/**  发布或者更新头像
	 * */
	public int UploadHeadImg(String imgName,int userid){
		int result = -1;
		result = userDao.UpdateHeadImg(userid, imgName);
		return result;
	}
	
}
