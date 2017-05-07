package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.AverageStar;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimeRelation;
import me.hagen.ssh.domain.User;
import me.hagen.ssh.domain.WorkComment;
import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.EnRolledMan;
import me.hagen.ssh.dto.OrderReview;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;



/**
 * �������ڿ��� ���е�        �鿴�ҷ����Ķ��� 
 */
@Repository("bossOrderDao")   //���ֵ��service������Ǹ���������һ��
public class BossOrderDao extends BaseDao<PartTimeOrder>{
	
	/** չʾȫ��  ͨ��Junit
	 * */
	public List<OrderReview> ShowAll(int userid){
		
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_order where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = ((SQLQuery) session.createSQLQuery(sql).setParameter(0, userid))
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (PartTimeOrder pto : brief) {
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
		return result;
	}
	
	/** չʾ������Աδȷ����
	 * */
	public List<OrderReview> UnChosen(int userid){
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_order where UserId =? and Status =0";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = ((SQLQuery) session.createSQLQuery(sql).setParameter(0, userid))
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (PartTimeOrder pto : brief) {
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
		return result;
	}
	
	/** չʾ���ڽ��е�
	 * */
	public List<OrderReview> OnGoing(int userid){
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_order where UserId =? and Status =1";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = ((SQLQuery) session.createSQLQuery(sql).setParameter(0, userid))
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (PartTimeOrder pto : brief) {
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
		return result;
	}
	
	
	
	/** չʾ����ɵ��� û�и����
	 * */
	public List<OrderReview> NoPay(int userid){
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_order where UserId =? and Status =2";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = ((SQLQuery) session.createSQLQuery(sql).setParameter(0, userid))
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (PartTimeOrder pto : brief) {
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
		return result;
	}
	
	
	/** ����δ���۵�
	 * */
	public List<OrderReview> NoComment(int userid){
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_order where UserId =? and Status =3";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = ((SQLQuery) session.createSQLQuery(sql).setParameter(0, userid))
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (PartTimeOrder pto : brief) {
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
		return result;
	}
	
	/** ��������� ���۽���
	 * */
	public List<OrderReview> Finished(int userid){
		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from part_time_order where UserId =? and Status =4";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = ((SQLQuery) session.createSQLQuery(sql).setParameter(0, userid))
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (PartTimeOrder pto : brief) {
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
		return result;
	}
	
	
	/**
	 * �鿴����ĳһ����ϸ��Ϣ �������������id �õ�һ������Ķ��� ͨ����Ԫ����
	 * */
	public PartTimeOrder ShowDetail(int orderId) {

		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> result = session
				.createQuery("select pto from PartTimeOrder pto where pto.id=?")
				.setParameter(0, orderId).list();
		if (result == null || result.isEmpty())
			return null;
		else {
			return result.get(0);
		}
	}
	
	/** չʾ��ر����ߵ���Ϣ
	 * 
	 * ͨ��Junit����
	 * */
	public List<EnRolledMan> ShowEnrolled(int orderid){
		List<EnRolledMan> people = new ArrayList<EnRolledMan>();
		Session session = this.currentSession();
		String sqlString = "select * from part_time_relation where OrderId ="+orderid;
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> temp = session.createSQLQuery(sqlString).addEntity(PartTimeRelation.class).list();
				
		if (temp == null || temp.isEmpty())
			return null;
		else {
			for (PartTimeRelation ptr : temp) {
				EnRolledMan eMan = new EnRolledMan();
				int EnrolledId = ptr.getUserId(); // ÿ�������ߵ�userid
				int Hired = ptr.getHired();
				
				float star = ((Number) session
								.createQuery(
										"select a.PTWorker from AverageStar a where a.UserId=?")
								.setParameter(0, EnrolledId).uniqueResult()).floatValue();
				
				String trueName = (String) session
						.createQuery(
								"select a.TrueName from UserInfo a where a.UserId=?")
						.setParameter(0, EnrolledId).uniqueResult();
				
//				String headImg = (String) session
//						.createQuery(
//								"select a.HeadImage from UserInfo a where a.UserId=?")
//						.setParameter(0, EnrolledId).uniqueResult();
//				
				eMan.setUserid(EnrolledId);
				eMan.setAverageStar(star);
				eMan.setTrueName(trueName);
//				eMan.setHeadImg(headImg);
				eMan.setHired(Hired);
				people.add(eMan);
				
			}
		}
		return people;
	}
	
	
	/** չʾ ���� 
	 * 
	 * �ýӿڸ� �ҷ����Ķ��� ����ɵ���δ���۵�ʹ��  
	 * չʾ�����в��빤������ ���ж�������ϸ��Ϣ
	 * */
	public List<EnRolledMan> showHired(int orderid){
		List<EnRolledMan> people = new ArrayList<EnRolledMan>();
		Session session = this.currentSession();
		String sqlString = "select * from part_time_relation where OrderId ="+orderid+" and Hired =1";
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> temp = session.createSQLQuery(sqlString).addEntity(PartTimeRelation.class).list();
				
		if (temp == null || temp.isEmpty())
			return null;
		else {
			for (PartTimeRelation ptr : temp) {
				EnRolledMan eMan = new EnRolledMan();
				int EnrolledId = ptr.getUserId(); // ÿ�������ߵ�userid
				int IfCommented = -1;
				
				//type= 1 enrolledid = ���ݿ���е�Userid  orderid = WorkId
				String sql = "select * from work_comment where UserId ="+EnrolledId+" and Type =1 and WorkId = "+orderid;		
				@SuppressWarnings("unchecked")
				List<WorkComment> wc = session.createSQLQuery(sql).addEntity(WorkComment.class).list();
				if(wc ==null||wc.isEmpty()){
					IfCommented = 1;
				}else{
					IfCommented = 2;
				}
				
				
				float star = ((Number) session
								.createQuery(
										"select a.PTWorker from AverageStar a where a.UserId=?")
								.setParameter(0, EnrolledId).uniqueResult()).floatValue();
				
				String trueName = (String) session
						.createQuery(
								"select a.TrueName from UserInfo a where a.UserId=?")
						.setParameter(0, EnrolledId).uniqueResult();
				
				String headImg = (String) session
						.createQuery(
								"select a.HeadImage from UserInfo a where a.UserId=?")
						.setParameter(0, EnrolledId).uniqueResult();
				
				eMan.setUserid(EnrolledId);
				eMan.setAverageStar(star);
				eMan.setTrueName(trueName);
				eMan.setHeadImg(headImg);
				eMan.setIfCommented(IfCommented);  //�Ƿ�����
				people.add(eMan);
				
			}
		}
		return people;
	}

}
