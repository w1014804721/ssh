package me.hagen.ssh.action;

import java.util.List;
import java.util.Map;

import me.hagen.ssh.dto.Comment;
import me.hagen.ssh.service.PartTimeCommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("PartTimeCommentAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class PartTimeCommentAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9046508242536283556L;
	
	@Autowired
	private PartTimeCommentService PartTimeCommentService;
	
	private int userid;
	
	
	
	
	/**  查看作为发布者 受到的所有评价 
	 *   传入的参数userid = 数据库中的publisherid 并且type值等于0
	 * */
	public String CommentAsBoss(){
		List<Comment> result = PartTimeCommentService.CommentAsBoss(userid);
		return  info(result);
	}

	
	/** 查看作为工作者参与别人工作 收到的评价
	 *  传入参数userid = 数据库中的userid 并且type = 1
	 * */
	public String CommentAsWorker(){
		List<Comment> result = PartTimeCommentService.CommentAsWorker(userid);
		return  info(result);
	}
	
	
	/**  获得  两个平均的信誉度
	 * */
	public String getStar(){
		Map<String, Object> resultMap = PartTimeCommentService.getStar(userid);
		return (info(resultMap));
	}
	
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
}
