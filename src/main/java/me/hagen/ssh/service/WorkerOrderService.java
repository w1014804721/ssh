package me.hagen.ssh.service;

import java.util.List;

import me.hagen.ssh.dao.WorkerOrderDao;
import me.hagen.ssh.dto.OrderReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WorkerOrderService")   //出了问题恢复这里
@Transactional
public class WorkerOrderService {
	@Autowired
	private WorkerOrderDao workerOrderDao;
	

	/**展示全部
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = workerOrderDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示  已报名的
	 * */
	public  List<OrderReview> Enroll(int userid){
		List<OrderReview> result = workerOrderDao.Enroll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示   正在进行的
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = workerOrderDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示  未收款的
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = workerOrderDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示 带评价的
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = workerOrderDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	/**
	 * 展示 已完成的
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = workerOrderDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示 被拒绝的
	 * */
	public  List<OrderReview> Rejected(int userid){
		List<OrderReview> result = workerOrderDao.Rejected(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
}
