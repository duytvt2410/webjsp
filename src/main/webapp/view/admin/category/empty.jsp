<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="/common/admin/header/tag-head.jsp" %>

<body id="page-top">
	
  

 <!--MESSAGE Modal-->
<div id="notificationModal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<%
				if(request.getAttribute("alert") != null) {
			%>
			<div class="text-center">
			
				<%
					if(request.getAttribute("alert").equals("success")) {
				%>
					<h1><i class="fas fa-check-circle text-success"></i></h1>
				<%
					} else if(request.getAttribute("alert").equals("danger")) {
				%>
					<h1><i class="far fa-times-circle text-danger"></i></h1>
				<%
					} 
				%>
					<h5 class="modal-title"><%=request.getAttribute("message") %></h5>	
			</div>			
			
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" onclick="history.back()">Đóng</button>	
			</div>
		<%
			}
		%>
		</div>
	</div>
</div>     

<%@include file="/common/admin/footer/script.jsp" %>


</body>

</html>
	