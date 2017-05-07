package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.AverageStar;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimeRelation;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.stereotype.Repository;

import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;

import me.hagen.ssh.dto.*;

@Repository("partTimeOrderDao")   //这个值和service层里的那个对象名字一致
public class PartTimeOrderDao extends BaseDao<PartTimeOrder> {
	
	/** 根据userid 得到发布时候 该用户的平均分  通过单元测试
	 * */
	public float StarByUserId(int UserId){
		float star;
		Session session = this.currentSession();
		
		 star = ((Number) session.createQuery("select a.PTPublisher from average_star a where a.UserId=?").setParameter(0, UserId)).floatValue();
		System.out.println(star);
		return star;
	}
	
	/**
	 * 刷新到主页时 看一些兼职的简略信息 条数可改 通过单元测试 该方法也就是 查看最新发布的订单 传入参数是第几次刷新 time从0开始
	 * 
	 * 这一条语句性能很一般 以后可以优化
	 * */
	public List<BriefPartTimeOrder> showMainOrder(int time) {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		String sql = "select * from part_time_order where Status = 0 ORDER BY id desc LIMIT "
				+ time + ",10";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = session.createSQLQuery(sql)
				.addEntity(PartTimeOrder.class).list();

		if (brief == null || brief.isEmpty())
			return null;
		else {

			for (PartTimeOrder pto : brief) {
				BriefPartTimeOrder bpto = new BriefPartTimeOrder();
				bpto.setOrderId(pto.getId());
				bpto.setCurrentNumber(pto.getCurrentNumber());
				bpto.setCutoffTime(pto.getCutoffTime().toString());
				bpto.setDetail(pto.getDetail());
				bpto.setLocation(pto.getLocation());
				bpto.setName(pto.getName());
				bpto.setRequiredNumber(pto.getRequiredNumber());
				bpto.setStartTime(pto.getStartTime().toString());
				bpto.setPrice(pto.getPay());
				result.add(bpto);
			}
		}

		return result;
	}

	
	
	
	
	/**
	 * 按价格排序 从高到低 查看订单的详细信息
	 * */
	public List<BriefPartTimeOrder> showByPay(int time) {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = session
				.createSQLQuery(
						"select * from part_time_order where status = 0 ORDER BY Pay desc LIMIT "
								+ time + ",10").addEntity(PartTimeOrder.class)
				.list();
		if (brief == null || brief.isEmpty())
			return null;
		else {

			for (PartTimeOrder pto : brief) {
				BriefPartTimeOrder bpto = new BriefPartTimeOrder();
				bpto.setOrderId(pto.getId());
				bpto.setCurrentNumber(pto.getCurrentNumber());
				bpto.setCutoffTime(pto.getCutoffTime().toString());
				bpto.setDetail(pto.getDetail());
				bpto.setLocation(pto.getLocation());
				bpto.setName(pto.getName());
				bpto.setRequiredNumber(pto.getRequiredNumber());
				bpto.setStartTime(pto.getStartTime().toString());
				bpto.setPrice(pto.getPay());
				result.add(bpto);
			}
		}

		return result;
	}

	
	
	
	
