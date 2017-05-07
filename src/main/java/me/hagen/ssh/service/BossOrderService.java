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

@Service("BossOrderService")   //��������ָ�����
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
	
	
	/**չʾȫ��
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = bossOrderDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	

	/**չʾ������Աδȷ����
	 * */
	public  List<OrderReview> UnChosen(int userid){
		List<OrderReview> result = bossOrderDao.UnChosen(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**չʾ���ڽ��е�
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = bossOrderDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**չʾ����ɵ��� û�и����
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = bossOrderDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**չʾ����δ���۵�
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = bossOrderDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	

	
	/**��������� ���۽���
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = bossOrderDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/** չʾ���� 
	 *  ���� ֮ǰ������ �Լ� �������� ���� ÿ���˵�������   ����˵���ʵ����   
	 * */
	public Map<String, Object> showDetail(int orderid){
		Map<String, Object> temp = new HashMap<String, Object>();
		PartTimeOrder pto = bossOrderDao.ShowDetail(orderid);
		List<EnRolledMan> People= bossOrderDao.ShowEnrolled(orderid);
		
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
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
			temp.put("content", content);  //���渽�� ��������Ƽ�
			temp.put("ImgName", imgNameArrayList);
			temp.put("People", People);
		}
		return temp;
	}
	
	
	

	/** չʾ ���� 
	 * 
	 * �ýӿڸ� �ҷ����Ķ��� ����ɵ���δ���۵�ʹ��  
	 * չʾ�����в��빤������ ���ж�������ϸ��Ϣ
	 * */
	public Map<String, Object> showHired(int orderid){
		Map<String, Object> temp = new HashMap<String, Object>();
		PartTimeOrder pto = bossOrderDao.ShowDetail(orderid);
		List<EnRolledMan> People= bossOrderDao.showHired(orderid);
		
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
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
			temp.put("content", content);  //���渽�� ��������Ƽ�
			temp.put("ImgName", imgNameArrayList);
			temp.put("People", People);
		}
		return temp;
	}
	
	
	
	
	
	
	
	/**  û�б����ߵ���ϸ��Ϣ �ṩ�� ��ְģ�� ʣ�µľ���ʹ��
	 * 
	 *  ����Ĳ��� Userid �ǵ�ǰ�û���id  �����ȡ��userid�Ƕ��������ߵ�id
	 * */
	public Map<String, Object> onlyDetail(int orderid,int Userid){
		Map<String, Object> temp = new HashMap<String, Object>();
		PartTimeOrder pto = bossOrderDao.ShowDetail(orderid);
		
		if (pto == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = ptpDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
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
			temp.put("content", content);  //���渽�� ��������Ƽ�
			temp.put("ImgName", imgNameArrayList);
		}
		return temp;
	}
}
