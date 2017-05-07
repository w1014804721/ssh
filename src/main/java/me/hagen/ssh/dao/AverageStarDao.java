package me.hagen.ssh.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import me.hagen.ssh.domain.AverageStar;

@Repository("averageStarDao")
public class AverageStarDao extends BaseDao<AverageStar> {
	/**
	 * ����userid type��0������  1�����ߣ�mark(0��ְ  1����) ����û�������ƽ���÷� ���Ҹ��µ� average-star���� 
	 * �ĸ�����  
	 * 00��ְ������ 
	 * 01��ְ������
	 * 10���ȷ�����
	 * 11���ȹ�����
	 * */
	 //ͨ����Ԫ����
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
		return c;   //c=1��������  0˵��������
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
		return c;   //c=1��������  0˵��������
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
		return c;   //c=1��������  0˵��������
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
		return c;   //c=1��������  0˵��������
	}
}
