package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimeRelation;
import me.hagen.ssh.domain.RunOffOrder;
import me.hagen.ssh.domain.RunOffRelation;
import me.hagen.ssh.dto.OrderReview;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("workerRunDao")
//���ֵ��service������Ǹ���������һ��
public class WorkerRunDao extends BaseDao<RunOffOrder>{

	

	/**
	 * չʾȫ��
	 * */
	public List<OrderReview> ShowAll(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffRelation.class)
				.list();
		
		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (RunOffRelation RunOffRelation : ptr) {
				System.out.println("!!!!!!!!!!!!!"+RunOffRelation.getOrderId());
				RunOffOrder pto = (RunOffOrder) session.get(
						RunOffOrder.class, RunOffRelation.getOrderId());
				OrderReview or = new OrderReview();
				or.setRequiredNumber(1);
				or.setOrderid(pto.getId());
				or.setTitle(pto.getName());
				or.setPay(pto.getPay());
				or.setStatus(pto.getStatus());
				or.setDetail(pto.getDetail());
				or.setEndInTime(pto.getFinishTime().toString());
				result.add(or);
			}
		}

		return result;
	}
	
	/**
	 * չʾ �ѱ�����
	 * */
	public List<OrderReview> Enroll(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (RunOffRelation RunOffRelation : ptr) {
				RunOffOrder pto = (RunOffOrder) session.get(
						RunOffOrder.class, RunOffRelation.getOrderId());
				
				if (pto.getStatus() == 0) {
					OrderReview or = new OrderReview();
					or.setRequiredNumber(1);
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getFinishTime().toString());
					result.add(or);
				} 
			}
		}

		return result;
	}
	
	/**
	 * չʾ ���ڽ��е�
	 * */
	public List<OrderReview> OnGoing(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (RunOffRelation RunOffRelation : ptr) {
				RunOffOrder pto = (RunOffOrder) session.get(
						RunOffOrder.class, RunOffRelation.getOrderId());
				
				if (pto.getStatus() == 1) {
					OrderReview or = new OrderReview();
					or.setRequiredNumber(1);
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getFinishTime().toString());
					result.add(or);
				} 
			}
		}

		return result;
	}
	
	/**
	 * չʾ δ�տ��
	 * */
	public List<OrderReview> NoPay(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (RunOffRelation RunOffRelation : ptr) {
				RunOffOrder pto = (RunOffOrder) session.get(
						RunOffOrder.class, RunOffRelation.getOrderId());
				
				if (pto.getStatus() == 2) {
					OrderReview or = new OrderReview();
					or.setRequiredNumber(1);
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getFinishTime().toString());
					result.add(or);
				} 
			}
		}

		return result;
	}
	
	/**
	 * չʾ �����۵�
	 * */
	public List<OrderReview> NoComment(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (RunOffRelation RunOffRelation : ptr) {
				RunOffOrder pto = (RunOffOrder) session.get(
						RunOffOrder.class, RunOffRelation.getOrderId());
				
				if (pto.getStatus() == 3) {
					OrderReview or = new OrderReview();
					or.setRequiredNumber(1);
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getFinishTime().toString());
					result.add(or);
				} 
			}
		}

		return result;
	}
	
	
	/**
	 * չʾ �����
	 * */
	public List<OrderReview> Finished(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (RunOffRelation RunOffRelation : ptr) {
				RunOffOrder pto = (RunOffOrder) session.get(
						RunOffOrder.class, RunOffRelation.getOrderId());
				
				if (pto.getStatus() == 4) {
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setRequiredNumber(1);
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getFinishTime().toString());
					result.add(or);
				} 
			}
		}

		return result;
	}

}
