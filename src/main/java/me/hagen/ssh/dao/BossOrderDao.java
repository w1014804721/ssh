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
 * 该类用于控制 所有的        查看我发布的订单 
 */
@Repository("bossOrderDao")   //这个值和service层里的那个对象名字一致
public class BossOrderDao extends BaseDao<PartTimeOrder>{
	
	/** 展示全部  通过Junit
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
	
	/** 展示工作人员未确定的
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
	
	/** 展示正在进行的
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
	
	
	
	/** 展示已完成但是 没有付款的
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
	
	
	/** 付款未评价的
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
	
	/** 彻底完成了 评价结束
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
	 * 查看具体某一个详细信息 传入这个订单的id 得到一个具体的对象 通过单元测试
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
	
	/** 展示相关报名者的信息
	 * 
	 * 通过Junit测试
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
				int EnrolledId = ptr.getUserId(); // 每个报名者的userid
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
	
	
	/** 展示 详情 
	 * 
	 * 该接口给 我发布的订单 已完成但是未评价的使用  
	 * 展示出所有参与工作的人 还有订单的详细信息
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
				int EnrolledId = ptr.getUserId(); // 每个报名者的userid
				int IfCommented = -1;
				
				//type= 1 enrolledid = 数据库表中的Userid  orderid = WorkId
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
				eMan.setIfCommented(IfCommented);  //是否被评价
				people.add(eMan);
				
			}
		}
		return people;
	}

}
