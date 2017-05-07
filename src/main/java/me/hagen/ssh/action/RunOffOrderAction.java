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
	 * 展示某条具体跑腿信息   并在后面附上四条相关推荐
	 * */
	public String ShowDetail() {
		return (info(RunOffOrderService.showDetail(orderId,userid)));
	}

	/**x
	 * 默认拉去数据库最新发布的订单 传入拉去的次数
	 * */
	public String ShowLatest(){
		List<BriefRunOrder> result = RunOffOrderService.showLatest(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	/**
	 * 按距离 传入拉取次数 和使用时的经纬度
	 * */
	public String ShowByDistance(){
		List<BriefRunOrder> result = RunOffOrderService.showByDistance(time, longitude, latitude);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * 按价格由高到低展示 兼职信息 传入拉取次数
	 * */
	public String ShowByPay(){
		List<BriefRunOrder> result = RunOffOrderService.showByPay(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * 按信誉度查看
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
	 * 按关键字匹配展示 兼职信息 传入拉取次数和关键字
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
	 * 删除订单
	 * */
	public String DeleteOrder(){
		int i =RunOffOrderService.Delete(partId);
		if(i==2) return info(Message.message[0]);
		else return info(Message.message[18]);
	}
	
	/** 报名接口
	 *  @return 只有返回1 是成功
	 * */
	public String Enroll(){
		int i = RunOffOrderService.Enroll(orderId, userid);
		if (i==1) {
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);	
		}
	}
	
	
	/**  报名成功之后 
	 * 	用户可以看到自己报名的这个跑腿信息发布者的信息
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
	
	/** 跑腿确认付款界面 获得付款人的信息
	 * */
	public String ConfirmPay(){
		List<ConfirmPay> result = RunOffOrderService.ConfirmPay(orderId);
		return info(result);
	}
	

	/** 订单完成 待付款
	 * */
	public String WorkDone(){
		int i = RunOffOrderService.WorkDone(orderId);
		if(i==1){
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	/** 订单完成 待付款
	 * */
	public String WaitComment(){
		int i = RunOffOrderService.WaitComment(orderId);
		if(i==1){
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	

	/** 订单评价成功状态变为 彻底完成了
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
