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

@Service("ptoService")   //��������ָ�����
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
	 * �����µļ�ְ��Ϣ ͨ����Ԫ����   
	 * 
	 *     @return ����������������ݿ�����һ�����ݵ� ����idֵ
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
		//pto.setAverageStar(partTimeOrderDao.StarByUserId(UserId));//����һ������userid��õ�ǰ�����ȵķ���
		pto.setAverageStar(4);
		partTimeOrderDao.create(pto);
		System.out.println("fuck!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+pto.getId());   //��һ��܌�
		i = pto.getId();
		
		return i;
	}

	/**
	 * ˢ�µ���ҳʱ ��һЩ��ְ�ļ�����Ϣ  ����ֵ��һ�������˹涨��Ŀdto��list
	 *
	 * */
	public List<BriefPartTimeOrder> showMainOrder(int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showMainOrder(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * ���ռ۸��ָߵ��� չʾ��Ϣ  ����ֵ��һ�������˹涨��Ŀdto��list
	 *
	 * */
	public List<BriefPartTimeOrder> showByPay(int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showByPay(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * ������鿴
	 *
	 * */
	public List<BriefPartTimeOrder> showByDistance(int time,float longitude,float latitude) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showByDistance(time,longitude,latitude);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * �������Ȳ鿴
	 *
	 * */
	public List<BriefPartTimeOrder> showByStar(int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.showByStar(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * ���ؼ���ƥ��ɸѡ
	 *
	 * */
	public List<BriefPartTimeOrder> showByKeyWord(String key,int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.ShowByKeyword(key,time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/** 
	 * �鿴ĳһ�������С����� ����������Ϣ
	 * */
	public List<BriefPartTimeOrder> ShowOneCategory(String Cid,int time) {
		List<BriefPartTimeOrder> result= partTimeOrderDao.ShowOneCategory(time, Cid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * �鿴����ĳһ����ϸ��Ϣ ����id �õ�һ��Map    
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
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
			int userid = pto.getUserid();  //�����ߵ�id
			String name = pto.getName();
			float Longitude = pto.getLongitude();
			float Latitude = pto.getLatitude();
			String price = pto.getPay();
			Timestamp ReleaseTime1 = pto.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			Timestamp EndInTime1 = pto.getEndInTime();
			String EndInTime = EndInTime1.toString();
			//String username = "��**";  //��һ����д
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
			temp.put("IfEnroll", ifEnroll);  //0δ���� 1�Ѿ�����
			temp.put("content", content);  //���渽�� ��������Ƽ�
			temp.put("ImgName", imgNameArrayList);
			temp.put("relative", result);//����Ƽ�
			
			return temp;
		}

	}
	
	
	/**����userid ɾ��ĳһ������ -1ʧ��0�ɹ�
	 * */
	public int Delete(int id){
		int result = partTimeOrderDao.DeletePartTimeOrder(id);
		return result;
	}
	
	/** �����ӿ�
	 *  @return ֻ�з���1 �ǳɹ�
	 * */
	public int Enroll(int orderId,int userId){
		int result = partTimeOrderDao.Enroll(orderId, userId);
		return result;
	}
	
	/** ¼��
	 * */
	public int Hire(int orderId,int userId){
		int result = partTimeOrderDao.Hire(orderId, userId);
		return result;
	}
	
	/**  �ܾ��û�����
	 * */
	public int Reject(int orderid){
		int result = partTimeOrderDao.Reject(orderid);
		return result;
	}
	
	/**���� ���ּ�ְ�Ѿ������  
	 *   ���ȷ�����    ������� ����û�и���  ���븶������
	 * */
	public int WorkDone(int orderid){
		int result = partTimeOrderDao.WorkDone(orderid);
		return result;
	}
	
	
	/** status = 3 ����״̬��Ϊ����ɹ� ������
	 * */
	public int WaitComment(int orderid){
		int result = partTimeOrderDao.WaitComment(orderid);
		return result;
	}
	
	
	/** status = 4  ����״̬��Ϊ���׽�����
	 * */
	public int Finished(int orderid){
		int result = partTimeOrderDao.Finished(orderid);
		return result;
	}
	
	/** status = 8    �����ߵ�����Թ�����Ա����
	 * */
	public int BossComment(int orderid){
		int result = partTimeOrderDao.BossComment(orderid);
		return result;
	}
	
	/** status = 9    �����ߵ�����Թ�����Ա����
	 * */
	public int WorkerComment(int orderid){
		int result = partTimeOrderDao.WorkerComment(orderid);
		return result;
	}
	
	
//	/** @param userId orderId
//	 *  ��ǰ�û�id �� ����id  �жϵ�ǰ�û��Ƿ���ԶԸö������б���  ����Ѿ������� �Ͳ����� 
//	 *  @return 0���Ա���  1�Ѿ�����
//	 * */
//	public int CheckEnroll(int userid, int orderid){
//		int result= partTimeOrderDao.CheckEnroll(userid, orderid);
//		return result;
//	}
	
	/** ����userid �õ�����ʱ�� ���û���ƽ����  ͨ����Ԫ����
	 * */
	public float StarByUserId(int UserId){
		float star = partTimeOrderDao.StarByUserId(UserId);
		return star;
	}
	
	/** ��ְ ����
	 * ȷ�ϸ���
	 * */
	public List<ConfirmPay> ConfirmPay(int orderid){
		List<ConfirmPay> result = partTimeOrderDao.ConfirmPay(orderid);
		return result;
	}
}
