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
	
	
	/**展示全部
	 * */
	public String ShowAll(){
		List<OrderReview> result = WorkerOrderService.ShowAll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示  已报名的
	 * */
	public String Enroll(){
		List<OrderReview> result = WorkerOrderService.Enroll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示   正在进行的
	 * */
	public String OnGoing(){
		List<OrderReview> result = WorkerOrderService.OnGoing(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示   未收款的
	 * */
	public String NoPay(){
		List<OrderReview> result = WorkerOrderService.NoPay(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示    带评价的
	 * */
	public String NoComment(){
		List<OrderReview> result = WorkerOrderService.NoComment(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示    已完成的
	 * */
	public String Finished(){
		List<OrderReview> result = WorkerOrderService.Finished(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示   被拒绝的
	 * */
	public String Rejected(){
		List<OrderReview> result = WorkerOrderService.Rejected(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
}
