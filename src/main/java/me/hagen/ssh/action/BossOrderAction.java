package me.hagen.ssh.action;

import java.util.List;
import java.util.Map;

import me.hagen.ssh.dto.Message;
import me.hagen.ssh.dto.OrderReview;
import me.hagen.ssh.service.BossOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("BossOrderAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class BossOrderAction extends AbstractAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4842383569413750115L;
	@Autowired
	private BossOrderService BossOrderService;
	
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

	/**展示全部
	 * */
	public String ShowAll(){
		List<OrderReview> result = BossOrderService.ShowAll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	
	/**展示工作人员未确定的
	 * */
	public String UnChosen(){
		List<OrderReview> result = BossOrderService.UnChosen(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示正在进行的
	 * */
	public String OnGoing(){
		List<OrderReview> result = BossOrderService.OnGoing(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示已完成但是 没有付款的
	 * */
	public String NoPay(){
		List<OrderReview> result = BossOrderService.NoPay(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**展示付款未评价的
	 * */
	public String NoComment(){
		List<OrderReview> result = BossOrderService.NoComment(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**彻底完成了 评价结束
	 * */
	public String Finished(){
		List<OrderReview> result = BossOrderService.Finished(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/** 显示详情
	 * */
	public String ShowDetail() {
		Map<String, Object> resultMap =BossOrderService.showDetail(orderid);
		return (info(resultMap));
	}
	
	/** 显示详情
	 *  未评价 我发布的简直订单
	 * */
	public String ShowHired() {
		Map<String, Object> resultMap =BossOrderService.showHired(orderid);
		return (info(resultMap));
	}
	
	
	/** 显示详情  没有报名者的
	 * */
	public String onlyDetail() {
		Map<String, Object> resultMap =BossOrderService.onlyDetail(orderid,userid);
		return (info(resultMap));
	}
}
