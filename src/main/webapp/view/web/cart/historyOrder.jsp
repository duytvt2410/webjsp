<%@page import="model.BillModel"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="model.ClassifyModel"%>
<%@page import="model.BrandModel"%>
<%@page import="model.CategoryModel"%>
<%@page import="model.ImagesModel"%>
<%@page import="utils.Utils"%>
<%@page import="model.ProductModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="/common/web/header/tag-head.jsp" %>

	<body>
		<%@include file="/common/web/header/header-template.jsp" %>
				<% 
            		List<BillModel> listBill = (List<BillModel>) request.getAttribute("listBill");
					int stt = 0;
            	%>
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th title="STT" width="1%">STT</th>
                      <th title="Tên người nhận" width="30%">Tên người nhận</th>
                      <th title="Tiền hóa đơn" width="20%">Tiền hóa đơn</th>
                      <th title="Trạng thái" width="15%">Trạng thái</th>
                      <th title="Ngày mua" width="15%">Ngày mua</th>
                      <th >Tác vụ</th>
                    </tr>
                  </thead>
                  <tbody>
                    	<% 
                    	
                    	for(BillModel bill : listBill) {
                    		stt++;
                    	%>
                    	<tr>
                    		<td><%= stt %></td>
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
								<button title="Xem chi tiết" class="btn btn-primary btn-sm" onclick="openViewModal('<%= request.getContextPath() +"/bill-view?id=" + bill.getId() %>');"><i class="fa fa-search"></i></button>
								<button title="Hủy đơn hàng" <%= bill.getStatus().equals("cancel") ||  bill.getStatus().equals("done") ? "disabled" : "" %> class="btn btn-danger btn-sm" onclick="openCancelModal('<%= bill.getId() %>');"><i class="fa fa-window-close"></i></button>
							</div>
							</td>
						</tr>
						<% } %>
                    
                  </tbody>
                </table>
              </div>
		</div>
		</div>
		<!-- /SECTION -->
		
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
	<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Bạn có chắc
						muốn hủy đơn hàng này chứ?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Chọn "Hủy" để hủy.</div>
				<input type="hidden" id="getIdCancel">
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="confirmCancel()">Hủy</button>
					<button class="btn btn-secondary" type="button" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
	

	<%@include file="/common/web/footer/footer-template.jsp" %>
	<%@include file="/common/web/footer/script.jsp" %>

	<script src="template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<script src="template/admin/js/demo/datatables-demo.js"></script>
	
	<script type="text/javascript">
	function openViewModal(url){
		$.get(url, function(responseText) {
	        $("#getView").html(responseText);
		});
		$('#viewModal').modal({
			show: true, 
			keyboard: false, 
			backdrop: 'static'
		});		
	}
	
	function openCancelModal(id){
		$('#getIdCancel').val(id);
		$('#cancelModal').modal({
			show: true, 
			keyboard: false, 
			backdrop: 'static'
		});		
	}
	
	function confirmCancel() {
		var id = $('#getIdCancel').val();
		window.location.href= '<%= request.getContextPath() + "/huydonhang?id="%>' + id;
	}
	</script>
	
	</body>
</html>
