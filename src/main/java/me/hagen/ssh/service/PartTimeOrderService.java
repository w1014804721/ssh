package me.hagen.ssh.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hagen.ssh.dao.PartTimeOrderDao;
import me.hagen.ssh.dao.PartTimePictureDao;
import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.dao.WorkerOrderDao;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.ConfirmPay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ptoService")   //出了问题恢复这里
@Transactional
public class PartTimeOrderService {
	@Autowired
	private PartTimeOrderDao partTimeOrderDao;
	@Autowired
	private PartTimePictureDao ptpDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private WorkerOrderDao workOrderDao;

	/**
	 * 发布新的兼职信息 通过单元测试   
	 * 
	 *     @return 是这个方法插入数据库后的这一条数据的 主键id值
	 * */
	public int publishPTOrder(int UserId, String Name, String Detail,
			String Pay, String Location, Float Longitude, Float Latitude,Timestamp ReleaseTime,
			Timestamp StartTime, Timestamp CutoffTime, Timestamp EndInTime,int Gender, String Age,
			String Education, String Occupation, int RequiredNumber,
			int CurrentNumber, String Cid, String SafeMoney, int Status,
			int Type) {
		int i = -1;
		PartTimeOrder pto = new PartTimeOrder();
		pto.setUserid(UserId);
		pto.setName(Name);
		pto.setDetail(Detail);
		pto.setPay(Pay);
		pto.setLocation(Location);
		pto.setLongitude(Longitude);
		pto.setLatitude(Latitude);
		pto.setReleaseTime(ReleaseTime);
		pto.setStartTime(StartTime);
		pto.setCutoffTime(CutoffTime);
		pto.setEndInTime(EndInTime);
		pto.setGender(Gender);
		pto.setAge(Age);
		pto.setEducation(Education);
		pto.setOccupation(Occupation);
		pto.setRequiredNumber(RequiredNumber);
		pto.setCurrentNumber(CurrentNumber);
		pto.setCid(Cid);
		pto.setSafeMoney(SafeMoney);
		pto.setStatus(Status);
		pto.setType(Type);
		//pto.setAverageStar(partTimeOrderDao.StarByUserId(UserId));//调用一个根据userid获得当前信誉度的方法
		pto.setAverageStar(4);
		partTimeOrderDao.create(pto);
		System.out.println("fuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+pto.getId());   //这一句很屌
		i = pto.getId();
		
		return i;
	}

	/**
	 * 刷新到主页时 看一些兼职的简略信息  返回值是一个包含了规定数目dto的list
	 *
	 * */
	public List<BriefPartTimeOrder> showMainOrder(int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showMainOrder(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * 按照价格又高到低 展示信息  返回值是一个包含了规定数目dto的list
	 *
	 * */
	public List<BriefPartTimeOrder> showByPay(int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showByPay(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 按距离查看
	 *
	 * */
	public List<BriefPartTimeOrder> showByDistance(int time,float longitude,float latitude) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showByDistance(time,longitude,latitude);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 按信誉度查看
	 *
	 * */
	public List<BriefPartTimeOrder> showByStar(int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showByStar(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 按关键字匹配筛选
	 *
	 * */
	public List<BriefPartTimeOrder> showByKeyWord(String key,int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.ShowByKeyword(key,time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/** 
	 * 查看某一个具体的小分类的 订单简略信息
	 * */
	public List<BriefPartTimeOrder> ShowOneCategory(String Cid,int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.ShowOneCategory(time, Cid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * 查看具体某一个详细信息 传入id 得到一个Map    
	 * */
	public Map<String, Object> showDetail(int orderid,int Userid) {
		Map<String, Object> temp = new HashMap<>();
		List<BriefPartTimeOrder> result= partTimeOrderDao.ShowRelative();
		PartTimeOrder pto = partTimeOrderDao.ShowDetail(orderid);
		System.out.println(Userid);
		System.out.println(orderid);
		int ifEnroll = partTimeOrderDao.IfEnroll(Userid, orderid);
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//取得对应图片的名字
			int userid = pto.getUserid();  //发布者的id
			String name = pto.getName();
			float Longitude = pto.getLongitude();
			float Latitude = pto.getLatitude();
			String price = pto.getPay();
			Timestamp ReleaseTime1 = pto.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			Timestamp EndInTime1 = pto.getEndInTime();
			String EndInTime = EndInTime1.toString();
			//String username = "李**";  //这一行重写
			String username = userDao.getTrueName(userid);
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
			int Status = pto.getStatus();
			int type = pto.getType();
			int CommentToPublisher = workOrderDao.CommentToPublisher(Userid, orderid);
			temp.put("userid", userid);
			temp.put("CommentToPublisher", CommentToPublisher);
			temp.put("Status", Status);
			temp.put("name", name);
			temp.put("type", type);
			temp.put("price", price);
			temp.put("Longitude", Longitude);
			temp.put("Latitude", Latitude);
			temp.put("ReleaseTime", ReleaseTime);
			temp.put("EndInTime", EndInTime);
			temp.put("username", username);
			temp.put("star", star);
			temp.put("startTime", startTime);
			temp.put("finishTime", finishTime);
			temp.put("location", location);
			temp.put("number1", number1);
			temp.put("number2", number2);
			temp.put("requirement", requirement);
			temp.put("IfEnroll", ifEnroll);  //0未报名 1已经报名
			temp.put("content", content);  //后面附加 几个相关推荐
			temp.put("ImgName", imgNameArrayList);
			temp.put("relative", result);//相关推荐
			
			return temp;
		}

	}
	
	
	/**根据userid 删除某一条订单 -1失败0成功
	 * */
	public int Delete(int id){
		int result = partTimeOrderDao.DeletePartTimeOrder(id);
		return result;
	}
	
	/** 报名接口
	 *  @return 只有返回1 是成功
	 * */
	public int Enroll(int orderId,int userId){
		int result = partTimeOrderDao.Enroll(orderId, userId);
		return result;
	}
	
	/** 录用
	 * */
	public int Hire(int orderId,int userId){
		int result = partTimeOrderDao.Hire(orderId, userId);
		return result;
	}
	
	/**  拒绝用户报名
	 * */
	public int Reject(int orderid){
		int result = partTimeOrderDao.Reject(orderid);
		return result;
	}
	
	/**雇主 发现兼职已经完成了  
	 *   点击确认完成    订单完成 但是没有付款  进入付款流程
	 * */
	public int WorkDone(int orderid){
		int result = partTimeOrderDao.WorkDone(orderid);
		return result;
	}
	
	
	/** status = 3 订单状态变为付款成功 带评价
	 * */
	public int WaitComment(int orderid){
		int result = partTimeOrderDao.WaitComment(orderid);
		return result;
	}
	
	
	/** status = 4  订单状态变为彻底结束了
	 * */
	public int Finished(int orderid){
		int result = partTimeOrderDao.Finished(orderid);
		return result;
	}
	
	/** status = 8    发布者单方面对工作人员评价
	 * */
	public int BossComment(int orderid){
		int result = partTimeOrderDao.BossComment(orderid);
		return result;
	}
	
	/** status = 9    发布者单方面对工作人员评价
	 * */
	public int WorkerComment(int orderid){
		int result = partTimeOrderDao.WorkerComment(orderid);
		return result;
	}
	
	
//	/** @param userId orderId
//	 *  当前用户id 和 订单id  判断当前用户是否可以对该订单进行报名  如果已经报过了 就不可以 
//	 *  @return 0可以报名  1已经报名
//	 * */
//	public int CheckEnroll(int userid, int orderid){
//		int result= partTimeOrderDao.CheckEnroll(userid, orderid);
//		return result;
//	}
	
	/** 根据userid 得到发布时候 该用户的平均分  通过单元测试
	 * */
	public float StarByUserId(int UserId){
		float star = partTimeOrderDao.StarByUserId(UserId);
		return star;
	}
	
	/** 兼职 订单
	 * 确认付款
	 * */
	public List<ConfirmPay> ConfirmPay(int orderid){
		List<ConfirmPay> result = partTimeOrderDao.ConfirmPay(orderid);
		return result;
	}
}