	/**
	 * 按距离由近到远 查看订单的详细信息  通过单元测试
	 * */
	public List<BriefPartTimeOrder> showByDistance(int time, float lon,float lat) {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		Session session = this.currentSession();
		// 千米 这个是范围
		int radius = 5;
		
		SpatialContext geo = SpatialContext.GEO;
		
		Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(
				geo.makePoint(lon, lat), radius * DistanceUtils.KM_TO_DEG, geo,
				null);
		
//		System.out.println(rectangle.getMinX() + "-" + rectangle.getMaxX());// 经度范围
//		System.out.println(rectangle.getMinY() + "-" + rectangle.getMaxY());// 纬度范围
		
		String sql = "select * from part_time_order WHERE status = 0 and (Latitude BETWEEN "+rectangle.getMinY()+" AND "+rectangle.getMaxY()+") AND (Longitude BETWEEN "+rectangle.getMinX()+" AND "+rectangle.getMaxX()+") LIMIT "
				+ time + ",10";
		
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = session.createSQLQuery(sql)
				.addEntity(PartTimeOrder.class).list();
		
		if (brief == null || brief.isEmpty())
			return null;
		else {

			for (PartTimeOrder pto : brief) {
				BriefPartTimeOrder bpto = new BriefPartTimeOrder();
				bpto.setOrderId(pto.getId());
				bpto.setCurrentNumber(pto.getCurrentNumber());
				bpto.setCutoffTime(pto.getCutoffTime().toString());
				bpto.setDetail(pto.getDetail());
				bpto.setLocation(pto.getLocation());
				bpto.setName(pto.getName());
				bpto.setRequiredNumber(pto.getRequiredNumber());
				bpto.setStartTime(pto.getStartTime().toString());
				bpto.setPrice(pto.getPay());
				result.add(bpto);
			}
			}
		return result;
	}

	/**
	 * 按发布时的信誉度排序  通过单元测试
	 * */
	 public List<BriefPartTimeOrder> showByStar(int time) {
		 List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
			Session session = this.currentSession();
			@SuppressWarnings("unchecked")
			List<PartTimeOrder> brief = session
					.createSQLQuery(
							"select * from part_time_order where status = 0 ORDER BY AverageStar LIMIT "
									+ time + ",10").addEntity(PartTimeOrder.class)
					.list();
			if (brief == null || brief.isEmpty())
				return null;
			else {
				for (PartTimeOrder pto : brief) {
					BriefPartTimeOrder bpto = new BriefPartTimeOrder();
					bpto.setOrderId(pto.getId());
					bpto.setCurrentNumber(pto.getCurrentNumber());
					bpto.setCutoffTime(pto.getCutoffTime().toString());
					bpto.setDetail(pto.getDetail());
					bpto.setLocation(pto.getLocation());
					bpto.setName(pto.getName());
					bpto.setRequiredNumber(pto.getRequiredNumber());
					bpto.setStartTime(pto.getStartTime().toString());
					bpto.setPrice(pto.getPay());
					result.add(bpto);
				}
			}
			return result;
	 }
	

	
	
	
	
	/**
	 * 根据关键字搜索找出相应的订单信息 返回一个简略信息   通过单元测试
	 * */
	public List<BriefPartTimeOrder> ShowByKeyword(String Keyword,int time) {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		String sql = "SELECT * FROM part_time_order WHERE name REGEXP '"
				+ Keyword + "'"+" LIMIT "
				+ time + ",10";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = session
				.createSQLQuery(
						sql).addEntity(PartTimeOrder.class)
				.list();
		System.out.println(sql);
		if (brief == null || brief.isEmpty())
			return null;
		else {
	
			for (PartTimeOrder pto : brief) {
				BriefPartTimeOrder bpto = new BriefPartTimeOrder();
				bpto.setOrderId(pto.getId());
				bpto.setCurrentNumber(pto.getCurrentNumber());
				bpto.setCutoffTime(pto.getCutoffTime().toString());
				bpto.setDetail(pto.getDetail());
				bpto.setLocation(pto.getLocation());
				bpto.setName(pto.getName());
				bpto.setRequiredNumber(pto.getRequiredNumber());
				bpto.setStartTime(pto.getStartTime().toString());
				bpto.setPrice(pto.getPay());
				result.add(bpto);
			}
		}
		return result;
	}





	/**
	 * 查看具体某一个详细信息 传入这个订单的id 得到一个具体的对象 通过单元测试
	 * */
	public PartTimeOrder ShowDetail(int id) {

		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> result = session
				.createQuery("select pto from PartTimeOrder pto where pto.id=?")
				.setParameter(0, id).list();
		if (result == null || result.isEmpty())
			return null;
		else {
			return result.get(0);
		}
	}

	
	
