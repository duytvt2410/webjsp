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
					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="template/web/img/shop01.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Laptop</h3>
								<a href="<%= request.getContextPath() + "/laptop" %>" class="cta-btn">Xem ngay <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="template/web/img/shop03.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Phụ kiện</h3>
								<a href="<%= request.getContextPath() + "/phukien" %>" class="cta-btn">Xem ngay <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->

					<!-- shop -->
					<div class="col-md-4 col-xs-6">
						<div class="shop">
							<div class="shop-img">
								<img src="template/web/img/product14.png" alt="">
							</div>
							<div class="shop-body">
								<h3>Điện thoại</h3>
								<a href="<%= request.getContextPath() + "/dienthoai" %>" class="cta-btn">Xem ngay <i class="fa fa-arrow-circle-right"></i></a>
							</div>
						</div>
					</div>
					<!-- /shop -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
  

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">Sản phẩm mới</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
									<li class="active"><a data-toggle="tab" href="#tab1" >Điện thoại</a></li>
									<li><a data-toggle="tab" href="#tab2">Laptop</a></li>
									<li><a data-toggle="tab" href="#tab3">Phụ kiện</a></li>
								</ul>
							</div>
						</div>
						
					</div>
					<!-- /section title -->
					<% List<ProductModel> listProductIsNewPhone = (List<ProductModel>) request.getAttribute("listProductIsNewPhone");%>
					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1">
									
									<% 
									if(listProductIsNewPhone != null) {
									for(ProductModel product : listProductIsNewPhone) {
										 
										%>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
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
														for(int i = 0; i < 5; i++ ){ 
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
										<!-- /product -->
									<% } 
									}
									%>
										
									</div>
									<div id="slick-nav-1" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								
								<% List<ProductModel> listProductIsNewLaptop = (List<ProductModel>) request.getAttribute("listProductIsNewLaptop");%>
								<!-- tab -->
								<div id="tab2" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-2">
									
									<% 
										if(listProductIsNewLaptop != null) {
											
										for(ProductModel product : listProductIsNewLaptop) { 
											 
										%>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
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
														for(int i = 0; i < 5; i++ ){ 
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
										<!-- /product -->
									<% } 
									}
									%>
									</div>
									<div id="slick-nav-2" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								<% List<ProductModel> listProductIsNewAccessories = (List<ProductModel>) request.getAttribute("listProductIsNewAccessories");%>
								<!-- tab -->
								<div id="tab3" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-3">
									
									<%
									if(listProductIsNewAccessories != null) {
									for(ProductModel product : listProductIsNewAccessories) { 
										 
									%>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
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
														for(int i = 0; i < 5; i++ ){ 
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
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">Xem chi tiết</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn" onclick="addCart(1, <%=product.getId()%>)"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
											</div>
										</div>
										<!-- /product -->
									<% }
									}
									%>
										
									</div>
									<div id="slick-nav-3" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">Sản phẩm đang giảm giá</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
									<li class="active"><a data-toggle="tab" href="#tab4">Điện thoại</a></li>
									<li><a data-toggle="tab" href="#tab5">Laptop</a></li>
									<li><a data-toggle="tab" href="#tab6">Phụ kiện</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->

					<% List<ProductModel> listProductIsPhoneDiscount = (List<ProductModel>) request.getAttribute("listProductIsPhoneDiscount");%>
					<!-- Products tab & slick -->
					<div class="col-md-12">
					
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab4" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-4">
									
									<% 
									if(listProductIsPhoneDiscount != null) {
									for(ProductModel product : listProductIsPhoneDiscount) {
										 
										%>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
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
														for(int i = 0; i < 5; i++ ){ 
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
										<!-- /product -->
									<% } 
									}
									%>
										
									</div>
									<div id="slick-nav-4" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								
								<% List<ProductModel> listProductIsLaptopDiscount = (List<ProductModel>) request.getAttribute("listProductIsLaptopDiscount");%>
								<!-- tab -->
								<div id="tab5" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-5">
									
									<% 
										if(listProductIsLaptopDiscount != null) {
										for(ProductModel product : listProductIsLaptopDiscount) { 
											 
										%>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
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
														for(int i = 0; i < 5; i++ ){ 
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
										<!-- /product -->
									<% } 
									}
									%>
										
									</div>
									<div id="slick-nav-5" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								<% List<ProductModel> listProductIsAccessoriesDiscount = (List<ProductModel>) request.getAttribute("listProductIsAccessoriesDiscount");%>
								<!-- tab -->
								<div id="tab6" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-6">
									
									<%
									if(listProductIsAccessoriesDiscount != null) {
									for(ProductModel product : listProductIsAccessoriesDiscount) { 
										 
									%>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
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
														for(int i = 0; i < 5; i++ ){ 
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
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">Xem chi tiết</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn" onclick="addCart(1, <%=product.getId()%>)"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
											</div>
										</div>
										<!-- /product -->
									<% }
									}
									%>
										
									</div>
									<div id="slick-nav-6" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->
	

	<%@include file="/common/web/footer/footer-template.jsp" %>
	<%@include file="/common/web/footer/script.jsp" %>
	
	
	</body>
</html>
