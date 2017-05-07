package me.hagen.ssh.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.Comment;
import me.hagen.ssh.dto.Message;
import me.hagen.ssh.service.PartTimeCommentService;
import me.hagen.ssh.service.PartTimeOrderService;
import me.hagen.ssh.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
@Controller("PartTimeOrderAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class PartTimeOrderAction extends AbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8181929426514105746L;
	@Autowired
	PartTimeOrderService ptoService;
	@Autowired
	PartTimeCommentService partTimeCommentService;
	@Autowired
	UserService userService;
	
	private int partId;  //������id
	private int userid;
	private int time;
	private String keyWord;
	private float longitude;
	private float latitude;
	private int orderId;
	private String Cid;
	/**
	 * չʾĳ�������ְ��Ϣ   ���ں��渽����������Ƽ�
	 * */
	public String ShowDetail() {
		Map<String, Object> resultMap =ptoService.showDetail(orderId,userid);
		String ss = info(resultMap);
		return ss;
	}

	/**
	 * Ĭ����ȥ���ݿ����·����Ķ��� ������ȥ�Ĵ���
	 * */
	public String ShowLatest(){
		List<BriefPartTimeOrder> result = ptoService.showMainOrder(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * ���۸��ɸߵ���չʾ ��ְ��Ϣ ������ȡ����
	 * */
	public String ShowByPay(){
		List<BriefPartTimeOrder> result = ptoService.showByPay(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	/**
	 * ������ ������ȡ���� ��ʹ��ʱ�ľ�γ��
	 * */
	public String ShowByDistance(){
		List<BriefPartTimeOrder> result = ptoService.showByDistance(time,longitude,latitude);
		
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			System.out.println(info(result));
			return info(result);
		}
	}
	
	/** �鿴ĳһ������С��������Ķ���
	 * */
	public String ShowOneCategory(){
List<BriefPartTimeOrder> result = ptoService.ShowOneCategory(Cid, time);
		
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			System.out.println(info(result));
			return info(result);
		}
	}
	
	/**
	 * �������Ȳ鿴
	 *
	 * */
	public String ShowByStar(){
		List<BriefPartTimeOrder> result = ptoService.showByStar(time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			System.out.println(info(result));
			return info(result);
		}
	}
	
	
	/**
	 * ���ؼ���ƥ��չʾ ��ְ��Ϣ ������ȡ�����͹ؼ���
	 * @throws Exception 
	 * */
	public String ShowByKey() throws Exception{
		System.out.println(keyWord);
		String k= URLDecoder.decode(keyWord,"utf-8") ;
		System.out.println(k);
		List<BriefPartTimeOrder> result = ptoService.showByKeyWord(k, time);
		if(result == null || result.isEmpty()) return(info("null"));
		else{
			return info(result);
		}
	}
	
	
	/**
	 * ɾ������
	 * */
	public String DeleteOrder(){
		int i =ptoService.Delete(partId);
		if(i==2) return info(Message.message[0]);
		else return info(Message.message[18]);
	}
	
	
	/** �����ӿ�
	 *  @return ֻ�з���1 �ǳɹ�
	 * */
	public String Enroll(){
		int i = ptoService.Enroll(orderId, userid);
		if (i==1) {
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);	
		}
	}
//	
//	/** @param userId orderId
//	 *  ��ǰ�û�id �� ����id  �жϵ�ǰ�û��Ƿ���ԶԸö������б���  ����Ѿ������� �Ͳ����� 
//	 *  @return 0���Ա���  1�Ѿ�����
//	 * */
//	public String CheckEnroll(){
//		int i = ptoService.CheckEnroll(userid, orderId);
//		if (i==0) {
//			return info("you can enroll");
//		}else {
//			return info("you have enrolled");	
//		}
//	}
//	
//	
	/** ¼��
	 * */
	public String Hire(){
		int i = ptoService.Hire(orderId, userid);
		if (i==1) {
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);	
		}
	}
		
	/** �ܾ�
	 * */
	public String Reject(){
		int i = ptoService.Reject(orderId);
		return info(Message.message[0]);
	}
	
	/** ������� ������
	 * */
	public String WorkDone(){
		int i = ptoService.WorkDone(orderId);
		if(i==1){
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	/** ������� ������
	 * */
	public String WaitComment(){
		int i = ptoService.WaitComment(orderId);
		if(i==1){
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	

	/** �������۳ɹ�״̬��Ϊ ���������
	 * */
	public String Finished(){
		int i = ptoService.Finished(orderId);
		if(i==1){	
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	/** �������۳ɹ�״̬8
	 * */
	public String BossComment(){
		int i = ptoService.BossComment(orderId);
		if(i==1){	
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	
	/** �������۳ɹ�״̬9
	 * */
	public String WorkerComment(){
		int i = ptoService.WorkerComment(orderId);
		if(i==1){	
			return info(Message.message[0]);
		}else {
			return info(Message.message[18]);
		}
	}
	
	/**  �����ɹ�֮�� 
	 * 	�û����Կ����Լ������������ְ��Ϣ�����ߵ���Ϣ
	 * */
	public String ShowBossInfo(){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Comment> r1 = partTimeCommentService.CommentAsBoss(userid);
		List<Comment> r2 = partTimeCommentService.CommentAsWorker(userid);
		String TrueName = userService.getTrueName(userid);
		float star = ptoService.StarByUserId(userid);
	resultMap.put("comment1", r1);
	resultMap.put("comment2", r2);
	resultMap.put("TrueName", TrueName);
	resultMap.put("star", star);
	return info(resultMap);
	}
	
	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCid() {
		return Cid;
	}

	public void setCid(String cid) {
		Cid = cid;
	}

}
