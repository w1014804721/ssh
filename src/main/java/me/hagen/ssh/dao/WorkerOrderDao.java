package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimeRelation;
import me.hagen.ssh.domain.WorkComment;
import me.hagen.ssh.dto.OrderReview;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * 该类用于控制 所有的
 * 
 * 查看我参与的订单
 */
@Repository("workerOrderDao")
// 这个值和service层里的那个对象名字一致
public class WorkerOrderDao extends BaseDao<PartTimeOrder> {

	
/** 工作者是否对自己参加工作的那个订单进行了评价 返回int  1
 * */
	public int CommentToPublisher(int userid,int WorkId){
		Session session = this.currentSession();
		// 是否对参与工作的订单进行了评价  type = 0 userid = 数据库中的publisherid  pto.getid = 数据库中的WorkId
		String sqlString = "select * from work_comment where PublisherId =? and Type = 0 and WorkId = "+WorkId;
		@SuppressWarnings("unchecked")
		List<WorkComment> wc = ((SQLQuery) session.createSQLQuery(sqlString)
				.setParameter(0, userid)).addEntity(WorkComment.class)
				.list();	
		int CommentToPublisher = 0;
		if (wc == null || wc.isEmpty()){
			CommentToPublisher = 1; //没有评价
		}else{ 
			CommentToPublisher = 2;  //已经评价过了
		}
		return CommentToPublisher;
	}
	
	
	/**
	 * 展示全部
	 * */
	public List<OrderReview> ShowAll(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
				
				// 是否对参与工作的订单进行了评价  type = 0 userid = 数据库中的publisherid  pto.getid = 数据库中的WorkId
				String sqlString = "select * from work_comment where PublisherId =? and Type = 0 and WorkId = "+pto.getId();
				@SuppressWarnings("unchecked")
				List<WorkComment> wc = ((SQLQuery) session.createSQLQuery(sqlString)
						.setParameter(0, userid)).addEntity(WorkComment.class)
						.list();	
				int CommentToPublisher = 0;
				if (wc == null || wc.isEmpty()){
					CommentToPublisher = 1; //没有评价
				}else{ 
					CommentToPublisher = 2;  //已经评价过了
				}
				
				OrderReview or = new OrderReview();
				or.setOrderid(pto.getId());
				or.setTitle(pto.getName());
				or.setPay(pto.getPay());
				or.setStatus(pto.getStatus());
				or.setDetail(pto.getDetail());
				or.setEndInTime(pto.getEndInTime().toString());
				or.setCurrentNumber(pto.getCurrentNumber());
				or.setRequiredNumber(pto.getRequiredNumber());
				or.setCommentToPublisher(CommentToPublisher);
				result.add(or);
			}
		}

		return result;
	}

	/**
	 * 展示 带评价的
	 * */
	public List<OrderReview> NoComment(int userid) {
	
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =? and Hired = 1";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();
	
		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
				if (pto.getStatus() == 3) {
					// 是否对参与工作的订单进行了评价  type = 0 userid = 数据库中的publisherid  pto.getid = 数据库中的WorkId
					String sqlString = "select * from work_comment where PublisherId =? and Type = 0 and WorkId = "+pto.getId();
					@SuppressWarnings("unchecked")
					List<WorkComment> wc = ((SQLQuery) session.createSQLQuery(sqlString)
							.setParameter(0, userid)).addEntity(WorkComment.class)
							.list();	
					int CommentToPublisher = 0;
					if (wc == null || wc.isEmpty()){
						CommentToPublisher = 1; //没有评价
					}else{ 
						CommentToPublisher = 2;  //已经评价过了
					}
					
					
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getEndInTime().toString());
					or.setCurrentNumber(pto.getCurrentNumber());
					or.setRequiredNumber(pto.getRequiredNumber());
					or.setCommentToPublisher(CommentToPublisher);
					System.out.println(wc.toString());
					System.out.println(CommentToPublisher);
					result.add(or);
				} 
			}
		}
	
		return result;
	}

	/**
	 * 展示 已报名的
	 * */
	public List<OrderReview> Enroll(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =? and Hired = 0";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
				
				if (pto.getStatus() == 0) {
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getEndInTime().toString());
					or.setCurrentNumber(pto.getCurrentNumber());
					or.setRequiredNumber(pto.getRequiredNumber());
					result.add(or);
				} 
			}
		}

		return result;
	}

	/**
	 * 展示 正在进行的
	 * */
	public List<OrderReview> OnGoing(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =? and Hired = 1";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty()) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!1");
			return null;
		} else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
				System.out.println("???????/" + pto.getStatus());
				if (pto.getStatus() == 1) {
					System.out.println("222222222222222222222");
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getEndInTime().toString());
					or.setCurrentNumber(pto.getCurrentNumber());
					or.setRequiredNumber(pto.getRequiredNumber());
					System.out.println("!3333333333333333333333333");
					result.add(or);

				} 
			}
		}
		System.out.println(result.size());
		return result;
	}

	/**
	 * 展示 未收款的
	 * */
	public List<OrderReview> NoPay(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =? and Hired = 1";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
				if (pto.getStatus() == 2) {
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getEndInTime().toString());
					or.setCurrentNumber(pto.getCurrentNumber());
					or.setRequiredNumber(pto.getRequiredNumber());
					result.add(or);
				} 
			}
		}

		return result;
	}

	/**
	 * 展示 已完成
	 * */
	public List<OrderReview> Finished(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =? and Hired = 1";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
				if (pto.getStatus() == 4) {
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getEndInTime().toString());
					or.setCurrentNumber(pto.getCurrentNumber());
					or.setRequiredNumber(pto.getRequiredNumber());
					result.add(or);
				} 
			}
		}

		return result;
	}
	
	/**
	 * 展示 被拒绝录用的订单
	 * */
	public List<OrderReview> Rejected(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_relation where UserId =? and Hired = 2";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(PartTimeRelation.class)
				.list();

		if (ptr == null || ptr.isEmpty())
			return null;
		else {
			for (PartTimeRelation partTimeRelation : ptr) {
				PartTimeOrder pto = (PartTimeOrder) session.get(
						PartTimeOrder.class, partTimeRelation.getOrderId());
			
					OrderReview or = new OrderReview();
					or.setOrderid(pto.getId());
					or.setTitle(pto.getName());
					or.setPay(pto.getPay());
					or.setStatus(pto.getStatus());
					or.setDetail(pto.getDetail());
					or.setEndInTime(pto.getEndInTime().toString());
					or.setCurrentNumber(pto.getCurrentNumber());
					or.setRequiredNumber(0);
					result.add(or);
				
			}
		}

		return result;
	}
}
