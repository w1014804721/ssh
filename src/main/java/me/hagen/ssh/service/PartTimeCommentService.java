package me.hagen.ssh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hagen.ssh.dao.AverageStarDao;
import me.hagen.ssh.dao.PartTimeCommentDao;
import me.hagen.ssh.dao.RunCommentPictureDao;
import me.hagen.ssh.domain.AverageStar;
import me.hagen.ssh.domain.RunComment;
import me.hagen.ssh.domain.WorkComment;
import me.hagen.ssh.dto.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("PartTimeCommentService")
@Transactional
public class PartTimeCommentService {
	@Autowired
	private PartTimeCommentDao partTimeCommentDao;
	@Autowired
	private RunCommentPictureDao runCommentPictureDao;
	@Autowired
	private AverageStarDao averageStarDao;
	/**
	 * 发布新的评论 通过单元测试
	 * 更新平均分表
	 * */
	public int pubishNewComment(String content, int workid, int userid,int type,int star, int publisherId){
		WorkComment wc = new WorkComment();
		wc.setContent(content);
		wc.setStar(star);
		wc.setType(type);
		wc.setUserId(userid);
		wc.setWorkId(workid);
		wc.setPublisherId(publisherId);
		partTimeCommentDao.create(wc);
		int result = wc.getId();
		if(type ==0){
			averageStarDao.updateAverageStar00(userid);
		}
		if(type ==1){
			averageStarDao.updateAverageStar01(userid);
		}
		return result;
	}
	
	/**  查看作为发布者 受到的所有评价 
	 *
	 * */
	public List<Comment> CommentAsBoss(int userid){
		List<Comment> result = new ArrayList<Comment>();
		List<WorkComment> r1 = partTimeCommentDao.WCommentAsBoss(userid);
		List<RunComment> r2 = partTimeCommentDao.RCommentAsBoss(userid);
		
		if(r1!=null&&!r1.isEmpty()){
			int i = 0;
			for(WorkComment wc:r1){
				Comment c = new Comment();
				c.setContent(wc.getContent());
				c.setName(partTimeCommentDao.getTrueName(wc.getPublisherId()));
				c.setStar(wc.getStar());
				c.setImgNameArrayList(partTimeCommentDao.getImgName(r1.get(i).getId()));
				c.setPublisherId(wc.getPublisherId());
				i++;
				result.add(c);
			}
		}
		
		if(r2!=null&&!r2.isEmpty()){
			int i = 0;
			for(RunComment rc:r2){
				Comment c = new Comment();
				c.setContent(rc.getContent());
				c.setName(partTimeCommentDao.getTrueName(rc.getPublisherId()));
				c.setStar(rc.getStar());
				c.setImgNameArrayList(runCommentPictureDao.getImgName(r2.get(i).getId()));
				c.setPublisherId(rc.getPublisherId());
				i++;
				result.add(c);
			}
		}
		return result;
	} 
	
	
	/** 查看作为工作者参与别人工作 收到的评价
	 *  
	 * */
	public List<Comment> CommentAsWorker(int userid){
		List<Comment> result = new ArrayList<Comment>();
		List<WorkComment> r1 = partTimeCommentDao.WCommentAsWorker(userid);
		List<RunComment> r2 = partTimeCommentDao.RCommentAsWorker(userid);
		
		if(r1!=null&&!r1.isEmpty()){
			int i = 0;
			for(WorkComment wc:r1){
				Comment c = new Comment();
				c.setContent(wc.getContent());
				c.setName(partTimeCommentDao.getTrueName(wc.getPublisherId()));
				c.setStar(wc.getStar());
				c.setImgNameArrayList(partTimeCommentDao.getImgName(r1.get(i).getId()));
				c.setPublisherId(wc.getPublisherId());
				i++;
				result.add(c);
			}
		}
		
		if(r2!=null&&!r2.isEmpty()){
			int i = 0;
			for(RunComment rc:r2){
				Comment c = new Comment();
				c.setContent(rc.getContent());
				c.setName(partTimeCommentDao.getTrueName(rc.getPublisherId()));
				c.setStar(rc.getStar());
				c.setImgNameArrayList(runCommentPictureDao.getImgName(r2.get(i).getId()));
				System.out.println(r2.get(i).getId());
				System.out.println(runCommentPictureDao.getImgName(r2.get(i).getId()).toString());
				c.setPublisherId(rc.getPublisherId());
				i++;
				result.add(c);
			}
		}
		return result;
	} 
	
	
	/**  获得  两个平均的信誉度
	 * */
	public Map<String, Object> getStar(int userid){
		Map<String, Object> result = new HashMap<String, Object>();
		AverageStar as = partTimeCommentDao.getStar(userid);
		result.put("star1", as.getPTPublisher());
		result.put("star2", as.getPTWorker());
		return result;
		
	}
}
