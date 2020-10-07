<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<footer class="sticky-footer bg-white">
	<div class="container my-auto">
		<div class="copyright text-center my-auto">
			<span>Copyright &copy; Your Website 2019</span>
		</div>
	</div>
</footer>

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>
  
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
				<%
					if(request.getAttribute("alert").equals("success")) {
						Long id = 0L;
						if(request.getParameter("id") != null) id = Long.parseLong(request.getParameter("id"));
						
				%>
						<button type="button" class="btn btn-secondary" onclick="window.location = window.location.href.split('?')[0];">Đóng</button>	
				<%
					} else {
				%>
						<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>		
				<%
					} 
				%>
			</div>
		<%
			}
		%>
		</div>
	</div>
</div>     