	/** 随机给出四个 相关的订单
	 * */
	public List<BriefPartTimeOrder> ShowRelative() {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		String sql = "SELECT * FROM part_time_order WHERE Status = 0 and Id >= ((SELECT MAX(Id) FROM part_time_order)-(SELECT MIN(Id) FROM part_time_order)) * RAND() + (SELECT MIN(Id) FROM part_time_order) limit 4;";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = session
				.createSQLQuery(
						sql).addEntity(PartTimeOrder.class)
				.list();
		if (brief == null || brief.isEmpty())
			return null;
		else {
	
			for (PartTimeOrder pto : brief) {
				BriefPartTimeOrder bpto = new BriefPartTimeOrder();
				bpto.setOrderId(pto.getId());
				bpto.setCurrentNumber(pto.getCurrentNumber());
				bpto.setCutoffTime(pto.getCutoffTime().toString());
				bpto.setDetail(pto.getDetail());
				bpto.setLocation(pto.getLocation());
				bpto.setName(pto.getName());
				bpto.setRequiredNumber(pto.getRequiredNumber());
				bpto.setStartTime(pto.getStartTime().toString());
				bpto.setPrice(pto.getPay());
				result.add(bpto);
			}
		}
		return result;
	}

	
	
	
	/**  根据小分类 显示相对应的 同一种类别的订单
	 *  @param time 刷新次数
	 *  @param Cid 小分类数据库里对应的id值
	 *  通过Junit测试
	 * */
	public List<BriefPartTimeOrder> ShowOneCategory(int time,String Cid) {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		String sql = "select * from part_time_order where Cid = "+Cid+" LIMIT "
				+ time + ",10";
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<PartTimeOrder> brief = session
				.createSQLQuery(
						sql).addEntity(PartTimeOrder.class)
				.list();
		if (brief == null || brief.isEmpty())
			return null;
		else {
	
			for (PartTimeOrder pto : brief) {
				BriefPartTimeOrder bpto = new BriefPartTimeOrder();
				bpto.setOrderId(pto.getId());
				bpto.setCurrentNumber(pto.getCurrentNumber());
				bpto.setCutoffTime(pto.getCutoffTime().toString());
				bpto.setDetail(pto.getDetail());
				bpto.setLocation(pto.getLocation());
				bpto.setName(pto.getName());
				bpto.setRequiredNumber(pto.getRequiredNumber());
				bpto.setStartTime(pto.getStartTime().toString());
				bpto.setPrice(pto.getPay());
				result.add(bpto);
			}
		}
		return result;
	}

	/**
	 * 根据id 删除某条订单 通过单元测试  -1失败 2成功 1没有该数据
	 * */
	public int DeletePartTimeOrder(int id) {
		int result = -1;
		Session session = this.currentSession();
		PartTimeOrder pto = (PartTimeOrder) session
				.get(PartTimeOrder.class, id);
		if (pto == null)
			result = 1;
		else {
			String sql = "delete from part_time_relation where OrderId =?";
			result = session.createSQLQuery(sql).setParameter(0, id).executeUpdate();
			session.delete(pto);
			result = 2;
			System.out.println("ccccccccccccccccccccccccccc"+result);
		}
		return result;
	}
	
