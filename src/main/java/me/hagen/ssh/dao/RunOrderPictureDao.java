package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import me.hagen.ssh.domain.PartTimePicture;
import me.hagen.ssh.domain.Runpicture;
@Repository("RunPictureDao")   //���ֵ��service������Ǹ���������һ��
public class RunOrderPictureDao extends BaseDao<Runpicture>  {

	
	/** ����orderid  ȥ�õ�������ͼƬ������
	 * �����ַ���һ��String��list���淵��
	 * ͨ����Ԫ����
	 * */
	public ArrayList<String> getImgName(int orderId){
		ArrayList<String> result = new ArrayList<String>();
		
		Session session = this.currentSession();
		String sql = "select * from run_picture where OrderId=?";
		
		@SuppressWarnings("unchecked")
		List<Runpicture> rp= ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, orderId)).addEntity(Runpicture.class).list();
		
		if (rp == null || rp.isEmpty())
			return null;
		else {
			for (Runpicture ptp : rp) {
				System.out.println(ptp.getImgName());
				result.add(ptp.getImgName());
			}
		}
		
		return result;
	}

}
