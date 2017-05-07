package me.hagen.ssh.action;

import java.util.List;

import me.hagen.ssh.dto.OrderReview;
import me.hagen.ssh.service.WorkerRunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("WorkerRunAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class WorkerRunAction  extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6999805615251475300L;

	@Autowired
	private WorkerRunService WorkerRunService;
	
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
		System.out.println("!!!!!!!!!!!!"+userid);
		List<OrderReview> result = WorkerRunService.ShowAll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ  �ѱ�����
	 * */
	public String Enroll(){
		List<OrderReview> result = WorkerRunService.Enroll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ   ���ڽ��е�
	 * */
	public String OnGoing(){
		List<OrderReview> result = WorkerRunService.OnGoing(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ   δ�տ��
	 * */
	public String NoPay(){
		List<OrderReview> result = WorkerRunService.NoPay(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ    �����۵�
	 * */
	public String NoComment(){
		List<OrderReview> result = WorkerRunService.NoComment(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ    ����ɵ�
	 * */
	public String Finished(){
		List<OrderReview> result = WorkerRunService.Finished(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
}
