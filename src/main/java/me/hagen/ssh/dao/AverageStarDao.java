package me.hagen.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import me.hagen.ssh.domain.AverageStar;

@Repository("averageStarDao")
public class AverageStarDao extends BaseDao<AverageStar> {
	/**
	 * 根据userid type（0发布者  1工作者）mark(0兼职  1跑腿) 算出用户的最新平均得分 并且更新到 average-star表中 
	 * 四个方法  
	 * 00兼职发布者 
	 * 01兼职工作者
	 * 10跑腿发布者
	 * 11跑腿工作者
	 * */
	 //通过单元测试
	public int updateAverageStar00(int userid){
		int total=0;
		int averageStar;
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<Integer> starList = session.createQuery("select wc.Star from WorkComment wc where wc.UserId=? and wc.Type=0")
				.setParameter(0, userid)
				.list();
		for(int goal:starList){
			total = total+goal;
		}
		averageStar = total/starList.size();
		int c = session.createSQLQuery("update average_star set PTPublisher=? where UserId=?")
				.setParameter(0, averageStar)
				.setParameter(1, userid)
				.executeUpdate();
		//System.out.println(c);
		return c;   //c=1是正常的  0说明有问题
	}
	
	public int updateAverageStar01(int userid){
		int total=0;
		int averageStar;
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<Integer> starList = session.createQuery("select wc.Star from WorkComment wc where wc.UserId=? and wc.Type=1")
				.setParameter(0, userid)
				.list();
		for(int goal:starList){
			total = total+goal;
		}
		averageStar = total/starList.size();
		int c = session.createSQLQuery("update average_star set PTWorker=? where UserId=?")
				.setParameter(0, averageStar)
				.setParameter(1, userid)
				.executeUpdate();
		//System.out.println(c);
		return c;   //c=1是正常的  0说明有问题
	}
	
	public int updateAverageStar10(int userid){
		int total=0;
		int averageStar;
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<Integer> starList = session.createQuery("select rc.Star from RunComment rc where rc.UserId=? and rc.Type=0")
				.setParameter(0, userid)
				.list();
		for(int goal:starList){
			total = total+goal;
		}
		averageStar = total/starList.size();
		int c = session.createSQLQuery("update average_star set RunPublisher=? where UserId=?")
				.setParameter(0, averageStar)
				.setParameter(1, userid)
				.executeUpdate();
		//System.out.println(c);
		return c;   //c=1是正常的  0说明有问题
	}
	
	public int updateAverageStar11(int userid){
		int total=0;
		int averageStar;
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<Integer> starList = session.createQuery("select rc.Star from RunComment rc where rc.UserId=? and rc.Type=1")
				.setParameter(0, userid)
				.list();
		for(int goal:starList){
			total = total+goal;
		}
		averageStar = total/starList.size();
		int c = session.createSQLQuery("update average_star set RunWorker=? where UserId=?")
				.setParameter(0, averageStar)
				.setParameter(1, userid)
				.executeUpdate();
		//System.out.println(c);
		return c;   //c=1是正常的  0说明有问题
	}
}
