package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import me.hagen.ssh.domain.RunCommentPicture;
import me.hagen.ssh.domain.WorkCommentPicture;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository("runCommentPictureDao")   //���ֵ��service������Ǹ���������һ��
public class RunCommentPictureDao extends BaseDao<RunCommentPicture> {
	/** ����orderid  ȥ�õ�������ͼƬ������
	 * �����ַ���һ��String��list���淵��
	 * ͨ����Ԫ����
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
