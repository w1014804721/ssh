package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import me.hagen.ssh.domain.PartTimePicture;
import me.hagen.ssh.domain.Runpicture;
@Repository("RunPictureDao")   //这个值和service层里的那个对象名字一致
public class RunOrderPictureDao extends BaseDao<Runpicture>  {

	
	/** 根据orderid  去得到所属的图片的名字
	 * 把名字放在一个String的list里面返回
	 * 通过单元测试
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
