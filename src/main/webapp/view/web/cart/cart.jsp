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
<br>
			<!-- container -->
<div class="container">
			
	<div class="row">
		<div class="col-xs-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">
						<div class="row">
							<div class="col-xs-8">
								<h4><i class="fa fa-shopping-cart" aria-hidden="true"></i> Giỏ hàng</h4>
							</div>
							<div class="col-xs-4">
								<button type="button" class="btn btn-primary btn-sm btn-block">
									<i class="fa fa-share" aria-hidden="true"></i> Tiếp tục mua hàng
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="panel-body">
						<%
                    	for(ProductModel product : cart.list()) {
                    	%>
					<div class="row">
						<div class="col-xs-2"><img style="height: 70px; margin: auto;" class="img-responsive" src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>">
						</div>
						<div class="col-xs-4">
							<h5 class="product-name"><strong><%= product.getName() %></strong></h5>
						</div>
						<div class="col-xs-6">
							<div class="col-xs-7 text-right">
								<h6><strong><%= product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()) %> <span class="text-muted">x</span></strong></h6>
							</div>
							<div class="add-to-cart col-xs-3 text-right">
								<div class="qty-label">
									<div class="input-number">
										<input id="qty-<%=product.getId() %>" onchange="limitQty(this.value,<%=product.getId() %> )" type="number" value="<%= product.getQtyInCart() %>" min="0" max="3">
										<span class="qty-up">+</span>
										<span class="qty-down">-</span>
									</div>
								</div>
								<button style="width: 100%" class="btn btn-primary btn-sm" onclick="updateCart(document.getElementById('qty-<%=product.getId() %>').value,<%= product.getId()%>)"> Cập nhật</button>
							</div>
							<div class="col-xs-2">
								<button title="Xóa" type="button" style="width: 100%; font-size: 20px; color: red" class="btn btn-link" onclick="deleteItemCart('<%=product.getId()%>')">
									<i class="fa fa-trash-o" aria-hidden="true"></i>
								</button>
							</div>
						</div>
					</div>
					<hr>
					<% 
                    	}
					%>
					
				</div>
              	</div>
			<!-- /container -->
			<!-- Order Details -->
					<div class="col-md-4 order-details" style="float:right">
						<div class="order-summary">
							<div class="order-col">
								<div><strong>TỔNG TIỀN</strong></div>
								<div style="width:200px"><strong class="order-total"><%= Utils.convertToVND(cart.total()) %></strong></div>
							</div>
						</div>
						
						<a href="<%= request.getContextPath() + (user != null ? "/thanhtoan" : "/dangnhap")%>" class="primary-btn order-submit">Thanh toán</a>
					</div>
				</div>
			</div>
		</div>
			
			
			
				

		<!-- /SECTION -->
	

	<%@include file="/common/web/footer/footer-template.jsp" %>
	<%@include file="/common/web/footer/script.jsp" %>
	<script type="text/javascript">
	function limitQty(qty, idQty) {
		  var num = parseInt(qty);
		  var id = "#qty-" + idQty;
		  if(num < 0) {
			  $(id).val("1");
		  } else if(num > 3){
			  $(id).val("3");
		  }
	}
	
	function updateCart(qty, id) {
		  var url = '<%= request.getContextPath() + "/giohang?option=update&id="%>' + id + "&qty=" + qty; 
			window.location.href = url;
		}
	
	function deleteItemCart(id) {
		  var url = '<%= request.getContextPath() + "/giohang?option=delete&id="%>' + id; 
			window.location.href = url;
		}
	 
	</script>
	<script src="template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
	<script src="template/admin/js/demo/datatables-demo.js"></script>
	
	</body>
</html>
