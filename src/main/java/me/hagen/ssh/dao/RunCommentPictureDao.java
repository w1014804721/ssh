package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.RunCommentPicture;
import me.hagen.ssh.domain.WorkCommentPicture;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("runCommentPictureDao")   //这个值和service层里的那个对象名字一致
public class RunCommentPictureDao extends BaseDao<RunCommentPicture> {
	/** 根据orderid  去得到所属的图片的名字
	 * 把名字放在一个String的list里面返回
	 * 通过单元测试
	 * */
	public ArrayList<String> getImgName(int orderId){
		ArrayList<String> result = new ArrayList<String>();
		
		Session session = this.currentSession();
		String sql = "select * from run_comment_picture where CommentId=?";
		
		@SuppressWarnings("unchecked")
		List<RunCommentPicture> PTP= ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, orderId)).addEntity(RunCommentPicture.class).list();
		
		if (PTP == null || PTP.isEmpty())
			return result;
		else {
			for (RunCommentPicture ptp : PTP) {
				System.out.println(ptp.getImgName());
				result.add(ptp.getImgName());
			}
		}
		
		return result;
	}
}
