package me.hagen.ssh.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import me.hagen.ssh.dao.UserDao;
import me.hagen.ssh.dto.ConfirmPay;
import me.hagen.ssh.dto.Message;
import me.hagen.ssh.service.AlipayService;
import me.hagen.ssh.service.PartTimeOrderService;
import me.hagen.ssh.service.UserService;

import org.apache.struts2.StrutsStatics;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import alipayUtility.AlipayConfig;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.opensymphony.xwork2.ActionContext;

@Controller("PayAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class PayAction extends AbstractAction {

	@Autowired
	PartTimeOrderService partTimeOrderService;
	@Autowired
	UserService userService;
	@Autowired
	AlipayService alipayService;
	
	private String notify_url = "http://商户网关地址/batch_trans_notify-JAVA-UTF-8/notify_url.jsp";
	private String email;  //支付宝账号
	private String account_name; //支付宝账户名称
	private String pay_date;	//付款日期
	private String batch_no;  //批次号
	private String batch_fee;//总金额
	private String batch_num; //总笔数
	private String detail_data; //详细数据
	private String checkNumber;

    public String getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    /**
	 * 
	 */
	private static final long serialVersionUID = -175821626660193018L;
	private int orderid;
	private String content;
	private String userid;  // 用来接受批量付款的 userid字符串 
	private float pay;
	private int UserId; //正常的单个userid
	private String PayPassword;
	private String detail;
	private int id;  // 优惠券的 id主键
	
	public String Test(){

		System.out.println("****************"+content);
		String result = rsa(content);
		System.out.println("****************"+result);
		return info(result);
	}
	
	
	
	public static String rsa(String content){
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALitqmAhbEtR1IKVO31sGsc7ng"
				+ "nNcXt7t4BSdP6f/Fd2CDF8OM1R/rNa5rjE0rkUuVeu+EGZsrHspLONeXzCR8XIPSTGzJesengJlYNfKaaK"
				+ "hqyuoo2o2JwI57JawXN6miUQI9cBzy8smT7aGeQk//qAxpYacqHihQ46TCY6"
				+ "kbmDAgMBAAECgYBucKyqwFIa2NzVGplw7+"
				+ "0zqBtikVGEHwAl5SnB+7rUMunDbh4p6xb9+yZu6IV72UDdhu0/jcKnVHVKSxiT2zs/r9TttdMpLzR7z"
				+ "/eKSQXp0tCKPs5MvM3vY5YARyXrtAda9WIJTuQBLz3TuDxvlFbxHLm8jktcWNpAzXlrTMgcAQJBAN6yf1RaHFaa6Sv/meVbexm5w"
				+ "CpjHW+lhJtaGUAfI+igQKUrpddL8jg765ufxnhMofWHf+zkpkM86fbHeNcGi0ECQQDUS7HC6kIMQqrLE"
				+ "LKn8hpZKmBEVe/3z3cLsgwVu9hcz/AKNjpbY598wg33QG1OmRP17wXSXoSBFlTPmdXzbufDAkEAuyrYW2PU13EhMiq"
				+ "EBMGLR8bXXIirD8/UEOF7wWmqu3QcgPWbT8Tz0hGCZuy1WRxHLt3wRYCywGYs2Y0y4b4NQQJAUObzvDLnrq"
				+ "JtUetwGf5yOg6jFwek5kwisstMPbATOPNpJ4sY3CDv82xERDhCL5S4fZ10G4I3frYS5fZbCWT"
				+ "3XwJBAK9agjx5/kYH5CiAUkTUYW2a8iHZUg3PUWZNtNqqvxptnWQU2BaYSvv92tjgOMn+Ri2E"
				+ "OlolC2BQnJdMBcFPocY=";
		
		
		
		String Result = "";
		try {
		Result = com.alipay.api.internal.util.AlipaySignature.rsaSign(content, privateKey, "UTF-8");
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Result;
	}
	

	
	
	/** 兼职确认付款界面 获得付款人的信息
	 * */
	public String ConfirmPay(){
		List<ConfirmPay> result = partTimeOrderService.ConfirmPay(orderid);
		return info(result);
	}
	
	public String AddBalance(){
		List<Integer> UserId = new ArrayList<Integer>();
		System.out.println(userid);
		String result = userid.replaceAll("[\\pP\\p{Punct}]","a");
		System.out.println(result);
		String[] r = result.split("a");
		System.out.println(r.length);
		for(int i =1;i<r.length;i++){
			String q = r[i];
			int a = Integer.valueOf(q);
			UserId.add(a);
			System.out.println(a);
		}


		int reuslt = userService.AddBalance(UserId, pay);
		if(reuslt >0){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	
	/**  用户提现后 余额变少
	 * */
	public String DecreaseBalance(){
		int result =userService.DecreaseBalance(UserId, pay);
		if(result >0){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	public void AlipaySubmit(){
        //TODO 写拦截器验证验证码
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "batch_trans_notify");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("notify_url", notify_url);
		sParaTemp.put("email", email);
		sParaTemp.put("account_name", account_name);
		sParaTemp.put("pay_date", pay_date);
		sParaTemp.put("batch_no", batch_no);
		sParaTemp.put("batch_fee", batch_fee);
		sParaTemp.put("batch_num", batch_num);
		sParaTemp.put("detail_data", detail_data);
		
		
		String sHtmlText = alipayUtility.AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		PrintWriter out;
		try {
			response = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			response.setContentType("text/html;charset=utf-8"); 
			System.out.println(sHtmlText);
			out = response.getWriter();
			out.println(sHtmlText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	/** 支付密码是否正确
	 * */
	public String PassRight(){
		boolean i =userService.PassRight(PayPassword, UserId);
		if(i==true){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	
	/** 使用优惠券
	 * */
	public String useCoupon(){
		int result = userService.UseCoupon(id);
		if(result >0){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	
	/**  给用户批量付款用的
	 * */
	public String BackMoney(){
		int result = alipayService.BackMoney(detail);
		if(result >0){
			return info(Message.message[0]);
		}else{
			return info(Message.message[18]);
		}
	}
	
	
	/** 测试用获取当前路劲
	 * */
	public String testt(){
		@SuppressWarnings("deprecation")
		String s = servletRequest.getRealPath("");
		System.out.println(s);
		return info(s);
	}
	
	
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPayPassword() {
		return PayPassword;
	}
	public void setPayPassword(String payPassword) {
		PayPassword = payPassword;
	}
	public float getPay() {
		return pay;
	}
	public void setPay(float pay) {
		this.pay = pay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public String getBatch_fee() {
		return batch_fee;
	}
	public void setBatch_fee(String batch_fee) {
		this.batch_fee = batch_fee;
	}
	public String getBatch_num() {
		return batch_num;
	}
	public void setBatch_num(String batch_num) {
		this.batch_num = batch_num;
	}
	public String getDetail_data() {
		return detail_data;
	}
	public void setDetail_data(String detail_data) {
		this.detail_data = detail_data;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
//	public static void main(String args[]) throws AlipayApiException {
//		String result = rsa("app_id=2016101702207341&bi"
//				+ "z_content={\"timeout_express\""
//				+ ":\"30m\",\"seller_id\":\"2043104"
//				+ "567@qq.com\",\"product_code\":\"QU"
//				+ "ICK_MSECURITY_PAY\",\"tota"
//				+ "l_amount\":\"0.01\",\"sub"
//				+ "ject\":\"1\",\"body\":\"���ǲ�"
//				+ "�����\",\"out_trade_no\":\"PAG"
//				+ "AWCXF5VP3YH4\"}&charset=utf-8&"
//				+ "method=alipay.trade.app.pay&sig"
//				+ "n_type=RSA&timestamp=2016-11-09 19:56:23&version=1.0");
//		System.out.println(result);
//		
//		
//	}
	
}
