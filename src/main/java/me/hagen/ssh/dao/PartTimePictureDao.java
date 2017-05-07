package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimePicture;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("partTimePictureDao")   //���ֵ��service������Ǹ���������һ��
public class PartTimePictureDao extends BaseDao<PartTimePicture> {
	
	
	/** ����orderid  ȥ�õ�������ͼƬ������
	 * �����ַ���һ��String��list���淵��
	 * ͨ����Ԫ����
	 * */
	public ArrayList<String> getImgName(int orderId){
		ArrayList<String> result = new ArrayList<String>();
		
		Session session = this.currentSession();
		String sql = "select * from part_time_picture where OrderId=?";
		
		@SuppressWarnings("unchecked")
		List<PartTimePicture> PTP= ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, orderId)).addEntity(PartTimePicture.class).list();
		
		if (PTP == null || PTP.isEmpty())
			return null;
		else {
			for (PartTimePicture ptp : PTP) {
				System.out.println(ptp.getImgName());
				result.add(ptp.getImgName());
			}
		}
		
		return result;
	}
}
