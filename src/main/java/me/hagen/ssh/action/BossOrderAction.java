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

	/**չʾȫ��
	 * */
	public String ShowAll(){
		List<OrderReview> result = BossOrderService.ShowAll(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	
	/**չʾ������Աδȷ����
	 * */
	public String UnChosen(){
		List<OrderReview> result = BossOrderService.UnChosen(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ���ڽ��е�
	 * */
	public String OnGoing(){
		List<OrderReview> result = BossOrderService.OnGoing(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ����ɵ��� û�и����
	 * */
	public String NoPay(){
		List<OrderReview> result = BossOrderService.NoPay(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**չʾ����δ���۵�
	 * */
	public String NoComment(){
		List<OrderReview> result = BossOrderService.NoComment(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**��������� ���۽���
	 * */
	public String Finished(){
		List<OrderReview> result = BossOrderService.Finished(userid);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/** ��ʾ����
	 * */
	public String ShowDetail() {
		Map<String, Object> resultMap =BossOrderService.showDetail(orderid);
		return (info(resultMap));
	}
	
	/** ��ʾ����
	 *  δ���� �ҷ����ļ�ֱ����
	 * */
	public String ShowHired() {
		Map<String, Object> resultMap =BossOrderService.showHired(orderid);
		return (info(resultMap));
	}
	
	
	/** ��ʾ����  û�б����ߵ�
	 * */
	public String onlyDetail() {
		Map<String, Object> resultMap =BossOrderService.onlyDetail(orderid,userid);
		return (info(resultMap));
	}
}
