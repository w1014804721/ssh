package me.hagen.ssh.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hagen.ssh.dao.BossRunDAO;
import me.hagen.ssh.dao.PartTimeOrderDao;
import me.hagen.ssh.dao.RunOrderPictureDao;
import me.hagen.ssh.dao.RunoffOrderDao;
import me.hagen.ssh.domain.RunOffOrder;
import me.hagen.ssh.dto.BriefRunOrder;
import me.hagen.ssh.dto.EnRolledMan;
import me.hagen.ssh.dto.OrderReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BossRunService")   //出了问题恢复这里
@Transactional
public class BossRunService {
	@Autowired
	private BossRunDAO bossRunDao;
	@Autowired
	private  RunOrderPictureDao RunPictureDao;
	@Autowired
	private RunoffOrderDao runoffOrderDao;
	@Autowired
	private PartTimeOrderDao ptodao;
	
	/**展示全部
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = bossRunDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	

	/**展示工作人员未确定的
	 * */
	public  List<OrderReview> UnChosen(int userid){
		List<OrderReview> result = bossRunDao.UnChosen(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**展示正在进行的
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = bossRunDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**展示已完成但是 没有付款的
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = bossRunDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**展示付款未评价的
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = bossRunDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	

	
	/**彻底完成了 评价结束
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = bossRunDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * 查看具体某一个详细信息 传入orderid userid 得到一个Map    
	 * 
	 * 无报名者信息
	 * */
	public Map<String, Object> showDetail(int orderid) {
		Map<String, Object> temp = new HashMap<String, Object>();
		RunOffOrder roo = runoffOrderDao.ShowDetail(orderid);
		int publisherId = roo.getUserId();//发布者的userid
		
		
		if(roo == null) 
			return temp;
		else {
			ArrayList<String> imgNameArrayList = RunPictureDao.getImgName(orderid);//取得对应图片的名字
			String publisherName = bossRunDao.GetTrueName(publisherId); //发布者真名
			String publisherPhone = bossRunDao.PublisherPhone(publisherId);//发布者手机号
			float Latitude = roo.getLatitude();
			float Longitude = roo.getLongitude();
			String name = roo.getName();
			String price = roo.getPay();
			Timestamp ReleaseTime1 = roo.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			float star = roo.getAverageStar();
			Timestamp FinishTime1 = roo.getFinishTime();
			String FinishTime = FinishTime1.toString();
			
			String Destination = roo.getDestination();
			int Status = roo.getStatus();
			String content = roo.getDetail();
			temp.put("publisherName", publisherName);
			temp.put("publisherPhone", publisherPhone);
			temp.put("Status", Status);
			temp.put("Longitude", Longitude);
			temp.put("Latitude",Latitude);
			temp.put("userid", publisherId);
			temp.put("name", name);
			temp.put("price", price);
			temp.put("ReleaseTime", ReleaseTime);
			temp.put("FinishTime", FinishTime);
			temp.put("star", star);
			temp.put("destination", Destination);
			temp.put("content", content);  //后面附加 几个相关推荐
			temp.put("ImgName", imgNameArrayList);// 图片名称
			temp.put("publisherId", publisherId);
			return temp;
		}
		
	}
	
	
		/** 详情 加 报名者信息
		 * */
		public Map<String, Object> DetailWithEnroll(int orderid) {
			Map<String, Object> temp = new HashMap<String, Object>();
			RunOffOrder roo = runoffOrderDao.ShowDetail(orderid);
			List<EnRolledMan> EnrolledMam = bossRunDao.enRolledMan(orderid);
			int publisherId = roo.getUserId();//发布者的userid
			
			if(roo == null) 
				return temp;
			else {
				ArrayList<String> imgNameArrayList = RunPictureDao.getImgName(orderid);//取得对应图片的名字
				String name = roo.getName();
				String price = roo.getPay();
				float Latitude = roo.getLatitude();
				float Longitude = roo.getLongitude();
				Timestamp ReleaseTime1 = roo.getReleaseTime();
				String ReleaseTime = ReleaseTime1.toString();
				float star = roo.getAverageStar();
				System.out.println("#################"+star);
				Timestamp FinishTime1 = roo.getFinishTime();
				String FinishTime = FinishTime1.toString();
				
				String Destination = roo.getDestination();
				int Status = roo.getStatus();
				String content = roo.getDetail();
				temp.put("Status", Status);
				temp.put("EnrolledMam", EnrolledMam);
				temp.put("Longitude", Longitude);
				temp.put("Latitude",Latitude);
				temp.put("name", name);
				temp.put("price", price);
				temp.put("ReleaseTime", ReleaseTime);
				temp.put("FinishTime", FinishTime);
				temp.put("star", star);
				temp.put("destination", Destination);
				temp.put("content", content);  //后面附加 几个相关推荐
				temp.put("ImgName", imgNameArrayList);// 图片名称
				temp.put("publisherId", publisherId);
				return temp;
			}
	}
}
