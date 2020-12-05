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
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- STORE -->
					<div id="store" class="col-sm-12 col-12">
						<!-- store top filter -->
						
						<% 
						
						List<ProductModel> listProduct = (List<ProductModel>) request.getAttribute("listProduct"); 
						String tukhoa = (String) request.getAttribute("tukhoa");
						String title = "";
						if(listProduct != null) {
						%>
						<h4 class="text-danger" style="margin: 20px"> <%= listProduct.size()%> sản phẩm tìm được với từ khóa "<%=tukhoa %>"</h4>
						<!-- store products -->
						
						
						<% for(ProductModel product : listProduct) { 
							if(!product.getCategoryName().equals(title)) {
								title = product.getCategoryName();
						%>	
							<h2 class="title col-md-12 col-sm-12" style="margin-top: 40px"><%= title %></h2>
						<% } %>
							<!-- product -->
							<div class="col-md-3 col-sm-4 col-6 product-store">
								<div class="product">
									<div class="product-img">
										<img src="<%= product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "" %>" alt="">
										<div class="product-label">
											<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
											
										</div>
									</div>
									<div class="product-body">
										<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>" title="<%=product.getName()%>"><%= Utils.limitName(product.getName(), 40)  %></a></h3>
										<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
										<div class="product-rating">
											<% double starRating = Math.floor(product.getRatingAvg());
														for(int k = 0; k < 5; k++ ){ 
															if(starRating > 0) { 
																starRating--;%>
															<i class="fa fa-star"></i>
														<% } else {%>
															<i class="fa fa-star-o empty"></i>
														<% }
														}
													%>
												</div>
										<div class="product-btns">
											<button class="quick-view" onclick="location.href='<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>'"><i class="fa fa-eye"></i><span class="tooltipp">Xem chi tiết</span></button>
										</div>
									</div>
									<div class="add-to-cart">
										<button class="add-to-cart-btn" onclick="addCart(1, <%=product.getId()%>)"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
									</div>
								</div>
							</div>
							<!-- /product -->
							<% } 
							} else {
							%>
							<h5 class="text-danger" style="margin: 20px">Không tìm thấy sản phẩm</h5>
							<%} %>
						<!-- /store products -->
						
						<!-- /store bottom filter -->
					</div>
					<!-- /STORE -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

	<%@include file="/common/web/footer/footer-template.jsp" %>
	<%@include file="/common/web/footer/script.jsp" %>
	
	<script type="text/javascript">
	
	function getPage(first, page){
		var url = window.location.href; 
		if(url.includes("?")) {
			url += '&phantrang=yes&first=' + first + '&page=' + page;
		} else {
			url += '?phantrang=yes&first=' + first + '&page=' + page;
		}
		$.get(url, function(responseText) {
	        $("#getPageProduct").html(responseText);
		});
	}

	$(document).ready(function(){
		 $("#tab1 li").css("display", "table");
		 $("#tab1 li").css("width", "100%");
		 $("#tab1 li").css("border-top", "1px solid #eee");
		 $("#tab1 li").css("padding", "5px 0");
		 
		 $("#tab1 li p").css("display", "table-cell");
		 $("#tab1 li p").css("width", "60%");
		 $("#tab1 li p").css("vertical-align", "top");
		 $("#tab1 li p").css("padding", "6px 5px");
		 $("#tab1 li p a").css("width", "100%");
	});
	
	
	$(document).ready(function(){
		    $("form").attr("action", window.location.href);
		  
		});
	</script>
	
	</body>
</html>
