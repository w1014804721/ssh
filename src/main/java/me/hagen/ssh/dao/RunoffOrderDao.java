package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimeRelation;
import me.hagen.ssh.domain.RunOffOrder;
import me.hagen.ssh.domain.RunOffRelation;
import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.BriefRunOrder;
import me.hagen.ssh.dto.ConfirmPay;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;

@Repository("runoffOrderDao")
public class RunoffOrderDao extends BaseDao<RunOffOrder> {

	
	/**
	 * 查看具体某一个详细信息 传入这个跑腿的id 得到一个具体的对象 通过单元测试
	 * */
	public RunOffOrder ShowDetail(int id) {

		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> result = session
				.createQuery("select roo from RunOffOrder roo where roo.id=?")
				.setParameter(0, id).list();
		if (result == null || result.isEmpty())
			return null;
		else {
			return result.get(0);
		}
	}
	
	

	/** 根据userid 得到发布时候 该用户的平均分  通过单元测试
	 * */
	public float StarByUserId(int UserId){
		float star;
		Session session = this.currentSession();
		
		 star = ((Number) session
				.createQuery(
						"select a.Runpublisher from AverageStar a where a.UserId=?")
				.setParameter(0, UserId).uniqueResult()).floatValue();
		System.out.println(star);
		return star;
	}
	
	
	/** 随机给出四个 相关的跑腿
	 * */
	public List<BriefRunOrder> ShowRelative() {
		int hahaha = 0;
		List<BriefRunOrder> result = new ArrayList<BriefRunOrder>();
		String sql = "SELECT * FROM run_off_order WHERE Status = 0 and Id >= ((SELECT MAX(Id) FROM run_off_order)-(SELECT MIN(Id) FROM run_off_order)) * RAND() + (SELECT MIN(Id) FROM run_off_order) limit 4;";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = session
				.createSQLQuery(
						sql).addEntity(RunOffOrder.class)
				.list();
		if (brief == null || brief.isEmpty())
			return null;
		else {
	
			for (RunOffOrder pto : brief) {
				BriefRunOrder bro = new BriefRunOrder();
				hahaha++;
				bro.setOrderId(pto.getId());
				bro.setContent(pto.getDetail());
				bro.setDestination(pto.getDestination());
				bro.setFinishTime(pto.getFinishTime().toString());
				bro.setName(pto.getName());
				bro.setPrice(pto.getPay());
				result.add(bro);
			}
		}
		System.out.println("dddddd"+hahaha);
		return result;
	}
	
	
	
	
	
	/**
	 * 刷新到主页时 看一些兼职的简略信息 条数可改 通过单元测试 该方法也就是 查看最新发布的订单 传入参数是第几次刷新 time从0开始
	 * 
	 * 这一条语句性能很一般 以后可以优化
	 * */
	public List<BriefRunOrder> showLatest(int time) {
		List<BriefRunOrder> result = new ArrayList<BriefRunOrder>();
		String sql = "select * from run_off_order where status = 0 ORDER BY id desc LIMIT "
				+ time + ",10";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = session.createSQLQuery(sql)
				.addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
	
			for (RunOffOrder pto : brief) {
				BriefRunOrder bro = new BriefRunOrder();
				bro.setOrderId(pto.getId());
				bro.setContent(pto.getDetail());
				bro.setDestination(pto.getDestination());
				bro.setFinishTime(pto.getFinishTime().toString());
				bro.setName(pto.getName());
				bro.setPrice(pto.getPay());
				result.add(bro);
			}
		}

