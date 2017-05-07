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
	
	
	
	
	/**  �鿴��Ϊ������ �ܵ����������� 
	 *   ����Ĳ���userid = ���ݿ��е�publisherid ����typeֵ����0
	 * */
	public String CommentAsBoss(){
		List<Comment> result = PartTimeCommentService.CommentAsBoss(userid);
		return  info(result);
	}

	
	/** �鿴��Ϊ�����߲�����˹��� �յ�������
	 *  �������userid = ���ݿ��е�userid ����type = 1
	 * */
	public String CommentAsWorker(){
		List<Comment> result = PartTimeCommentService.CommentAsWorker(userid);
		return  info(result);
	}
	
	
	/**  ���  ����ƽ����������
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
