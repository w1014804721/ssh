package me.hagen.ssh.action;

import java.util.HashMap;
import java.util.Map;

import me.hagen.ssh.service.PCService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller("PersonalCenterAction")
@org.springframework.context.annotation.Scope(value = "prototype")
public class PersonalCenterAction  extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2491734351368550262L;
	
	@Autowired
	private PCService pcService;
	
	private int userid;
	
	public String enter(){
		Map<String, Object> result = pcService.enter(userid);
		return info(result);
	}

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
}
