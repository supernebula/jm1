package com.jm1.model;

//留言
public class MessageBean {
	
	//昵称
	private String nick;
	//邮箱
	private String email;
	//正文
	private String content;
	
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
