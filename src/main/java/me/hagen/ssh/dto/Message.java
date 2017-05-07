package me.hagen.ssh.dto;

public class Message {
	private static int random;
	private int errcode;
	private String errmsg;
	public Message(){}
	public Message(int e,String s){errcode = e;errmsg = s;}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
/*

 * errcode >0 ok

 * errcode <0 error

 * */
	public static Message[] message = {
		new Message(1,"ok"),
		new Message(-1,"Wrong checkNumber"),
		new Message(-2,"connect timeout"),
		new Message(-3,"invalid token"),
		new Message(-4,"get user info from weixin failed"),
		new Message(-5,"Login and checkNumber has sent"),
		new Message(-6,"This is a new user and Register"),
		new Message(-7,"password length mismatched"),
		new Message(-8,"password error"),
		new Message(-9,"information not satisfied"),
		new Message(-10,"you have no relevant order"),
		new Message(-11,"username not exists"),
		new Message(-12,"username or password wrong"),
		new Message(-13,"not login"),
		new Message(-14,"device not found"),
		new Message(-15,"image wrong"),
		new Message(-16,"you and userId not in a group"),
		new Message(-17,""),
		new Message(-18,"Something Wrong, but i don't know"),
		new Message(-19,"arguments null"),
		new Message(-20,"ShortMessage send fail"),
		new Message(-22,""),
		new Message(-23,""),
		new Message(-24,""),
		new Message(-25,""),
		new Message(-26,""),
		new Message(-27,"exception occur!"),
		new Message(-28,"secret or other error!"),
		new Message(-29,"key exsits"),
		new Message(-30,"could not get current button"),
		new Message(-31,"parent label not exsits"),
		new Message(-32,"is out of size")
	};
}
