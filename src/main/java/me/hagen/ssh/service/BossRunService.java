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

@Service("BossRunService")   //��������ָ�����
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
	
	/**չʾȫ��
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = bossRunDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	

	/**չʾ������Աδȷ����
	 * */
	public  List<OrderReview> UnChosen(int userid){
		List<OrderReview> result = bossRunDao.UnChosen(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**չʾ���ڽ��е�
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = bossRunDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**չʾ����ɵ��� û�и����
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = bossRunDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**չʾ����δ���۵�
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = bossRunDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	

	
	/**��������� ���۽���
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = bossRunDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	
	/**
	 * �鿴����ĳһ����ϸ��Ϣ ����orderid userid �õ�һ��Map    
	 * 
	 * �ޱ�������Ϣ
	 * */
	public Map<String, Object> showDetail(int orderid) {
		Map<String, Object> temp = new HashMap<String, Object>();
		RunOffOrder roo = runoffOrderDao.ShowDetail(orderid);
		int publisherId = roo.getUserId();//�����ߵ�userid
		
		
		if(roo == null) 
			return temp;
		else {
			ArrayList<String> imgNameArrayList = RunPictureDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
			String publisherName = bossRunDao.GetTrueName(publisherId); //����������
			String publisherPhone = bossRunDao.PublisherPhone(publisherId);//�������ֻ���
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
			temp.put("content", content);  //���渽�� ��������Ƽ�
			temp.put("ImgName", imgNameArrayList);// ͼƬ����
			temp.put("publisherId", publisherId);
			return temp;
		}
		
	}
	
	
		/** ���� �� ��������Ϣ
		 * */
		public Map<String, Object> DetailWithEnroll(int orderid) {
			Map<String, Object> temp = new HashMap<String, Object>();
			RunOffOrder roo = runoffOrderDao.ShowDetail(orderid);
			List<EnRolledMan> EnrolledMam = bossRunDao.enRolledMan(orderid);
			int publisherId = roo.getUserId();//�����ߵ�userid
			
			if(roo == null) 
				return temp;
			else {
				ArrayList<String> imgNameArrayList = RunPictureDao.getImgName(orderid);//ȡ�ö�ӦͼƬ������
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
				temp.put("content", content);  //���渽�� ��������Ƽ�
				temp.put("ImgName", imgNameArrayList);// ͼƬ����
				temp.put("publisherId", publisherId);
				return temp;
			}
	}
}
