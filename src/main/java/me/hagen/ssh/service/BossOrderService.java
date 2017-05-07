package me.hagen.ssh.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hagen.ssh.dao.BossOrderDao;
import me.hagen.ssh.dao.PartTimeOrderDao;
import me.hagen.ssh.dao.PartTimePictureDao;
import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.dao.WorkerOrderDao;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.EnRolledMan;
import me.hagen.ssh.dto.OrderReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("BossOrderService")   //出了问题恢复这里
@Transactional
public class BossOrderService {
	
	@Autowired
	private BossOrderDao bossOrderDao;
	@Autowired
	private PartTimePictureDao ptpDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private WorkerOrderDao workOrderDao;
	
	
	/**展示全部
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = bossOrderDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	

	/**展示工作人员未确定的
	 * */
	public  List<OrderReview> UnChosen(int userid){
		List<OrderReview> result = bossOrderDao.UnChosen(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**展示正在进行的
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = bossOrderDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**展示已完成但是 没有付款的
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = bossOrderDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**展示付款未评价的
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = bossOrderDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	

	
	/**彻底完成了 评价结束
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = bossOrderDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/** 展示详情 
	 *  包括 之前的详情 以及 报名的人 还有 每个人的信誉度   这个人的真实姓名   
	 * */
	public Map<String, Object> showDetail(int orderid){
		Map<String, Object> temp = new HashMap<String, Object>();
		PartTimeOrder pto = bossOrderDao.ShowDetail(orderid);
		List<EnRolledMan> People= bossOrderDao.ShowEnrolled(orderid);
		
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//取得对应图片的名字
			float Latitude = pto.getLatitude();
			float Longitude = pto.getLongitude();
			String name = pto.getName();
			String price = pto.getPay();
			int Status = pto.getStatus();
			Timestamp ReleaseTime1 = pto.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			Timestamp EndInTime1 = pto.getEndInTime();
			String EndInTime = EndInTime1.toString();
			float star = pto.getAverageStar();
			Timestamp startTime1 = pto.getStartTime();
			String startTime = startTime1.toString();
			Timestamp finishTime1 = pto.getCutoffTime();
			String finishTime = finishTime1.toString();
			String location = pto.getLocation();
			int number1 = pto.getCurrentNumber();
			int number2 = pto.getRequiredNumber();
			String requirement = pto.getAge()+"//"+pto.getGender()+"//"+pto.getEducation()+"//"+pto.getOccupation();
			String content = pto.getDetail();
			int userid = pto.getUserid();
			temp.put("Status", Status);
			temp.put("userid", userid);
			temp.put("name", name);
			temp.put("price", price);
			temp.put("ReleaseTime", ReleaseTime);
			temp.put("Longitude", Longitude);
			temp.put("Latitude", Latitude);
			temp.put("EndInTime", EndInTime);
			temp.put("star", star);
			temp.put("startTime", startTime);
			temp.put("finishTime", finishTime);
			temp.put("location", location);
			temp.put("number1", number1);
			temp.put("number2", number2);
			temp.put("requirement", requirement);
			temp.put("content", content);  //后面附加 几个相关推荐
			temp.put("ImgName", imgNameArrayList);
			temp.put("People", People);
		}
		return temp;
	}
	
	
	

	/** 展示 详情 
	 * 
	 * 该接口给 我发布的订单 已完成但是未评价的使用  
	 * 展示出所有参与工作的人 还有订单的详细信息
	 * */
	public Map<String, Object> showHired(int orderid){
		Map<String, Object> temp = new HashMap<String, Object>();
		PartTimeOrder pto = bossOrderDao.ShowDetail(orderid);
		List<EnRolledMan> People= bossOrderDao.showHired(orderid);
		
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//取得对应图片的名字
			String name = pto.getName();
			String price = pto.getPay();
			int Status = pto.getStatus();
			float Latitude = pto.getLatitude();
			float Longitude = pto.getLongitude();
			Timestamp ReleaseTime1 = pto.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			Timestamp EndInTime1 = pto.getEndInTime();
			String EndInTime = EndInTime1.toString();
			float star = pto.getAverageStar();
			Timestamp startTime1 = pto.getStartTime();
			String startTime = startTime1.toString();
			Timestamp finishTime1 = pto.getCutoffTime();
			String finishTime = finishTime1.toString();
			String location = pto.getLocation();
			int number1 = pto.getCurrentNumber();
			int number2 = pto.getRequiredNumber();
			String requirement = pto.getAge()+"//"+pto.getGender()+"//"+pto.getEducation()+"//"+pto.getOccupation();
			String content = pto.getDetail();
			int userid = pto.getUserid();
			temp.put("userid", userid);
			temp.put("Status", Status);
			temp.put("Longitude", Longitude);
			temp.put("Latitude", Latitude);
			temp.put("name", name);
			temp.put("price", price);
			temp.put("ReleaseTime", ReleaseTime);
			temp.put("EndInTime", EndInTime);
			temp.put("star", star);
			temp.put("startTime", startTime);
			temp.put("finishTime", finishTime);
			temp.put("location", location);
			temp.put("number1", number1);
			temp.put("number2", number2);
			temp.put("requirement", requirement);
			temp.put("content", content);  //后面附加 几个相关推荐
			temp.put("ImgName", imgNameArrayList);
			temp.put("People", People);
		}
		return temp;
	}
	
	
	
	
	
	
	
