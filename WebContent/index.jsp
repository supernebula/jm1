<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.jm1.data.MessagePreDao"%>
<%@ page import="com.jm1.model.*"%>
<%@ page import="com.jm1.common.*"%>
<%@ page import="java.util.regex.*" %>
<%
MessagePreDao msgDao = new MessagePreDao();
%>
<%


String pageStr = request.getParameter("page");
String pageSizeStr = request.getParameter("pageSize");
int pageIndex = 1;
int pageSize = 5;

Pattern pattern = Pattern.compile("^[0-9]*$");

if(pageStr != null)
{
	Matcher matcher = pattern.matcher(pageStr);
	if(matcher.matches()){pageIndex = Integer.parseInt(pageStr);}
}

if(pageSizeStr != null)
{
	Matcher matcher2 = pattern.matcher(pageSizeStr);
	if(pageSizeStr != null && matcher2.matches()){pageSize = Integer.parseInt(pageSizeStr);}
}


PagedList<Message> pagedList = msgDao.selectPaged(pageIndex, pageSize, null);
int temp = 1;
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style/bootstrap/4.1.3/css/bootstrap.min.css" />
</head>
<body>
<%@ include file="head.jsp" %>



	<div class="container" style="width:80%;">
<%for(Message item : pagedList.items) {%>

  		<div class="row" style="margin:10px; width:100%; height:50px; background-color:#FAFAFA;">
		    <div class="col-md-3" itemid="<%= item.getId()%>">
		      <%= item.getNick() %>
		       <%= item.getEmail() %>
		       <%= item.getCreateTime() %>
		    </div>
		    <div class="col-md-8">
		      <%= item.getContent() %>
		    </div>
  		</div>
<%
}
%>
	<div class="row" style="margin:10px; width:100%; background-color:#EAEAEA;">
	    <%@ include file="form.jsp" %>
  		</div>

    </div>
    <%@ include file="foot.jsp" %>
</body>
</html>