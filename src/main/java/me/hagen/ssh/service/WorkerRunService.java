package me.hagen.ssh.service;

import java.util.List;

import me.hagen.ssh.dao.WorkerOrderDao;
import me.hagen.ssh.dao.WorkerRunDao;
import me.hagen.ssh.dto.OrderReview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("WorkerRunService")   //出了问题恢复这里
@Transactional
public class WorkerRunService {
	@Autowired
	private WorkerRunDao workerRunDao;
	

	/**展示全部
	 * */
	public  List<OrderReview> ShowAll(int userid){
		List<OrderReview> result = workerRunDao.ShowAll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示  已报名的
	 * */
	public  List<OrderReview> Enroll(int userid){
		List<OrderReview> result = workerRunDao.Enroll(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示   正在进行的
	 * */
	public  List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = workerRunDao.OnGoing(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示  未收款的
	 * */
	public  List<OrderReview> NoPay(int userid){
		List<OrderReview> result = workerRunDao.NoPay(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	
	/**
	 * 展示 带评价的
	 * */
	public  List<OrderReview> NoComment(int userid){
		List<OrderReview> result = workerRunDao.NoComment(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
	/**
	 * 展示 已完成的
	 * */
	public  List<OrderReview> Finished(int userid){
		List<OrderReview> result = workerRunDao.Finished(userid);
		if(result ==null || result.isEmpty()) return null;
		return result;
	}
}
