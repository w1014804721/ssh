package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.RunOffOrder;
import me.hagen.ssh.domain.UserInfo;
import me.hagen.ssh.dto.EnRolledMan;
import me.hagen.ssh.dto.OrderReview;
import me.hagen.ssh.dto.Publisher;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("bossRunDao")
// 这个值和service层里的那个对象名字一致
public class BossRunDAO extends BaseDao<RunOffOrder> {

	/**
	 * 展示全部 通过Junit
	 * */
	public List<OrderReview> ShowAll(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_order where UserId =?";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0,userid)).addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (RunOffOrder pto : brief) {
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
	 * 展示工作人员未确定的
	 * */
	public List<OrderReview> UnChosen(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_order where UserId =? and Status =0";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (RunOffOrder pto : brief) {
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
	 * 展示正在进行的
	 * */
	public List<OrderReview> OnGoing(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_order where UserId =? and Status =1";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (RunOffOrder pto : brief) {
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
	 * 展示已完成但是 没有付款的
	 * */
	public List<OrderReview> NoPay(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_order where UserId =? and Status =2";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (RunOffOrder pto : brief) {
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
	
	/** 付款未评价的
	 * */
	public List<OrderReview> NoComment(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_order where UserId =? and Status = 3";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (RunOffOrder pto : brief) {
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
	
	/** 彻底完成了 评价结束
	 * */
	public List<OrderReview> Finished(int userid) {

		List<OrderReview> result = new ArrayList<OrderReview>();
		String sql = "select * from run_off_order where UserId =? and Status =4";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
			for (RunOffOrder pto : brief) {
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
	
	
	
	
	/** 发布者 或者  报名人的真名
	 * */
	public String GetTrueName(int userid){
		Session session = this.currentSession();
		String trueNameString = (String) session.createQuery("select u.TrueName from UserInfo u where u.UserId =?")
				.setParameter(0, userid).uniqueResult();
		return trueNameString;
	}
	
	/** 发布者手机号
	 * */
	public String PublisherPhone(int userid){
		Session session = this.currentSession();
		String phoneString = (String) session.createQuery("select u.phoneNumber from User u where u.id =?")
				.setParameter(0, userid).uniqueResult();
		return phoneString;
	}
	

	
	/**
	 * 综合的报名者信息
	 * */
	public List<EnRolledMan> enRolledMan(int orderid){
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<Integer> result = (List<Integer>) session.createQuery("select u.UserId from RunOffRelation u where u.OrderId = ?")
				.setParameter(0, orderid).list();
		if (result ==null || result.isEmpty()) {
			return null;
		}else {
			List<EnRolledMan> en = new ArrayList<EnRolledMan>();
			int enrolledId = result.get(0);
			String trueNameString = (String) session.createQuery("select u.TrueName from UserInfo u where u.UserId =?")
					.setParameter(0, enrolledId).uniqueResult();
			float star = ((Number) session
					.createQuery(
							"select a.RunWorker from AverageStar a where a.UserId=?")
					.setParameter(0, enrolledId).uniqueResult()).floatValue();
			String HeadImg = (String) session.createQuery("select u.HeadImage from UserInfo u where u.UserId =?")
					.setParameter(0, enrolledId).uniqueResult();
			EnRolledMan enRolledMan = new EnRolledMan();
			enRolledMan.setTrueName(trueNameString);
			enRolledMan.setAverageStar(star);
			enRolledMan.setHeadImg(HeadImg);
			enRolledMan.setUserid(enrolledId);
			en.add(enRolledMan);
			return en;
		}
	}
	
}
