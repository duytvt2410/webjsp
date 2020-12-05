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
		List<CategoryModel> listCategory = (List<CategoryModel>) request.getAttribute("listCategory"); 
		List<BrandModel> listBrand = (List<BrandModel>) request.getAttribute("listBrand");
		List<ClassifyModel> listClassify = (List<ClassifyModel>) request.getAttribute("listClassify");
		String nhomsp[] = null;
		String theloai = (String) request.getAttribute("theloai");
		String nsx = "";
		if(request.getParameter("nsx") != null && !request.getParameter("nsx").equals("")) nsx =request.getParameter("nsx");
		if(request.getParameterValues("nhomsp") != null) nhomsp = request.getParameterValues("nhomsp");
		 if(listClassify != null && listClassify.size() > 0){
			 Collections.sort(listClassify, new Comparator<ClassifyModel>() {
					@Override
					public int compare(ClassifyModel c2, ClassifyModel c1) {

						return c1.getName().compareTo(c2.getName());
					}
			});
		 }
		
		%>
		
		
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- ASIDE -->
					<div id="aside" class="col-sm-3 col-3">
					
						<% if(listCategory != null && listCategory.size() != 0) { %>
						<!-- aside Widget -->
						<div class="aside">
							<h3 class="aside-title">Danh mục</h3>
							<div class="checkbox-filter">
								<% 
								for(CategoryModel category : listCategory) { 
									
								%>
								
								<div class="input-checkbox">
									<input type="radio" <%= (theloai != null ? (category.getAlias().equals(theloai) ? "checked" : "") : "") %> onchange="window.location.href='<%= request.getContextPath() + "/phukien?theloai=" + category.getAlias() %>'" value="<%=category.getId() %>" id="category-<%=category.getId() %>">
									<label for="category-<%=category.getId() %>">
										<span></span>
										<%= category.getName() %>
										<small></small>
									</label>
								</div>
								<% } %>
								
							</div>
						</div>
						<!-- /aside Widget -->
						<% } %>
						
						<form method="get">
						<% if(listBrand != null && listBrand.size() != 0) { %>
						<!-- aside Widget -->
						<div class="aside">
							<h3 class="aside-title">Nhãn hàng</h3>
							<div class="checkbox-filter">
								<% for(BrandModel brand : listBrand) { %>
								<div class="input-checkbox">
									<input type="radio" <%= (brand.getId().equals(nsx) ? "checked" : "") %> value="<%=brand.getId()%>" name = "nsx" id="brand-<%=brand.getId()%>">
									<label for="brand-<%=brand.getId()%>">
										<span></span>
										<%= brand.getName() %>
										<small></small>
									</label>
								</div>
								<% } %>
								
							</div>
						</div>
						<!-- /aside Widget -->
						<% } %>
						
						<!-- /aside Widget -->

						<% if(listClassify != null && listClassify.size() != 0) { %>
						<!-- aside Widget -->
						<div class="aside">
							<h3 class="aside-title">Bộ lọc</h3>
							<div class="checkbox-filter" style="margin-left: 14px">
								<% 
								String title = "";
								for(ClassifyModel classify : listClassify) { 
									String nameSplit[] = classify.getName().split(":");
									if (!nameSplit[0].equals(title)) {
										title = nameSplit[0];
								%>
								<h6 class="text-primary"><%= title %></h6>
								<% } 
									boolean kt = false;
									if(nhomsp != null) {
										for(String isChoose : nhomsp) {
											if(!isChoose.equals("")) {
												if(classify.getId().equals(isChoose)){
													kt = true;	
									%>
													<div class="input-checkbox">
														<input type="checkbox" checked name="nhomsp" value="<%=classify.getId()%>" id="classify-<%=classify.getId()%>">
														<label for="classify-<%=classify.getId()%>">
															<span></span>
															<%= nameSplit[1] %>
															<small></small>
														</label>
													</div>
									<% 			 } 
									 		 }
										}
									}
									if(kt == false) {
								%>
										<div class="input-checkbox">
											<input type="checkbox" name="nhomsp" value="<%=classify.getId()%>" id="classify-<%=classify.getId()%>">
											<label for="classify-<%=classify.getId()%>">
												<span></span>
												<%= nameSplit[1] %>
												<small></small>
											</label>
										</div>
								<%	}
								}
								%>
								
							</div>
						</div>
						<!-- /aside Widget -->
						<% } %>
						<input type="hidden" name="theloai" value="<%=theloai%>">
						<button type="submit">Lọc</button>
						</form>
					</div>
					
					<!-- /ASIDE -->

					<!-- STORE -->
					<div id="store" class="col-sm-9 col-9">
						<!-- store top filter -->
						
						<!-- /store top filter -->
						<div id="getPageProduct">
						<% 
						
						List<ProductModel> listProduct = (List<ProductModel>) request.getAttribute("listProduct"); 
						if(listProduct != null && listProduct.size() > 0) {
							int limit = (int) request.getAttribute("limit");
							int length = (int) request.getAttribute("length");
							int first = (int) request.getAttribute("first");
							int pg = (int) request.getAttribute("page");
							int end = first + (length - first) ;
							if(end - limit - first >= 0) end = first + limit - 1;
						%>
						
						<!-- store products -->
						<div class="row">
						<% int countProduct = 0;
							for(ProductModel product : listProduct) { 
								countProduct++;
						%>
							<!-- product -->
							<div class="col-md-4 col-sm-6">
								<div class="product">
									<div class="product-img">
										<img src="<%= product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "" %>" alt="">
										<div class="product-label">
											<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
											<%= product.getIsNew().equals("yes") ? "<span class='new'> NEW </span>" : ""%>
										</div>
									</div>
									<div class="product-body">
										<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>" title="<%=product.getName()%>"><%= Utils.limitName(product.getName(), 40) %></a></h3>
										<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
										<div class="product-rating">
											<% double starRating = Math.floor(product.getRated());
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
							<% } %>
							
							
						</div>
						<!-- /store products -->
						<% int numPage = length / limit; 
						if(numPage == 0) {
							numPage = 1;
						} else if(length % limit != 0){
							numPage = numPage + 1;
						}
							if(numPage > 0) {
						%>
						<!-- store bottom filter -->
						<div class="store-filter clearfix">
						<span class="store-qty"><%="Hiển thị " + countProduct +" trên " + length + " kết quả" %></span>
							<ul class="store-pagination">
								<% for(int i = 0; i < numPage; i++) { %>
								<% if(pg == (i+1)) { %>
								<li class="active"><%= i + 1 %></li>
								<% } else { %>
								<li ><a onclick="getPage(<%= i * limit + 1%>, <%= i + 1 %>)"><%= i + 1 %></a></li>
								<% }
								}
								%>
							</ul>
						</div>
							<% }
							}
						%>
						</div>
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
