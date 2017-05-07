package me.hagen.ssh.action;

import java.util.List;

import me.hagen.ssh.dto.OrderReview;
import me.hagen.ssh.service.WorkerOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("WorkerOrderAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class WorkerOrderAction  extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3983518474960606296L;
	@Autowired
	WorkerOrderService WorkerOrderService;
	
	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	/**չʾȫ��
	 * */
	public String ShowAll(){
		List<OrderReview> result = WorkerOrderService.ShowAll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ  �ѱ�����
	 * */
	public String Enroll(){
		List<OrderReview> result = WorkerOrderService.Enroll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ   ���ڽ��е�
	 * */
	public String OnGoing(){
		List<OrderReview> result = WorkerOrderService.OnGoing(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ   δ�տ��
	 * */
	public String NoPay(){
		List<OrderReview> result = WorkerOrderService.NoPay(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ    �����۵�
	 * */
	public String NoComment(){
		List<OrderReview> result = WorkerOrderService.NoComment(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ    ����ɵ�
	 * */
	public String Finished(){
		List<OrderReview> result = WorkerOrderService.Finished(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ   ���ܾ���
	 * */
	public String Rejected(){
		List<OrderReview> result = WorkerOrderService.Rejected(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
}
