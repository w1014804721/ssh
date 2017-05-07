package me.hagen.ssh.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Finishings;

import me.hagen.ssh.dao.RunOrderPictureDao;
import me.hagen.ssh.dao.RunoffOrderDao;
import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.RunOffOrder;
import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.BriefRunOrder;
import me.hagen.ssh.dto.ConfirmPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spatial4j.core.shape.impl.Range.LongitudeRange;

@Service("RunOffOrderService")
@Transactional
public class RunoffOrderService {
	@Autowired
	private RunoffOrderDao runoffOrderDao;
	@Autowired
	private  RunOrderPictureDao RunPictureDao;
	@Autowired
	private UserDao userDao;

	/**
	 * 发布新的跑腿信息 
	 * @return 返回存入这一条订单的id成功 
	 *   别的值就是出了一些奇怪的问题
	 * */
	public int publicRORDER(int UserId, String Name, String Detail,
			String Pay, float Longitude,float Latitude, Timestamp ReleaseTime,
			String Destination, Timestamp FinishTime, int Status) {
		int result = -1;
		RunOffOrder roo = new RunOffOrder();
		roo.setUserId(UserId);
		roo.setName(Name);
		roo.setReleaseTime(ReleaseTime);
		roo.setDestination(Destination);
		roo.setLongitude(Longitude);
		roo.setLatitude(Latitude);
		roo.setFinishTime(FinishTime);
		roo.setDetail(Detail);
		roo.setPay(Pay);
		roo.setStatus(Status);
		roo.setAverageStar(4);//调用一个根据userid获得当前信誉度的方法);
		System.out.println("33333333333");
		runoffOrderDao.create(roo);
		result = roo.getId();
		return result;
	}
	
	
	/**
	 * 查看具体某一个详细信息 传入orderid userid 得到一个Map    
	 * */
	public Map<String, Object> showDetail(int orderid,int Userid) {
		Map<String, Object> temp = new HashMap<String, Object>();
		List<BriefRunOrder> result= runoffOrderDao.ShowRelative();
		RunOffOrder roo = runoffOrderDao.ShowDetail(orderid);
		int ifEnroll = runoffOrderDao.IfEnroll(Userid, orderid);
		
		if (roo == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = RunPictureDao.getImgName(orderid);//取得对应图片的名字
	        int userid = roo.getUserId();
			String name = roo.getName();
			String price = roo.getPay();
			float Longitude = roo.getLongitude();
			float Latitude = roo.getLatitude();
			Timestamp ReleaseTime1 = roo.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			String username = userDao.getTrueName(userid);// 发布者真实姓名
			float star = roo.getAverageStar();
			Timestamp FinishTime1 = roo.getFinishTime();
			String FinishTime = FinishTime1.toString();
			
			String Destination = roo.getDestination();
			int Status = roo.getStatus();
			String content = roo.getDetail();
			temp.put("Status", Status);
			temp.put("IfEnroll", ifEnroll);
			temp.put("userid", userid);
			temp.put("name", name);
			temp.put("Longitude", Longitude);
			temp.put("Latitude", Latitude);
			temp.put("price", price);
			temp.put("ReleaseTime", ReleaseTime);
			temp.put("username", username);
			temp.put("FinishTime", FinishTime);
			temp.put("star", star);
			temp.put("destination", Destination);
			temp.put("content", content);  //后面附加 几个相关推荐
			temp.put("ImgName", imgNameArrayList);// 图片名称
			temp.put("relative", result);
			return temp;
		}

	}
	
	
	
	/**
	 * 刷新到主页时 看一些兼职的简略信息  返回值是一个包含了规定数目dto的list
	 *
	 * */
	public List<BriefRunOrder> showLatest(int time) {
		List<BriefRunOrder> result= runoffOrderDao.showLatest(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * 按照价格又高到低 展示信息  返回值是一个包含了规定数目dto的list
	 *
	 * */
	public List<BriefRunOrder> showByPay(int time) {
		List<BriefRunOrder> result= runoffOrderDao.showByPay(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * 按距离查看
	 *
	 * */
	public List<BriefRunOrder> showByDistance(int time,float longitude,float latitude) {
		List<BriefRunOrder> result= runoffOrderDao.showByDistance(time,longitude,latitude);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 按信誉度查看
	 *
	 * */
	public List<BriefRunOrder> showByStar(int time) {
		List<BriefRunOrder> result= runoffOrderDao.showByStar(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**根据userid 删除某一条订单 -1失败0成功
	 * */
	public int Delete(int id){
		int result = runoffOrderDao.DeleteRunOrder(id);
		return result;
	}
	
	/**
	 * 按关键字匹配筛选
	 *
	 * */
	public List<BriefRunOrder> showByKeyWord(String key,int time) {
		List<BriefRunOrder> result= runoffOrderDao.ShowByKeyword(key,time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/** 报名接口
	 *  @return 只有返回1 是成功
	 * */
	public int Enroll(int orderId,int userId){
		int result = runoffOrderDao.Enroll(orderId, userId);
		return result;
	}
	
	/** 跑腿 订单
	 * 确认付款
	 * */
	public List<ConfirmPay> ConfirmPay(int orderid){
		List<ConfirmPay> result = runoffOrderDao.ConfirmPay(orderid);
		return result;
	}
	
	
	/**雇主 发现兼职已经完成了  
	 *   点击确认完成    订单完成 但是没有付款  进入付款流程
	 * */
	public int WorkDone(int orderid){
		int result = runoffOrderDao.WorkDone(orderid);
		return result;
	}
	
	
	/** status = 3 订单状态变为付款成功 带评价
	 * */
	public int WaitComment(int orderid){
		int result = runoffOrderDao.WaitComment(orderid);
		return result;
	}
	
	
	/** status = 4  订单状态变为彻底结束了
	 * */
	public int Finished(int orderid){
		int result = runoffOrderDao.Finished(orderid);
		return result;
	}
	
	/** status = 8 订单状态变为彻底结束了
	 * */
	public int BossComment(int orderid){
		int result = runoffOrderDao.BossComment(orderid);
		return result;
	}
	
	/** status = 9 订单状态变为彻底结束了
	 * */
	public int WorkerComment(int orderid){
		int result = runoffOrderDao.WorkerComment(orderid);
		return result;
	}
	
}
