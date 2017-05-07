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
	 * �����µ�������Ϣ 
	 * @return ���ش�����һ��������id�ɹ� 
	 *   ���ֵ���ǳ���һЩ��ֵ�����
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
		roo.setAverageStar(4);//����һ������userid��õ�ǰ�����ȵķ���);
		System.out.println("33333333333");
		runoffOrderDao.create(roo);
		result = roo.getId();
		return result;
	}
	
	
	/**
	 * �鿴����ĳһ����ϸ��Ϣ ����orderid userid �õ�һ��Map    
	 * */
	public Map<String, Object> showDetail(int orderid,int Userid) {
		Map<String, Object> temp = new HashMap<String, Object>();
		List<BriefRunOrder> result= runoffOrderDao.ShowRelative();
		RunOffOrder roo = runoffOrderDao.ShowDetail(orderid);
		int ifEnroll = runoffOrderDao.IfEnroll(Userid, orderid);
		
		if (roo == null)
			return temp;
		else {
			ArrayList<String> imgNameArrayList = RunPictureDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
	        int userid = roo.getUserId();
			String name = roo.getName();
			String price = roo.getPay();
			float Longitude = roo.getLongitude();
			float Latitude = roo.getLatitude();
			Timestamp ReleaseTime1 = roo.getReleaseTime();
			String ReleaseTime = ReleaseTime1.toString();
			String username = userDao.getTrueName(userid);// ��������ʵ����
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
			temp.put("content", content);  //���渽�� ��������Ƽ�
			temp.put("ImgName", imgNameArrayList);// ͼƬ����
			temp.put("relative", result);
			return temp;
		}

	}
	
	
	
	/**
	 * ˢ�µ���ҳʱ ��һЩ��ְ�ļ�����Ϣ  ����ֵ��һ�������˹涨��Ŀdto��list
	 *
	 * */
	public List<BriefRunOrder> showLatest(int time) {
		List<BriefRunOrder> result= runoffOrderDao.showLatest(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * ���ռ۸��ָߵ��� չʾ��Ϣ  ����ֵ��һ�������˹涨��Ŀdto��list
	 *
	 * */
	public List<BriefRunOrder> showByPay(int time) {
		List<BriefRunOrder> result= runoffOrderDao.showByPay(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * ������鿴
	 *
	 * */
	public List<BriefRunOrder> showByDistance(int time,float longitude,float latitude) {
		List<BriefRunOrder> result= runoffOrderDao.showByDistance(time,longitude,latitude);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * �������Ȳ鿴
	 *
	 * */
	public List<BriefRunOrder> showByStar(int time) {
		List<BriefRunOrder> result= runoffOrderDao.showByStar(time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**����userid ɾ��ĳһ������ -1ʧ��0�ɹ�
	 * */
	public int Delete(int id){
		int result = runoffOrderDao.DeleteRunOrder(id);
		return result;
	}
	
	/**
	 * ���ؼ���ƥ��ɸѡ
	 *
	 * */
	public List<BriefRunOrder> showByKeyWord(String key,int time) {
		List<BriefRunOrder> result= runoffOrderDao.ShowByKeyword(key,time);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/** �����ӿ�
	 *  @return ֻ�з���1 �ǳɹ�
	 * */
	public int Enroll(int orderId,int userId){
		int result = runoffOrderDao.Enroll(orderId, userId);
		return result;
	}
	
	/** ���� ����
	 * ȷ�ϸ���
	 * */
	public List<ConfirmPay> ConfirmPay(int orderid){
		List<ConfirmPay> result = runoffOrderDao.ConfirmPay(orderid);
		return result;
	}
	
	
	/**���� ���ּ�ְ�Ѿ������  
	 *   ���ȷ�����    ������� ����û�и���  ���븶������
	 * */
	public int WorkDone(int orderid){
		int result = runoffOrderDao.WorkDone(orderid);
		return result;
	}
	
	
	/** status = 3 ����״̬��Ϊ����ɹ� ������
	 * */
	public int WaitComment(int orderid){
		int result = runoffOrderDao.WaitComment(orderid);
		return result;
	}
	
	
	/** status = 4  ����״̬��Ϊ���׽�����
	 * */
	public int Finished(int orderid){
		int result = runoffOrderDao.Finished(orderid);
		return result;
	}
	
	/** status = 8 ����״̬��Ϊ���׽�����
	 * */
	public int BossComment(int orderid){
		int result = runoffOrderDao.BossComment(orderid);
		return result;
	}
	
	/** status = 9 ����״̬��Ϊ���׽�����
	 * */
	public int WorkerComment(int orderid){
		int result = runoffOrderDao.WorkerComment(orderid);
		return result;
	}
	
}
