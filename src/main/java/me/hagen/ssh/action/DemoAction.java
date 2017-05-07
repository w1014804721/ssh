package me.hagen.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import me.hagen.ssh.domain.User;
import me.hagen.ssh.service.UserService;
import net.sf.json.JSONObject;

@Controller("DemoAction")
public class DemoAction extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -744815261360172784L;

//	private String username;
//	private String password;
//	@Autowired
//	private UserService service;
//	
//	public String index()
//	{
//		return "index";
//	}
//	public String login()
//	{
////		User u = service.findUser(username, password);
//		Message msg = new Message();
//		if("dd"==null)
//		{
//			msg.errcode = -1;
//			msg.errmsg = "鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒";
//		}
//		else
//		{
//			msg.errcode = 1;
//			msg.errmsg = "ok";
//			session.put("user", "dd");
//		}
//		String res = JSONObject.fromObject(msg).toString();
//	
//		return info(res);
//	}
//	public String register()
//	{
//		Message msg = new Message();
//		if(username==null||password==null)
//		{
//			msg.errcode = -1;
//			msg.errmsg = "淇℃伅涓嶅畬鏁�;
//			return info(""d);
//		}
//		boolean u = service.adduser(username, password);
//		if(!u)
//		{
//			msg.errcode = -2;
//			msg.errmsg = "鐢ㄦ埛鍚嶅凡瀛樺湪";
//			return info(msg);
//		}else
//		{
//			msg.errcode = 1;
//			msg.errmsg = "ok";
//			return info(msg);
//		}
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	
//	public static class Message
//	{
//		private int errcode;
//		private String errmsg;
//		public int getErrcode() {
//			return errcode;
//		}
//		public void setErrcode(int errcode) {
//			this.errcode = errcode;
//		}
//		public String getErrmsg() {
//			return errmsg;
//		}
//		public void setErrmsg(String errmsg) {
//			this.errmsg = errmsg;
//		}
//		
//	}

	
}
