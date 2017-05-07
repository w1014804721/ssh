package me.hagen.ssh.service;

import java.util.List;

import me.hagen.ssh.dao.WorkerOrderDao;
import me.hagen.ssh.dto.OrderReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WorkerOrderService")   //��������ָ�����
@Transactional
public class WorkerOrderService {
	@Autowired
	private WorkerOrderDao workerOrderDao;
	

	/**չʾȫ��
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = workerOrderDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * չʾ  �ѱ�����
	 * */
	public  List<OrderReview> Enroll(int userid){
		List<OrderReview> result = workerOrderDao.Enroll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * չʾ   ���ڽ��е�
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = workerOrderDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * չʾ  δ�տ��
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = workerOrderDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * չʾ �����۵�
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = workerOrderDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	/**
	 * չʾ ����ɵ�
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = workerOrderDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * չʾ ���ܾ���
	 * */
	public  List<OrderReview> Rejected(int userid){
		List<OrderReview> result = workerOrderDao.Rejected(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
}
