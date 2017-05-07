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

@Repository("partTimeOrderDao")   //���ֵ��service������Ǹ���������һ��
public class PartTimeOrderDao extends BaseDao<PartTimeOrder> {
	
	/** ����userid �õ�����ʱ�� ���û���ƽ����  ͨ����Ԫ����
	 * */
	public float StarByUserId(int UserId){
		float star;
		Session session = this.currentSession();
		
		 star = ((Number) session.createQuery("select a.PTPublisher from average_star a where a.UserId=?").setParameter(0, UserId)).floatValue();
		System.out.println(star);
		return star;
	}
	
	/**
	 * ˢ�µ���ҳʱ ��һЩ��ְ�ļ�����Ϣ �����ɸ� ͨ����Ԫ���� �÷���Ҳ���� �鿴���·����Ķ��� ��������ǵڼ���ˢ�� time��0��ʼ
	 * 
	 * ��һ��������ܺ�һ�� �Ժ�����Ż�
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
	 * ���۸����� �Ӹߵ��� �鿴��������ϸ��Ϣ
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
	 * �������ɽ���Զ �鿴��������ϸ��Ϣ  ͨ����Ԫ����
	 * */
	public List<BriefPartTimeOrder> showByDistance(int time, float lon,float lat) {
		List<BriefPartTimeOrder> result = new ArrayList<BriefPartTimeOrder>();
		Session session = this.currentSession();
		// ǧ�� ����Ƿ�Χ
		int radius = 5;
		
		SpatialContext geo = SpatialContext.GEO;
		
		Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(
				geo.makePoint(lon, lat), radius * DistanceUtils.KM_TO_DEG, geo,
				null);
		
//		System.out.println(rectangle.getMinX() + "-" + rectangle.getMaxX());// ���ȷ�Χ
//		System.out.println(rectangle.getMinY() + "-" + rectangle.getMaxY());// γ�ȷ�Χ
		
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
	 * ������ʱ������������  ͨ����Ԫ����
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
	 * ���ݹؼ��������ҳ���Ӧ�Ķ�����Ϣ ����һ��������Ϣ   ͨ����Ԫ����
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
	 * �鿴����ĳһ����ϸ��Ϣ �������������id �õ�һ������Ķ��� ͨ����Ԫ����
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

	
	
	/** ��������ĸ� ��صĶ���
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

	
	
	
	/**  ����С���� ��ʾ���Ӧ�� ͬһ�����Ķ���
	 *  @param time ˢ�´���
	 *  @param Cid С�������ݿ����Ӧ��idֵ
	 *  ͨ��Junit����
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
	 * ����id ɾ��ĳ������ ͨ����Ԫ����  -1ʧ�� 2�ɹ� 1û�и�����
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
	 * ȥpart_time_order���� �ҵ�ǰ�û��Ƿ��Ѿ������� 
	 * 
	 * @return 0δ����     1������
	 * ͨ��Junit
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
			result = 0; //δ�ѵ���� ��ʾδ����
		}else{
			result = 1;
		}
		System.out.println("}}}}}}}}}}}}}}}}}}}}}"+result);
		return result;
	}
	
	
	
	/**  �����ӿ� 
	 * 	
	 *  ��ͬ�� ������������еĲ���
	 *  
	 *  �ò�ѯ�����Ͳ���part_time_relation һ������ ��������һ�� 
	 *  
	 *  
	 *  ����ȡ�� ��Ҫ�������������� ���бȽ�֮��  ����Ѿ������ �ͷ��ر���ʧ��
	 *  ���Ա����Ļ� ����part_time_relation�в���һ����¼   
	 *  part_time_order�е�CurrentNumber �ֶ���Ϊ�д����� ���Զ����ͳ�ƺ͸���
	 *  
	 *  @param orderId ����������id  userid ��ǰ�û�Ҳ���Ǳ����ߵ�id
	 *  @return ֻ�з���1 �ǳɹ�
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
				//���Ա���
				String sqlString = "insert into part_time_relation (OrderId,UserId,Hired) VALUES (?,?,?) ";
				result = session.createSQLQuery(sqlString).setParameter(0, orderId).setParameter(1, userId).setParameter(2, 0).executeUpdate();
				System.out.println("baituobaituobaituobaituo!!!!!!!"+result);
			}
			return result;
		}
	}
	
//	/** @param userId orderId
//	 *  ��ǰ�û�id �� ����id  �жϵ�ǰ�û��Ƿ���ԶԸö������б���  ����Ѿ������� �Ͳ����� 
//	 *  @return 0���Ա���  1�Ѿ�����
//	 * */
//	public int CheckEnroll(int userid, int orderid){
//		int result = -1;
//		String hql = "select ptr from PartTimeRelation ptr where ptr.OrderId = ? and ptr.UserId = ?";
//		Session session = this.currentSession();
//		PartTimeRelation ptr = (PartTimeRelation) session.createQuery(hql).setParameter(0, orderid).setParameter(1, userid).uniqueResult();
//		if(ptr == null){
//			result = 0; //���Ա��� ��ǰ�û�δ�Ըö�������
//		}else{
//			result = 1;// ���û��Ѿ��������ö���
//		}
//		return result;
//	}
	
	
	/**
	 *  ¼�ýӿ�
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
	
	/** ��ʣ�������˾ܾ�
	 * */
	public int Reject(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_relation set Hired = 2 where OrderId = ? and Hired = 0";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		System.out.println("!!!!!!!!!!!!!"+result);
		return result;
	}
	
	
	/**  ���� ���ּ�ְ�Ѿ������  
	 *   ���ȷ�����    ������� ����û�и���  ���븶������
	 * */
	public int WorkDone(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 2,RequiredNumber = -1 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	
	/**  ȷ�ϸ���֮�� ��ɴ�����  status��Ϊ3
	 * */
	public int WaitComment(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 3 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	
	/** ���۳ɹ�֮��  �ö������׽���  status��Ϊ4
	 * */
	public int Finished(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 4 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	/** �����ߵ�����Թ�����Ա����
	 *  status ���8 
	 * */
	public int BossComment(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 8 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	/** �����ߵ�����Է����߽�������
	 *  status ���9 
	 * */
	public int WorkerComment(int orderid){
		Session  session = this.currentSession();
		int result = -1;
		String sql = "update part_time_order set Status = 9 where Id = ?";
		result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
		return result;
	}
	
	
	/** ��ְ ����
	 * ȷ�ϸ���
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
