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

		<!-- SECTION -->
		<div class="section">
			<% BillModel bill = (BillModel) request.getAttribute("billModel"); %>
			<!-- container -->
			<div class="container">
			<%
				if(request.getAttribute("codeConfirm") == null) {
			%>
			<form action="<%= request.getContextPath() + "/thanhtoan"%>" method="post">
				<!-- row -->
				<div class="row">

					<div class="col-md-7">
						
						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Thông tin khách hàng</h3>
							</div>
							<% String message = (String) request.getAttribute("message"); %>
							<% if(message != null) {%>
								
								<div class="alert alert-danger alert-dismissible fade in">
								<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								<%= message %>
								</div>
								<% } %>
							<div class="form-group">
								<input class="input" value="<%= bill != null ? bill.getFullname() : "" %>" type="text" name="fullname" placeholder="Họ tên">
							</div>
							<div class="form-group">
								<input class="input" value="<%= bill != null ? bill.getEmail() : "" %>" type="email" name="email" placeholder="Email">
							</div>
							<div class="form-group">
								<input class="input" value="<%= bill != null ? bill.getAddress() : "" %>" type="text" name="address" placeholder="Địa chỉ">
							</div>
							<div class="form-group">
								<input class="input" value="<%= bill != null ? bill.getPhone() : "" %>" type="text" name="phone" placeholder="Số điện thoại">
							</div>
						</div>
						<!-- /Billing Details -->

						

						<!-- Order notes -->
						<div class="order-notes">
							<textarea class="input" name="note" placeholder="Ghi chú"><%= bill != null ? bill.getNote() : "" %></textarea>
						</div>
						<!-- /Order notes -->
					</div>

					<!-- Order Details -->
					<div class="col-md-5 order-details">
						<div class="section-title text-center">
							<h3 class="title">Giỏ hàng</h3>
						</div>
						<div class="order-summary">
							<div class="order-col">
								<div><strong>SẢN PHẨM</strong></div>
								<div><strong>TỔNG</strong></div>
							</div>
							<div class="order-products">
								<% for(ProductModel product : cart.list()){ %>
								<div class="order-col">
									<div><%= product.getQtyInCart() %>x <%= product.getName() %></div>
									<div><%= product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()) %></div>
								</div>
								<% } %>
							</div>
							<div class="order-col">
								<div><strong>TỔNG TIỀN</strong></div>
								<div style="width: 200px"><strong class="order-total"><%= Utils.convertToVND(cart.total()) %></strong></div>
							</div>
						</div>
						
						<button type="submit" class="primary-btn order-submit">Thanh toán</button>
					</div>
					
					<!-- /Order Details -->
				</div>
				<!-- /row -->
				</form>
			
			<% } else { %>
			
			<form action="<%= request.getContextPath() + "/xacnhandon"%>" method="get">
				<!-- row -->
				<div class="row">

					<div class="col-md-12">
						
						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">Xác nhận đặt hàng</h3>
							</div>
							<h6>Mã xác nhận đã được gửi vài Email của bạn</h6>
							<div class="form-group">
								<input class="input" type="text" name="maxacnhan" placeholder="Nhập mã">
								<a class="mt-2" href="<%=request.getContextPath() + "/guilaima"%>">Gửi lại mã</a>
							</div>
						</div>
						<!-- /Billing Details -->
						<button type="submit" class="primary-btn order-submit">Xác nhận</button>
						
					</div>
					<!-- /Order Details -->
				</div>
				<!-- /row -->
				</form>
				<% } %>
				</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		
		  

	<%@include file="/common/web/footer/footer-template.jsp" %>
	<%@include file="/common/web/footer/script.jsp" %>

	<script src="template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<script src="template/admin/js/demo/datatables-demo.js"></script>
	<script type="text/javascript">
	<%if (request.getAttribute("message") != null) {%>
		$('#notificationModal').modal({
				show : true,
				keyboard : false,
				backdrop : 'static'
			});
		<%}%>
	</script>
	</body>
</html>
