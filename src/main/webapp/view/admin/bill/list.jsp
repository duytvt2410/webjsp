<%@page import="model.BillModel"%>
<%@page import="model.ClassifyModel"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.List"%>
<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%@include file="/common/admin/header/tag-head.jsp" %>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    	<%@include file="/common/admin/menu/menu.jsp" %>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <%@include file="/common/admin/header/header-template.jsp" %>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Danh sách hóa đơn</h1>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
          <div class="card-header py-3">
            	<% 
            		List<BillModel> listBill = (List<BillModel>) request.getAttribute("listBill");
            	
	            	String status = (String) request.getAttribute("status");
            		
            	%>
            		
            		<form action="<%=request.getContextPath() + "/admin-bill-list" %>" method="get">
            		<div class="row">
					<div class="form-group col-lg-2">
					<label for="status_fillter" class="m-0 text-primary font-weight-bold">Trạng thái: </label>
						  <select class="form-control custom-select-sm" id="status_filter" required name="status">
						    <option value="confirm" <%= (status.equalsIgnoreCase("confirm") ? "selected" : "" ) %>>Đã xác nhận</option>
						    <option value="inprocess" <%= (status.equalsIgnoreCase("inprocess") ? "selected" : "" ) %>>Đang giao</option>
						    <option value="done" <%= (status.equalsIgnoreCase("done") ? "selected" : "" ) %>>Đã giao</option>
						    <option value="cancel" <%= (status.equalsIgnoreCase("cancel") ? "selected" : "" ) %>>Đã hủy</option>
						  </select>
						 
					</div>
					<div class="form-group col-lg-2">
						<br>
						<button title="Lọc" class="btn btn-primary btn-sm" type="submit">Lọc</button>
						 
					</div>
					</div>
					</form>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th width="1%"><input type="checkbox"></th>
                      <th title="Tên người nhận" width="30%">Tên người nhận</th>
                      <th title="Tiền hóa đơn" width="20%">Tiền hóa đơn</th>
                      <th title="Trạng thái" width="15%">Trạng thái</th>
                      <th title="Ngày mua" width="15%">Ngày mua</th>
                      <th width="4%">Tác vụ <br><button title="Xóa" style="display:none" id="btn_deleteAll" onclick="deleteAll()" class="btn btn-danger btn-sm"><i class="far fa-trash-alt"></i></button></th>
                    </tr>
                  </thead>
                  <tbody>
                    	<% 
                    	
                    	for(BillModel bill : listBill) {
                    	%>
                    	<tr>
                    		<td><input type="checkbox" value="<%= bill.getId()%>"></td>
                     		<td title="<%=bill.getFullname()%>"><%= bill.getFullname() %></td>
							<td><%= Utils.convertToVND(bill.getTotalPrice())%></td>
							<td>
								<% if(bill.getStatus().equals("done")) { %>
									<div class="btn btn-success" style="cursor: default">
										<p>Đã giao</p>
									</div>
								<% } else if(bill.getStatus().equals("inprocess")) { %>
									<div class="btn btn-warning" style="cursor: default">
										<p>Đang giao</p>
									</div>
								<% } else if(bill.getStatus().equals("confirm")) { %>
									<div class="btn btn-info" style="cursor: default">
										<p>Đã xác nhận</p>
									</div>
								<% } else if(bill.getStatus().equals("cancel")) { %>
									<div class="btn btn-danger" style="cursor: default">
										<p>Đã hủy</p>
									</div>
								<% } %>
							</td>
							<td><%= Utils.formatDate(bill.getCreateDate())%></td>
							<td>
							<div class="hidden-sm hidden-sm btn-group">
								<button title="Xem chi tiết" class="btn btn-success btn-sm" onclick="openViewModal('<%= request.getContextPath() +"/bill-view?id=" + bill.getId() %>');"><i class="fas fa-search"></i></button>
								<button title="Cập nhật trạng thái" class="btn btn-info btn-sm" onclick="openConfirmUpdateStatus('<%=bill.getId()%>');"><i class="fas fa-edit"></i></button>
								<button title="Xóa" class="btn btn-danger btn-sm btn_deleteOne" onclick="deleteOne(<%=bill.getId()%>)"><i class="far fa-trash-alt"></i></button>
							</div>
							</td>
						</tr>
						<% } %>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <%@include file="/common/admin/footer/footer-template.jsp" %>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
	   
	  <!--MESSAGE Modal-->
	<div id="viewModal" class="modal fade">
		<div class="modal-dialog modal-lg modal-confirm">
			<div class="modal-content">
				<div class="modal-header">	
					 <h6 class="m-0 font-weight-bold text-primary">Xem chi tiết</h6>
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				</div>
	
				<div id="getView"></div>
			</div>
		</div>
	</div>    
	
	<!-- Confirm Modal-->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Bạn có chắc
						muốn xóa chứ?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Chọn "Xóa" để xóa.</div>
				<input type="hidden" id="getIdDelete">
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="confirmDelete()">Xóa</button>
					<button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>

		<!-- Confirm Modal-->
	<div class="modal fade" id="updateStatusModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Cập nhật trạng thái đơn hàng</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<select class="form-control" id="statusOrder" required name="status">
						    <option value="confirm">Đã xác nhận</option>
						    <option value="inprocess">Đang giao</option>
						    <option value="done">Đã giao</option>
						    <option value="cancel">Đã hủy</option>
						  </select>
				</div>
				<input type="hidden" id="getIdUpdateStatus">
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="confirmUpdateStatus()">Cập nhật</button>
					<button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
<%@include file="/common/admin/footer/script.jsp" %>
  <script src="template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script src="template/admin/js/demo/datatables-demo.js"></script>
  <script type="text/javascript">
  function deleteOne(id) {
		var idsToDelete =[];
		idsToDelete.push(id);
		$('#getIdDelete').val(idsToDelete);
			$('#confirmModal').modal({
				show: true, 
				keyboard: false, 
				backdrop: 'static'
			});		
	}
  

  function confirmDelete() {
	  var ids = $('#getIdDelete').val();
	  window.location.href= '<%= request.getContextPath() + "/admin-bill-delete?option=delete&id="%>' + ids;
	}
  
  function deleteAll() {
			$('#confirmModal').modal({
				show: true, 
				keyboard: false, 
				backdrop: 'static'
			});		
	}
  
  $("#dataTable input[type=checkbox]").click(function(){
		var idsToDelete = [];
		$("#dataTable input[type=checkbox]:checked").each(function(){
			idsToDelete.push($(this).val());
		});
		$('#getIdDelete').val(idsToDelete);
		if(idsToDelete.length > 0) {
			$("#btn_deleteAll").css("display", "block");
			$(".btn_deleteOne").css("display", "none");
		} else {
			$("#btn_deleteAll").css("display", "none");
			$(".btn_deleteOne").css("display", "block");
		}
	});
  
    function openConfirmUpdateStatus(id){
		$('#getIdUpdateStatus').val(id);
		$('#updateStatusModal').modal({
			show: true, 
			keyboard: false, 
			backdrop: 'static'
		});		
	}
  
    function confirmUpdateStatus() {
		var id = $('#getIdUpdateStatus').val();
		window.location.href= '<%= request.getContextPath() + "/admin-bill-update-status?id="%>' + id + '&status=' + $("#statusOrder").val();
	}
  
  </script>
  
  
</body>

</html>
	