		return result;
	}


	
	/**
	 * 按价格排序 从高到低 查看订单的详细信息
	 * */
	public List<BriefRunOrder> showByPay(int time) {
		List<BriefRunOrder> result = new ArrayList<BriefRunOrder>();
		String sql = "select * from run_off_order where status = 0 ORDER BY Pay desc LIMIT "
				+ time + ",10";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunOffOrder> brief = session.createSQLQuery(sql)
				.addEntity(RunOffOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {
	
			for (RunOffOrder pto : brief) {
				BriefRunOrder bro = new BriefRunOrder();
				bro.setOrderId(pto.getId());
				bro.setContent(pto.getDetail());
				bro.setDestination(pto.getDestination());
				bro.setFinishTime(pto.getFinishTime().toString());
				bro.setName(pto.getName());
				bro.setPrice(pto.getPay());
				result.add(bro);
			}
		}

		return result;
	}
	
	
	
	/**
	 * 按发布时的信誉度排序  通过单元测试
	 * */
	 public List<BriefRunOrder> showByStar(int time) {
		 List<BriefRunOrder> result = new ArrayList<BriefRunOrder>();
			String sql = "select * from run_off_order where status = 0 ORDER BY AverageStar LIMIT "
					+ time + ",10";
			Session session = this.currentSession();
			@SuppressWarnings("unchecked")
			List<RunOffOrder> brief = session.createSQLQuery(sql)
					.addEntity(RunOffOrder.class).list();

			if (brief == null || brief.isEmpty())
				return null;
			else {
		
				for (RunOffOrder pto : brief) {
					BriefRunOrder bro = new BriefRunOrder();
					bro.setOrderId(pto.getId());
					bro.setContent(pto.getDetail());
					bro.setDestination(pto.getDestination());
					bro.setFinishTime(pto.getFinishTime().toString());
					bro.setName(pto.getName());
					bro.setPrice(pto.getPay());
					result.add(bro);
				}
			}

			return result;
	 }
	 
	 
	 
		/**
		 * 按距离由近到远 查看订单的详细信息  通过单元测试
		 * */
		public List<BriefRunOrder> showByDistance(int time, float lon,float lat) {
			
			// 千米 这个是范围
			int radius = 5;
			
			SpatialContext geo = SpatialContext.GEO;
			
			Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(
					geo.makePoint(lon, lat), radius * DistanceUtils.KM_TO_DEG, geo,
					null);
			
			
			 List<BriefRunOrder> result = new ArrayList<BriefRunOrder>();
				String sql = "select * from run_off_order WHERE status = 0 and (Latitude BETWEEN "+rectangle.getMinY()+" AND "+rectangle.getMaxY()+") AND (Longitude BETWEEN "+rectangle.getMinX()+" AND "+rectangle.getMaxX()+") LIMIT "
						+ time + ",3";
				Session session = this.currentSession();
				@SuppressWarnings("unchecked")
				List<RunOffOrder> brief = session.createSQLQuery(sql)
						.addEntity(RunOffOrder.class).list();

				if (brief == null || brief.isEmpty())
					return null;
				else {
			
					for (RunOffOrder pto : brief) {
						BriefRunOrder bro = new BriefRunOrder();
						bro.setOrderId(pto.getId());
						bro.setContent(pto.getDetail());
						bro.setDestination(pto.getDestination());
						bro.setFinishTime(pto.getFinishTime().toString());
						bro.setName(pto.getName());
						bro.setPrice(pto.getPay());
						result.add(bro);
					}
				}

				return result;
		}
		
		
		
		
		/**
		 * 根据关键字搜索找出相应的订单信息 返回一个简略信息   通过单元测试
		 * */
		public List<BriefRunOrder> ShowByKeyword(String Keyword,int time) {
			 List<BriefRunOrder> result = new ArrayList<BriefRunOrder>();
				String sql = "SELECT * FROM run_off_order WHERE status = 0 and name REGEXP '"
						+ Keyword + "'"+" LIMIT "
						+ time + ",10";
				Session session = this.currentSession();
				@SuppressWarnings("unchecked")
				List<RunOffOrder> brief = session.createSQLQuery(sql)
						.addEntity(RunOffOrder.class).list();

				if (brief == null || brief.isEmpty())
					return null;
				else {
			
					for (RunOffOrder pto : brief) {
						BriefRunOrder bro = new BriefRunOrder();
						bro.setOrderId(pto.getId());
						bro.setContent(pto.getDetail());
						bro.setDestination(pto.getDestination());
						bro.setFinishTime(pto.getFinishTime().toString());
						bro.setName(pto.getName());
						bro.setPrice(pto.getPay());
						result.add(bro);
					}
				}

				return result;
		}
		
		
		
		/**
		 * 根据id 删除某条订单 通过单元测试  -1失败 2成功 1没有该数据
		 * */
		public int DeleteRunOrder(int id) {
			int result = -1;
			Session session = this.currentSession();
			RunOffOrder roo = (RunOffOrder) session
					.get(RunOffOrder.class, id);
			if (roo == null)
				result = 1;
			else {
				String sql = "delete from run_off_relation where OrderId =?";
				result = session.createSQLQuery(sql).setParameter(0, id).executeUpdate();
				session.delete(roo);
				result = 2;
				System.out.println("ccccccccccccccccccccccccccc"+result);
			}
			return result;
		}

	 
		/** 报名接口
		 * @return 只有返回为1 是成功报名了
		 * */
		public int Enroll(int orderId,int userId){
			synchronized(RunoffOrderDao.class){
				int result = -1;
				Session session = this.currentSession();
				
				RunOffOrder roo = (RunOffOrder) session.get(RunOffOrder.class, orderId);
				if(roo == null) result = 0;
				if(roo.getStatus()==0){
					//可以报名
					String sqlString = "insert into run_off_relation (OrderId,UserId,Hired) VALUES (?,?,?) ";
					result = session.createSQLQuery(sqlString).setParameter(0, orderId).setParameter(1, userId).setParameter(2, 0).executeUpdate();
				}
				return result;
			}
		}
		
		/**
		 * @param OrderId Userid
		 * 
		 * 去run_off_order表中 找当前用户是否已经报名过 
		 * 
		 * @return 0未报名     1报名了
		 * 通过Junit
		 * */
		public int IfEnroll(int userid,int orderid){
			int result = -1;
			Session session = this.currentSession();
			String sql = "select * from run_off_relation where UserId =? and OrderId = ?";
			@SuppressWarnings("unchecked")
			List<RunOffRelation> ptr = ((SQLQuery) ((SQLQuery) session.createSQLQuery(sql)
					.setParameter(0, userid)).setParameter(1, orderid)).addEntity(RunOffRelation.class)
					.list();
			if (ptr == null || ptr.isEmpty())
			{
				result = 0; //未搜到结果 表示未报名
			}else{
				result = 1;
			}
			System.out.println("}}}}}}}}}}}}}}}}}}}}}"+result);
			return result;
		}
		
		
		/** 兼职 订单
		 * 确认付款
		 * */
		public List<ConfirmPay> ConfirmPay(int orderid){
			List<ConfirmPay> result = new ArrayList<ConfirmPay>();
			Session session = this.currentSession();
			String sql = "select * from run_off_relation where OrderId = ?";
			@SuppressWarnings("unchecked")
			List<RunOffRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
					.setParameter(0, orderid)).addEntity(RunOffRelation.class)
					.list();
			if(ptr==null||ptr.isEmpty()){
				return null;
			}else{
				for(RunOffRelation p:ptr){
					ConfirmPay cp = new ConfirmPay();
					int userid = p.getUserId();
					
					String pay =(String) session
							.createQuery(
									"select a.Pay from RunOffOrder a where a.id=?")
							.setParameter(0, orderid).uniqueResult();
					
					String TrueName =  (String) session
							.createQuery(
									"select a.TrueName from UserInfo a where a.UserId=?")
							.setParameter(0, userid).uniqueResult();
					cp.setPay(pay);
					cp.setTrueName(TrueName);
					cp.setUserid(userid);
					result.add(cp);
				}
			}
			return result;
		}
		
		/**  雇主 发现跑腿已经完成了  
		 *   点击确认完成    订单完成 但是没有付款  进入付款流程
		 * */
		public int WorkDone(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 2 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		
		/**  确认付款之后 变成带评价  status变为3
		 * */
		public int WaitComment(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 3 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		
		/** 评价成功之后  该订单彻底结束  status变为4
		 * */
		public int Finished(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 4 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		/** 发布者对工作者评价  status变为8
		 * */
		public int BossComment(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 8 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		
		/** 工作者对发布者评价  status变为9
		 * */
		public int WorkerComment(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 9 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
}
