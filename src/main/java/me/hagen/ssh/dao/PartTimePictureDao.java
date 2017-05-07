package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimePicture;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("partTimePictureDao")   //这个值和service层里的那个对象名字一致
public class PartTimePictureDao extends BaseDao<PartTimePicture> {
	
	
	/** 根据orderid  去得到所属的图片的名字
	 * 把名字放在一个String的list里面返回
	 * 通过单元测试
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