	/**
	 * @param OrderId Userid
	 * 
	 * 去part_time_order表中 找当前用户是否已经报名过 
	 * 
	 * @return 0未报名     1报名了
	 * 通过Junit
	 * */
	public int IfEnroll(int userid,int orderid){
		int result = -1;
		Session session = this.currentSession();
		String sql = "select * from part_time_relation where UserId =? and OrderId = ?";
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, userid)).setParameter(1, orderid)).addEntity(PartTimeRelation.class)
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
	
	
	
	/**  报名接口 
	 * 	
	 *  用同步 锁定这个方法中的操作
	 *  
	 *  让查询余量和插入part_time_relation 一步成型 都被锁在一起 
	 *  
	 *  
	 *  首先取出 需要人数和现有人数 进行比较之后  如果已经相等了 就返回报名失败
	 *  可以报名的话 就往part_time_relation中插入一条记录   
	 *  part_time_order中的CurrentNumber 字段因为有触发器 会自动完成统计和更新
	 *  
	 *  @param orderId 报名订单的id  userid 当前用户也就是报名者的id
	 *  @return 只有返回1 是成功
	 * */
	public int Enroll(int orderId,int userId){
		synchronized(PartTimeOrderDao.class){
			int result = -1;
			Session session = this.currentSession();
			
			PartTimeOrder pto = (PartTimeOrder) session.get(PartTimeOrder.class, orderId);
			if(pto == null){
				result = 0;
			}
			if(pto.getCurrentNumber()<pto.getRequiredNumber()){
				//可以报名
				String sqlString = "insert into part_time_relation (OrderId,UserId,Hired) VALUES (?,?,?) ";
				result = session.createSQLQuery(sqlString).setParameter(0, orderId).setParameter(1, userId).setParameter(2, 0).executeUpdate();
				System.out.println("baituobaituobaituobaituo!!!!!!!"+result);
			}
			return result;
		}
	}
	
//	/** @param userId orderId
//	 *  当前用户id 和 订单id  判断当前用户是否可以对该订单进行报名  如果已经报过了 就不可以 
//	 *  @return 0可以报名  1已经报名
//	 * */
//	public int CheckEnroll(int userid, int orderid){
//		int result = -1;
//		String hql = "select ptr from PartTimeRelation ptr where ptr.OrderId = ? and ptr.UserId = ?";
//		Session session = this.currentSession();
//		PartTimeRelation ptr = (PartTimeRelation) session.createQuery(hql).setParameter(0, orderid).setParameter(1, userid).uniqueResult();
//		if(ptr == null){
//			result = 0; //可以报名 当前用户未对该订单报名
//		}else{
//			result = 1;// 该用户已经报名过该订单
//		}
//		return result;
//	}
	
	
	/**
	 *  录用接口
	 * */
	public int Hire(int orderId,int userId){
		synchronized(PartTimeOrderDao.class){
			
			int result = -1;
			Session session = this.currentSession();
			String sql = "update part_time_relation set Hired = 1 where OrderId = "+orderId+" and UserId ="+userId;
			result = session.createSQLQuery(sql).executeUpdate();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!"+result);
			return result;
		}
		
	}
	
	/** 将剩余所有人拒绝
	 * */
	public int Reject(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_relation set Hired = 2 where OrderId = ? and Hired = 0";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		System.out.println("!!!!!!!!!!!!!"+result);
		return result;
	}
	
	
	/**  雇主 发现兼职已经完成了  
	 *   点击确认完成    订单完成 但是没有付款  进入付款流程
	 * */
	public int WorkDone(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 2,RequiredNumber = -1 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	
	/**  确认付款之后 变成带评价  status变为3
	 * */
	public int WaitComment(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 3 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	
	/** 评价成功之后  该订单彻底结束  status变为4
	 * */
	public int Finished(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 4 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	/** 发布者单方面对工作人员评价
	 *  status 变成8 
	 * */
	public int BossComment(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 8 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	/** 工作者单方面对发布者进行评价
	 *  status 变成9 
	 * */
	public int WorkerComment(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 9 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	
	/** 兼职 订单
	 * 确认付款
	 * */
	public List<ConfirmPay> ConfirmPay(int orderid){
		List<ConfirmPay> result = new ArrayList<ConfirmPay>();
		Session session = this.currentSession();
		String sql = "select * from part_time_relation where OrderId = ? and Hired = 1";
		@SuppressWarnings("unchecked")
		List<PartTimeRelation> ptr = ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, orderid)).addEntity(PartTimeRelation.class)
				.list();
		if(ptr==null||ptr.isEmpty()){
			return null;
		}else{
			for(PartTimeRelation p:ptr){
				ConfirmPay cp = new ConfirmPay();
				int userid = p.getUserId();
				
				String pay =(String) session
						.createQuery(
								"select a.Pay from PartTimeOrder a where a.id=?")
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
}