	/**  没有报名者的详细信息 提供给 兼职模块 剩下的九种使用
	 * 
	 *  传入的参数 Userid 是当前用户的id  里面获取的userid是订单发布者的id
	 * */
	public Map<String, Object> onlyDetail(int orderid,int Userid){
		Map<String, Object> temp = new HashMap<String, Object>();
		PartTimeOrder pto = bossOrderDao.ShowDetail(orderid);
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//取得对应图片的名字
			String name = pto.getName();
			String price = pto.getPay();
			int Status = pto.getStatus();
			float Latitude = pto.getLatitude();
			float Longitude = pto.getLongitude();
			Timestamp ReleaseTime1 = pto.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			Timestamp EndInTime1 = pto.getEndInTime();
			String EndInTime = EndInTime1.toString();
			float star = pto.getAverageStar();
			Timestamp startTime1 = pto.getStartTime();
			String startTime = startTime1.toString();
			Timestamp finishTime1 = pto.getCutoffTime();
			String finishTime = finishTime1.toString();
			String location = pto.getLocation();
			int number1 = pto.getCurrentNumber();
			int number2 = pto.getRequiredNumber();
			String requirement = pto.getAge()+"//"+pto.getGender()+"//"+pto.getEducation()+"//"+pto.getOccupation();
			String content = pto.getDetail();
			int userid = pto.getUserid();
			String trueName = userDao.getTrueName(userid);
			String phoneNumber = userDao.getPhone(userid);
			int CommentToPublisher = workOrderDao.CommentToPublisher(Userid, orderid);
			temp.put("userid", userid);
			temp.put("trueName", trueName);
			temp.put("phoneNumber", phoneNumber);
			temp.put("Longitude", Longitude);
			temp.put("Latitude", Latitude);
			temp.put("name", name);
			temp.put("price", price);
			temp.put("Status", Status);
			temp.put("ReleaseTime", ReleaseTime);
			temp.put("EndInTime", EndInTime);
			temp.put("star", star);
			temp.put("CommentToPublisher",CommentToPublisher);
			temp.put("startTime", startTime);
			temp.put("finishTime", finishTime);
			temp.put("location", location);
			temp.put("number1", number1);
			temp.put("number2", number2);
			temp.put("requirement", requirement);
			temp.put("content", content);  //后面附加 几个相关推荐
			temp.put("ImgName", imgNameArrayList);
		}
		return temp;
	}
}
