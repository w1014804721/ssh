package me.hagen.ssh.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.BriefRunOrder;
import me.hagen.ssh.dto.ConfirmPay;
import me.hagen.ssh.dto.Message;
import me.hagen.ssh.service.PartTimeOrderService;
import me.hagen.ssh.service.RunoffOrderService;
import me.hagen.ssh.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("RunOffOrderAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class RunOffOrderAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2103195216054342194L;
	@Autowired
	RunoffOrderService RunOffOrderService;
	@Autowired
	UserService userService;
	@Autowired
	PartTimeOrderService partTimeOrderService;
	private int userid;
	private int time;
	private float longitude;
	private float latitude;
	private String keyWord;
	private int partId;
	private int orderId;
	
	
	/**
	 * չʾĳ������������Ϣ   ���ں��渽����������Ƽ�
	 * */
	public String ShowDetail() {
		return (info(RunOffOrderService.showDetail(orderId,userid)));
	}

	/**x
	 * Ĭ����ȥ���ݿ����·����Ķ��� ������ȥ�Ĵ���
	 * */
	public String ShowLatest(){
		List<BriefRunOrder> result = RunOffOrderService.showLatest(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	/**
	 * ������ ������ȡ���� ��ʹ��ʱ�ľ�γ��
	 * */
	public String ShowByDistance(){
		List<BriefRunOrder> result = RunOffOrderService.showByDistance(time, longitude, latitude);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * ���۸��ɸߵ���չʾ ��ְ��Ϣ ������ȡ����
	 * */
	public String ShowByPay(){
		List<BriefRunOrder> result = RunOffOrderService.showByPay(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * �������Ȳ鿴
	 *
	 * */
	public String ShowByStar(){
		List<BriefRunOrder> result = RunOffOrderService.showByStar(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	/**
	 * ���ؼ���ƥ��չʾ ��ְ��Ϣ ������ȡ�����͹ؼ���
	 * @throws UnsupportedEncodingException 
	 * */
	public String ShowByKey() throws UnsupportedEncodingException{
		System.out.println("999999999999999999999"+keyWord);
		String k= URLDecoder.decode(keyWord,"utf-8") ;
		System.out.println("88888888888888888888"+k);
		List<BriefRunOrder> result = RunOffOrderService.showByKeyWord(k, time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * ɾ������
	 * */
	public String DeleteOrder(){
		int i =RunOffOrderService.Delete(partId);
		if(i==2) return info(Message.message[0]);
		else return info(Message.message[18]);
	}
	
	/** �����ӿ�
	 *  @return ֻ�з���1 �ǳɹ�
	 * */
	public String Enroll(){
		int i = RunOffOrderService.Enroll(orderId, userid);
		if (i==1) {
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);	
		}
	}
	
	
	/**  �����ɹ�֮�� 
	 * 	�û����Կ����Լ����������������Ϣ�����ߵ���Ϣ
	 * */
	public String ShowBossInfo(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String TrueName = userService.getTrueName(userid);
		float star = partTimeOrderService.StarByUserId(userid);
		String phoneNumber = userService.getPhone(userid);
		resultMap.put("TrueName", TrueName);
		resultMap.put("star", star);
		resultMap.put("phoneNumber", phoneNumber);
		return info(resultMap);
	}
	
	/** ����ȷ�ϸ������ ��ø����˵���Ϣ
	 * */
	public String ConfirmPay(){
		List<ConfirmPay> result = RunOffOrderService.ConfirmPay(orderId);
		return info(result);
	}
	

	/** ������� ������
	 * */
	public String WorkDone(){
		int i = RunOffOrderService.WorkDone(orderId);
		if(i==1){
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	/** ������� ������
	 * */
	public String WaitComment(){
		int i = RunOffOrderService.WaitComment(orderId);
		if(i==1){
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	

	/** �������۳ɹ�״̬��Ϊ ���������
	 * */
	public String Finished(){
		int i = RunOffOrderService.Finished(orderId);
		if(i==1){	
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	
	/** Status = 8
	 * */
	public String BossComment(){
		int i = RunOffOrderService.BossComment(orderId);
		if(i==1){	
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	/** Status = 9
	 * */
	public String WorkerComment(){
		int i = RunOffOrderService.WorkerComment(orderId);
		if(i==1){	
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	
	
	
	
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getPartId() {
		return partId;
	}

	public void setPartId(int partId) {
		this.partId = partId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	
}
