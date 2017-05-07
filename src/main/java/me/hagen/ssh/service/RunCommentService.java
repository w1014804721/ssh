package me.hagen.ssh.service;

import me.hagen.ssh.dao.AverageStarDao;
import me.hagen.ssh.dao.RunCommentDao;
import me.hagen.ssh.domain.RunComment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("RunCommentService")
@Transactional
public class RunCommentService {
	@Autowired
	private RunCommentDao rcDao;
	@Autowired
	private AverageStarDao averageStarDao;
	
	/** 发布新的 关于跑腿的 评论
	 * 
	 * 通过单元测试
	 * */
	public int pubishNewComment(String content, int runId, int userid,int type,int star, int publisherId){
		RunComment rComment = new RunComment();
		rComment.setContent(content);
		rComment.setRunId(runId);
		rComment.setStar(star);
		rComment.setType(type);
		rComment.setUserId(userid);
		rComment.setPublisherId(publisherId);
		rcDao.create(rComment);
		int result = rComment.getId();
		if(type ==0){
			averageStarDao.updateAverageStar10(userid);
		}
		if(type ==1){
			averageStarDao.updateAverageStar11(userid);
		}
		return result;
	}
}
