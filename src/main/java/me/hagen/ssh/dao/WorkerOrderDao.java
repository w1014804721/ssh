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
 * �������ڿ��� ���е�
 * 
 * �鿴�Ҳ���Ķ���
 */
@Repository("workerOrderDao")
// ���ֵ��service������Ǹ���������һ��
public class WorkerOrderDao extends BaseDao<PartTimeOrder> {

	
/** �������Ƿ���Լ��μӹ������Ǹ��������������� ����int  1
 * */
	public int CommentToPublisher(int userid,int WorkId){
		Session session = this.currentSession();
		// �Ƿ�Բ��빤���Ķ�������������  type = 0 userid = ���ݿ��е�publisherid  pto.getid = ���ݿ��е�WorkId
		String sqlString = "select * from work_comment where PublisherId =? and Type = 0 and WorkId = "+WorkId;
		@SuppressWarnings("unchecked")
		List<WorkComment> wc = ((SQLQuery) session.createSQLQuery(sqlString)
				.setParameter(0, userid)).addEntity(WorkComment.class)
				.list();	
		int CommentToPublisher = 0;
		if (wc == null || wc.isEmpty()){
			CommentToPublisher = 1; //û������
		}else{ 
			CommentToPublisher = 2;  //�Ѿ����۹���
		}
		return CommentToPublisher;
	}
	
	
	/**
	 * չʾȫ��
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
				
				// �Ƿ�Բ��빤���Ķ�������������  type = 0 userid = ���ݿ��е�publisherid  pto.getid = ���ݿ��е�WorkId
				String sqlString = "select * from work_comment where PublisherId =? and Type = 0 and WorkId = "+pto.getId();
				@SuppressWarnings("unchecked")
				List<WorkComment> wc = ((SQLQuery) session.createSQLQuery(sqlString)
						.setParameter(0, userid)).addEntity(WorkComment.class)
						.list();	
				int CommentToPublisher = 0;
				if (wc == null || wc.isEmpty()){
					CommentToPublisher = 1; //û������
				}else{ 
					CommentToPublisher = 2;  //�Ѿ����۹���
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
	 * չʾ �����۵�
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
					// �Ƿ�Բ��빤���Ķ�������������  type = 0 userid = ���ݿ��е�publisherid  pto.getid = ���ݿ��е�WorkId
					String sqlString = "select * from work_comment where PublisherId =? and Type = 0 and WorkId = "+pto.getId();
					@SuppressWarnings("unchecked")
					List<WorkComment> wc = ((SQLQuery) session.createSQLQuery(sqlString)
							.setParameter(0, userid)).addEntity(WorkComment.class)
							.list();	
					int CommentToPublisher = 0;
					if (wc == null || wc.isEmpty()){
						CommentToPublisher = 1; //û������
					}else{ 
						CommentToPublisher = 2;  //�Ѿ����۹���
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
	 * չʾ �ѱ�����
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
	 * չʾ ���ڽ��е�
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
	 * չʾ δ�տ��
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
	 * չʾ �����
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
	 * չʾ ���ܾ�¼�õĶ���
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
