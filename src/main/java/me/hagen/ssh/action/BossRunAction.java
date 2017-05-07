package me.hagen.ssh.action;

import java.util.List;
import java.util.Map;

import me.hagen.ssh.dto.OrderReview;
import me.hagen.ssh.service.BossRunService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("BossRunAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class BossRunAction extends AbstractAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1879385585261235251L;

	@Autowired
	private BossRunService BossRunService;
	
	private int userid;
	private int orderid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	/**չʾȫ��
	 * */
	public String ShowAll(){
		List<OrderReview> result = BossRunService.ShowAll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ������Աδȷ����
	 * */
	public String UnChosen(){
		List<OrderReview> result = BossRunService.UnChosen(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ���ڽ��е�
	 * */
	public String OnGoing(){
		List<OrderReview> result = BossRunService.OnGoing(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ����ɵ��� û�и����
	 * */
	public String NoPay(){
		List<OrderReview> result = BossRunService.NoPay(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ����δ���۵�
	 * */
	public String NoComment(){
		List<OrderReview> result = BossRunService.NoComment(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**��������� ���۽���
	 * */
	public String Finished(){
		List<OrderReview> result = BossRunService.Finished(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	/** չʾ����
	 * */
	public String ShowDetail(){
		Map<String, Object>  result = BossRunService.showDetail(orderid);
		return (info(result));
	}
	
	/** չʾ����
	 * */
	public String DetailWithEnroll(){
		Map<String, Object>  result = BossRunService.DetailWithEnroll(orderid);
		return (info(result));
	}
	
}
