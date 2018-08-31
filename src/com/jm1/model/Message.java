package com.jm1.model;

import java.util.Date;
import java.util.UUID;

//留言
public class Message {
	
	//唯一编号
	private UUID id;
	
	//昵称
	private String nick;
	//邮箱
	private String email;
	//正文
	private String content;
	
	//创建时间
	private Date createTime;
	

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
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
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}
