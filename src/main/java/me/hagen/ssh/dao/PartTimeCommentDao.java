package me.hagen.ssh.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import me.hagen.ssh.domain.AverageStar;
import me.hagen.ssh.domain.PartTimeOrder;
import me.hagen.ssh.domain.PartTimePicture;
import me.hagen.ssh.domain.RunComment;
import me.hagen.ssh.domain.WorkComment;
import me.hagen.ssh.domain.WorkCommentPicture;

@Repository("partTimeCommentDao")
public class PartTimeCommentDao extends BaseDao<WorkComment> {
	
	/** 通过userid 展示真名
	 * */
	public String getTrueName(int userid){
		Session session = this.currentSession();
		String trueName = (String) session
				.createQuery(
						"select a.TrueName from UserInfo a where a.UserId=?")
				.setParameter(0, userid).uniqueResult();
		return trueName;
	}
	
	
	
	/**  查看作为发布者 受到的所有评价   (兼职部分)
	 *   
	 * */
	public List<WorkComment> WCommentAsBoss(int userid){
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<WorkComment> result = session
				.createQuery("select wc from WorkComment wc where wc.UserId=? and wc.Type=0")
				.setParameter(0, userid).list();
		if(result ==null||result.isEmpty()){
			return null;
		}else{
			return result;
		}
	} 
	
	/**  查看作为发布者 受到的所有评价   (跑腿部分)
	 *  
	 * */
	public List<RunComment> RCommentAsBoss(int userid){
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunComment> result = session
				.createQuery("select rc from RunComment rc where rc.UserId=? and rc.Type=0")
				.setParameter(0, userid).list();
		if(result ==null||result.isEmpty()){
			return null;
		}else{
			return result;
		}
	} 
	
	/** 查看作为工作者参与别人工作 收到的评价   (兼职部分)
	 *  传入参数userid = 数据库中的userid 并且type = 1
	 * */
	public List<WorkComment> WCommentAsWorker(int userid){
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<WorkComment> result = session
				.createQuery("select wc from WorkComment wc where wc.UserId=? and wc.Type=1")
				.setParameter(0, userid).list();
		if(result ==null||result.isEmpty()){
			return null;
		}else{
			return result;
		}
	}
	
	/** 查看作为工作者参与别人工作 收到的评价   (跑腿部分)
	 *  传入参数userid = 数据库中的userid 并且type = 1
	 * */
	public List<RunComment> RCommentAsWorker(int userid){
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<RunComment> result = session
				.createQuery("select rc from RunComment rc where rc.UserId=? and rc.Type=1")
				.setParameter(0, userid).list();
		if(result ==null||result.isEmpty()){
			return null;
		}else{
			return result;
		}
	}
	
	
	/**  获得  两个平均的信誉度
	 * */
	public AverageStar getStar(int userid){
		Session session = this.currentSession();
		@SuppressWarnings("unchecked")
		List<AverageStar> result = session.createQuery("select a from AverageStar a where a.UserId = ?")
				.setParameter(0, userid).list();
		if(result ==null||result.isEmpty()){
			return null;
		}else{
			return result.get(0);
		}
	}
	
	
	
	/** 根据orderid  去得到所属的图片的名字
	 * 把名字放在一个String的list里面返回
	 * 通过单元测试
	 * */
	public ArrayList<String> getImgName(int orderId){
		ArrayList<String> result = new ArrayList<String>();
		
		Session session = this.currentSession();
		String sql = "select * from work_comment_picture where WorkCommentId=?";
		
		@SuppressWarnings("unchecked")
		List<WorkCommentPicture> PTP= ((SQLQuery) session.createSQLQuery(sql)
				.setParameter(0, orderId)).addEntity(WorkCommentPicture.class).list();
		
		if (PTP == null || PTP.isEmpty())
			return result;
		else {
			for (WorkCommentPicture ptp : PTP) {
				System.out.println(ptp.getImgName());
				result.add(ptp.getImgName());
			}
		}
		
		return result;
	}
}
