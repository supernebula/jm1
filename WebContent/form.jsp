<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="container" style="width:100%;">
	<form action="submit.jsp" method="post">
  		<div class="row" style="margin:10px; width:100%;">
		    <div class="col-md-3" >
		      昵称:
		    </div>
		    <div class="col-md-9">
		      <input type="text"  name="nick">
		    </div>
  		</div>
  		  	<div class="row" style="margin:10px; width:100%;">
			    <div class="col-md-3" >
			      Email:
			    </div>
			    <div class="col-md-9">
			      <input type="text"  name="email">
			    </div>
  			</div>
  		
  		  	<div class="row" style="margin:10px; width:100%;">
	  			<div class="col-md-3" >
			      内容:
			    </div>
			    <div class="col-md-9">
			      	<textarea name="content" rows="5" style="width:100%;"></textarea>
			    </div>
		  	</div>
		  	  <div class="row" style="margin:10px; width:100%;">

			    <div class="col-md-12" style="text-align: center;">
			      	<input type="submit" value="提交"/>
			    </div>
		  	</div>
  	</form>


    </div>