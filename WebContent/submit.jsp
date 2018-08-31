<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.jm1.data.MessagePreDao"%>
<%@ page import="com.jm1.model.*"%>
<%@ page import="com.jm1.common.*"%>
<%@ page import="java.util.UUID"%>

<%

String nick = request.getParameter("nick");
String email = request.getParameter("email");
String content = request.getParameter("content");

MessagePreDao msgDao = new MessagePreDao();

Message message = new Message();
message.setId(UUID.randomUUID());
message.setNick(nick);
message.setEmail(email);
message.setContent(content);

boolean isSuccess = msgDao.insert(message);

String hint = isSuccess ? "发表成功，正在返回首页..." :  "提交失败，请重新填写";
response.setHeader("Refresh", "2, index.jsp");
%>
<h3><%= hint%></h3>

