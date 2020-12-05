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
			<%
				if(request.getAttribute("alert") != null) {
			%>
			<div class="modal-header">	
				<%
					if(request.getAttribute("alert").equals("success")) {
						
						
				%>
						<button type="button" class="close" onclick="window.location = window.location.href.split('?')[0];">&times;</button>
						
				<%
					} else {
				%>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>		
				<%
					} 
				%>
                
			</div>
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
				<button type="button" onclick="window.location = window.location.href.split('?')[0];" class="btn btn-secondary" data-dismiss="modal">Đóng</button>		
				
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
	