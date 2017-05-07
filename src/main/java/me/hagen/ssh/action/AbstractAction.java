package me.hagen.ssh.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.finder.ClassFinder.Info;

import me.hagen.ssh.dto.BriefPartTimeOrder;
import me.hagen.ssh.dto.Message;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AbstractAction extends ActionSupport implements RequestAware,
		SessionAware, ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6917622318026619763L;
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected HttpServletResponse response;
	protected HttpServletRequest servletRequest;
	protected HttpServletRequest req;

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
		req = servletRequest;
	}

	public String info(String msg) {
		request.put("info", msg);
		return SUCCESS;
	}
	public String info(int msg) {
		request.put("info", msg);
		return SUCCESS;
	}

	public String info(Map<String,Object> msg) {
		if (msg == null)
			msg = new HashMap<String, Object>();
		JSONArray  json = JSONArray.fromObject(msg);
		request.put("info", json.toString());
		return SUCCESS;
	}
	
	public String info(Message msg)
	{
		if(msg==null)msg = new Message();
		JSONObject json = JSONObject.fromObject(msg);
		request.put("info", json.toString());
		return SUCCESS;
	}
	
	public String info(List e) {
		if (e == null)
			e = new ArrayList<>();
		JSONArray  json = JSONArray.fromObject(e);
		request.put("info", json.toString());
		return SUCCESS;
	}
	
	
}
