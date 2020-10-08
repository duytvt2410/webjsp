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
									<li class="active"><a data-toggle="tab" href="#tab1">Điện thoại</a></li>
									<li><a data-toggle="tab" href="#tab2">Laptop</a></li>
									<li><a data-toggle="tab" href="#tab3">Phụ kiện</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->
					<% List<ProductModel> listProductIsPhone = (List<ProductModel>) request.getAttribute("listProductIsPhone");%>
					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1">
									
									<% for(ProductModel product : listProductIsPhone) { %>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image()%>" alt="">
												<div class="product-label">
													<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= product.getName() %></a></h3>
												<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="quick-view" onclick="location.href='<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><i class="fa fa-eye"></i><span class="tooltipp">Xem chi tiết</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn" onclick="location.href='<%= request.getContextPath() + "/giohang?option=add&id="+ product.getId()%>'"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
											</div>
										</div>
										<!-- /product -->
									<% } %>
										
									</div>
									<div id="slick-nav-1" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								
								<% List<ProductModel> listProductIsLaptop = (List<ProductModel>) request.getAttribute("listProductIsLaptop");%>
								<!-- tab -->
								<div id="tab2" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-2">
									
									<% for(ProductModel product : listProductIsLaptop) { %>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image()%>)  %>" alt="">
												<div class="product-label">
													<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= product.getName() %></a></h3>
												<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="quick-view" onclick="location.href='<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>'"><i class="fa fa-eye"></i><span class="tooltipp">Xem chi tiết</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn" onclick="location.href='<%= request.getContextPath() + "/giohang?option=add&id="+ product.getId()%>'"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
											</div>
										</div>
										<!-- /product -->
									<% } %>
										
									</div>
									<div id="slick-nav-2" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								<% List<ProductModel> listProductIsAccessories = (List<ProductModel>) request.getAttribute("listProductIsAccessories");%>
								<!-- tab -->
								<div id="tab3" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-3">
									
									<% for(ProductModel product : listProductIsAccessories) { %>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image()%>" alt="">
												<div class="product-label">
													<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= product.getName() %></a></h3>
												<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
											</div>
										</div>
										<!-- /product -->
									<% } %>
										
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
									
									<% for(ProductModel product : listProductIsPhoneDiscount) { %>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image()%>" alt="">
												<div class="product-label">
													<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= product.getName() %></a></h3>
												<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
											</div>
										</div>
										<!-- /product -->
									<% } %>
										
									</div>
									<div id="slick-nav-4" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								
								<% List<ProductModel> listProductIsLaptopDiscount = (List<ProductModel>) request.getAttribute("listProductIsLaptopDiscount");%>
								<!-- tab -->
								<div id="tab5" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-5">
									
									<% for(ProductModel product : listProductIsLaptopDiscount) { %>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image()%>" alt="">
												<div class="product-label">
													<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= product.getName() %></a></h3>
												<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
											</div>
										</div>
										<!-- /product -->
									<% } %>
										
									</div>
									<div id="slick-nav-5" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
								<% List<ProductModel> listProductIsAccessoriesDiscount = (List<ProductModel>) request.getAttribute("listProductIsAccessoriesDiscount");%>
								<!-- tab -->
								<div id="tab6" class="tab-pane">
									<div class="products-slick" data-nav="#slick-nav-6">
									
									<% for(ProductModel product : listProductIsAccessoriesDiscount) { %>
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="data:image/jpg;base64,<%= product.getImageProduct().getBase64Image()%>" alt="">
												<div class="product-label">
													<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
													<span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= product.getName() %></a></h3>
												<h4 class="product-price"><%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> 
												<%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
											</div>
										</div>
										<!-- /product -->
									<% } %>
										
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

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">Top selling</h4>
							<div class="section-nav">
								<div id="slick-nav-3" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-3">
							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product07.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product08.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product09.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- product widget -->
							</div>

							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product01.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product02.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product03.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- product widget -->
							</div>
						</div>
					</div>

					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">Top selling</h4>
							<div class="section-nav">
								<div id="slick-nav-4" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-4">
							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product04.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product05.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product06.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- product widget -->
							</div>

							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product07.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product08.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product09.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- product widget -->
							</div>
						</div>
					</div>

					<div class="clearfix visible-sm visible-xs"></div>

					<div class="col-md-4 col-xs-6">
						<div class="section-title">
							<h4 class="title">Top selling</h4>
							<div class="section-nav">
								<div id="slick-nav-5" class="products-slick-nav"></div>
							</div>
						</div>

						<div class="products-widget-slick" data-nav="#slick-nav-5">
							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product01.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product02.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product03.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- product widget -->
							</div>

							<div>
								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product04.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product05.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- /product widget -->

								<!-- product widget -->
								<div class="product-widget">
									<div class="product-img">
										<img src="template/web/img/product06.png" alt="">
									</div>
									<div class="product-body">
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="#">product name goes here</a></h3>
										<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
									</div>
								</div>
								<!-- product widget -->
							</div>
						</div>
					</div>

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="newsletter">
							<p>Sign Up for the <strong>NEWSLETTER</strong></p>
							<form>
								<input class="input" type="email" placeholder="Enter Your Email">
								<button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
							</form>
							<ul class="newsletter-follow">
								<li>
									<a href="#"><i class="fa fa-facebook"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-twitter"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-instagram"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-pinterest"></i></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->


	<%@include file="/common/web/footer/footer-template.jsp" %>
	<%@include file="/common/web/footer/script.jsp" %>
	</body>
</html>
