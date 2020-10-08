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
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<ul class="breadcrumb-tree">
							<li><a href="#">Home</a></li>
							<li><a href="#">All Categories</a></li>
							<li><a href="#">Accessories</a></li>
							<li class="active">Headphones (227,490 Results)</li>
						</ul>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->
		<% 
		List<CategoryModel> listCategory = (List<CategoryModel>) request.getAttribute("listCategory"); 
		List<BrandModel> listBrand = (List<BrandModel>) request.getAttribute("listBrand");
		List<ClassifyModel> listClassify = (List<ClassifyModel>) request.getAttribute("listClassify");
		Long nsx = 0L;
		String nhomsp[] = null;
		String theloai = (String) request.getAttribute("theloai");
		if(request.getParameter("nsx") != null && !request.getParameter("nsx").equals("")) nsx = Long.parseLong(request.getParameter("nsx"));
		if(request.getParameterValues("nhomsp") != null) nhomsp = request.getParameterValues("nhomsp");
		 
		Collections.sort(listClassify, new Comparator<ClassifyModel>() {
			@Override
			public int compare(ClassifyModel c2, ClassifyModel c1) {

				return c1.getName().compareTo(c2.getName());
			}
		});
		%>
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- ASIDE -->
					<div id="aside" class="col-md-3">
					
						<% if(listCategory != null) { %>
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
						<% if(listBrand != null) { %>
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

						<% if(listClassify != null) { %>
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
												if(classify.getId().equals(Long.parseLong(isChoose))){
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
						<!-- aside Widget -->
						<div class="aside">
							<h3 class="aside-title">Top selling</h3>
							<div class="product-widget">
								<div class="product-img">
									<img src="./img/product01.png" alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name"><a href="#">product name goes here</a></h3>
									<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
								</div>
							</div>

							<div class="product-widget">
								<div class="product-img">
									<img src="./img/product02.png" alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name"><a href="#">product name goes here</a></h3>
									<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
								</div>
							</div>

							<div class="product-widget">
								<div class="product-img">
									<img src="./img/product03.png" alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name"><a href="#">product name goes here</a></h3>
									<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
								</div>
							</div>
						</div>
						<!-- /aside Widget -->
					
					</div>
					
					<!-- /ASIDE -->

					<!-- STORE -->
					<div id="store" class="col-md-9">
						<!-- store top filter -->
						<div class="store-filter clearfix">
							<div class="store-sort">
								<label>
									Sort By:
									<select class="input-select">
										<option value="0">Popular</option>
										<option value="1">Position</option>
									</select>
								</label>

								<label>
									Show:
									<select class="input-select">
										<option value="0">20</option>
										<option value="1">50</option>
									</select>
								</label>
							</div>
							<ul class="store-grid">
								<li class="active"><i class="fa fa-th"></i></li>
								<li><a href="#"><i class="fa fa-th-list"></i></a></li>
							</ul>
						</div>
						<!-- /store top filter -->
						<% 
						List<ProductModel> listProduct = (List<ProductModel>) request.getAttribute("listProduct"); %>
						<!-- store products -->
						<div class="row">
						<% for(ProductModel product : listProduct) { %>
							<!-- product -->
							<div class="col-md-4 col-xs-6">
								<div class="product">
									<div class="product-img">
										<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image() %>" alt="">
										<div class="product-label">
											<span class="sale">-30%</span>
											<span class="new">NEW</span>
										</div>
									</div>
									<div class="product-body">
										<h3 class="product-name"><a href="#"><%= product.getName() %></a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
										<div class="product-rating">
											<i class="fa fa-star"></i>
											<i class="fa fa-star"></i>
											<i class="fa fa-star"></i>
											<i class="fa fa-star"></i>
											<i class="fa fa-star"></i>
										</div>
										<div class="product-btns">
											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
										</div>
									</div>
									<div class="add-to-cart">
										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
									</div>
								</div>
							</div>
							<!-- /product -->
							<% } %>
							
						</div>
						<!-- /store products -->

						<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<span class="store-qty">Showing 20-100 products</span>
							<ul class="store-pagination">
								<li class="active">1</li>
								<li><a href="#">2</a></li>
								<li><a href="#">3</a></li>
								<li><a href="#">4</a></li>
								<li><a href="#"><i class="fa fa-angle-right"></i></a></li>
							</ul>
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
