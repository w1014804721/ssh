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
	 * �鿴����ĳһ����ϸ��Ϣ ����������ȵ�id �õ�һ������Ķ��� ͨ����Ԫ����
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
	
	

	/** ����userid �õ�����ʱ�� ���û���ƽ����  ͨ����Ԫ����
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
	
	
	/** ��������ĸ� ��ص�����
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
	 * ˢ�µ���ҳʱ ��һЩ��ְ�ļ�����Ϣ �����ɸ� ͨ����Ԫ���� �÷���Ҳ���� �鿴���·����Ķ��� ��������ǵڼ���ˢ�� time��0��ʼ
	 * 
	 * ��һ��������ܺ�һ�� �Ժ�����Ż�
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
	 * ���۸����� �Ӹߵ��� �鿴��������ϸ��Ϣ
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
	 * ������ʱ������������  ͨ����Ԫ����
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
		 * �������ɽ���Զ �鿴��������ϸ��Ϣ  ͨ����Ԫ����
		 * */
		public List<BriefRunOrder> showByDistance(int time, float lon,float lat) {
			
			// ǧ�� ����Ƿ�Χ
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
		 * ���ݹؼ��������ҳ���Ӧ�Ķ�����Ϣ ����һ��������Ϣ   ͨ����Ԫ����
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
		 * ����id ɾ��ĳ������ ͨ����Ԫ����  -1ʧ�� 2�ɹ� 1û�и�����
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

	 
		/** �����ӿ�
		 * @return ֻ�з���Ϊ1 �ǳɹ�������
		 * */
		public int Enroll(int orderId,int userId){
			synchronized(RunoffOrderDao.class){
				int result = -1;
				Session session = this.currentSession();
				
				RunOffOrder roo = (RunOffOrder) session.get(RunOffOrder.class, orderId);
				if(roo == null) result = 0;
				if(roo.getStatus()==0){
					//���Ա���
					String sqlString = "insert into run_off_relation (OrderId,UserId,Hired) VALUES (?,?,?) ";
					result = session.createSQLQuery(sqlString).setParameter(0, orderId).setParameter(1, userId).setParameter(2, 0).executeUpdate();
				}
				return result;
			}
		}
		
		/**
		 * @param OrderId Userid
		 * 
		 * ȥrun_off_order���� �ҵ�ǰ�û��Ƿ��Ѿ������� 
		 * 
		 * @return 0δ����     1������
		 * ͨ��Junit
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
				result = 0; //δ�ѵ���� ��ʾδ����
			}else{
				result = 1;
			}
			System.out.println("}}}}}}}}}}}}}}}}}}}}}"+result);
			return result;
		}
		
		
		/** ��ְ ����
		 * ȷ�ϸ���
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
		
		/**  ���� ���������Ѿ������  
		 *   ���ȷ�����    ������� ����û�и���  ���븶������
		 * */
		public int WorkDone(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 2 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		
		/**  ȷ�ϸ���֮�� ��ɴ�����  status��Ϊ3
		 * */
		public int WaitComment(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 3 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		
		/** ���۳ɹ�֮��  �ö������׽���  status��Ϊ4
		 * */
		public int Finished(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 4 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		/** �����߶Թ���������  status��Ϊ8
		 * */
		public int BossComment(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 8 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
		
		
		/** �����߶Է���������  status��Ϊ9
		 * */
		public int WorkerComment(int orderid){
			Session  session = this.currentSession();
			int result = -1;
			String sql = "update run_off_order set Status = 9 where Id = ?";
			result = session.createSQLQuery(sql).setParameter(0,orderid).executeUpdate();
			return result;
		}
}
