<%@page import="utils.SessionUtil"%>
<%@page import="model.UserModel"%>
<%@page import="utils.Utils"%>
<%@page import="model.ProductModel"%>
<%@page import="model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- HEADER -->
		<header>
			<% UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User"); %>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
					<ul class="header-links pull-left">
						<li><a href="#"><i class="fa fa-phone"></i> 0833532303</a></li>
						<li><a href="#"><i class="fa fa-envelope-o"></i> email@email.com</a></li>
						<li><a href="#"><i class="fa fa-map-marker"></i> 1734 Stonecoal Road</a></li>
					</ul>
					<ul class="header-links pull-right ">
					<% if(user == null) { %>
						<li><a href="<%= request.getContextPath() + "/dangnhap"%>"><i class="fa fa-sign-in" aria-hidden="true"></i> Đăng nhập</a></li>
						<li><a href="<%= request.getContextPath() + "/dangky"%>"><i class="fa fa-user-o"></i> Đăng ký</a></li>
					<% } else { %>
						<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user-o "></i> Xin chào <%= user.getFullName() %></a>
							 <div class="dropdown-menu p-3" style="padding: 10px">
							 	<% if(user.getRole().equals("admin")) { %>
							      <a class="dropdown-item" style="color: black" href="<%=request.getContextPath() + "/admin-home"%>">Vào trang admin</a><br>
							    <% } %>
							      <a class="dropdown-item" style="color: black" href="<%=request.getContextPath() + "/doimatkhau"%>">Đổi mật khẩu</a><br>
							      <a class="dropdown-item" style="color: black" href="<%=request.getContextPath() + "/donhang"%>">Đơn hàng</a><br>
							      <a class="dropdown-item" style="color: black" href="<%=request.getContextPath() + "/dangxuat"%>">Đăng xuất</a><br>
							    </div>
						</li>
					<% } %>
					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="<%= request.getContextPath() + "/trangchu" %>" class="logo">
									<img src="template/web/img/logo.png" alt="">
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form action="<%=request.getContextPath() + "/timkiem"%>">
									<input name="tukhoa" style="border-radius: 40px 0 0 40px; width: calc(100% - 160px);" class="input" placeholder="Nhập từ khóa">
									
									<button type="submit" class="search-btn">Tìm</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						<!-- ACCOUNT -->
						<div class="col-md-3 clearfix">
							<div class="header-ctn">

								<!-- Cart -->
								
								<% Cart cart = (Cart) session.getAttribute("Cart"); 
									if(cart == null) cart = new Cart();
								%>
								
								<div class="dropdown" id="getMiniCart">
								
									<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
										<i class="fa fa-shopping-cart"></i>
										<span>Giỏ hàng</span>
										<div class="qty"><%= (cart.list() != null ? cart.list().size() : 0) %></div>
									</a>
									<div class="cart-dropdown" style="right: -40px !important">
										<div class="cart-list">
										<% for(ProductModel productInCart : cart.list()) { %>
											<div class="product-widget">
												<div class="product-img">
													<img src="<%=(productInCart.getUrlImageProduct() != null ? Utils.getPathImage(productInCart.getUrlImageProduct()) : "")%>" alt="">
												</div>
												<div class="product-body">
													<h3 class="product-name"><a href="#"><%= productInCart.getName() %></a></h3>
													<h4 class="product-price"><span class="qty"><%= productInCart.getQtyInCart() %>x</span><%= (productInCart.getPricePromotional() != 0 ? Utils.convertToVND(productInCart.getPricePromotional()) : Utils.convertToVND(productInCart.getPrice())) %></h4>
												</div>
												<button class="delete" onclick="removeItemCart(<%=productInCart.getId()%>)"><i class="fa fa-close"></i></button>
											</div>
										<% } %>
										</div>
										<div class="cart-summary">
											<small><%= cart.list().size() %> Sản phẩm</small>
											<h5>Tổng giá: <%= Utils.convertToVND(cart.total()) %></h5>
										</div>
										<div class="cart-btns">
											<a href="<%= request.getContextPath() + "/giohang"%>">Xem giỏ</a>
											<a href="<%= request.getContextPath() + (user != null ? "/thanhtoan" : "/dangnhap")%>">Thanh toán  <i class="fa fa-arrow-circle-right"></i></a>
										</div>
									</div>
									
								</div>
								<!-- /Cart -->
							
								<!-- Menu Toogle -->
								<div class="menu-toggle">
									<a href="#">
										<i class="fa fa-bars"></i>
										<span>Menu</span>
									</a>
								</div>
								<!-- /Menu Toogle -->
							</div>
						</div>
						<!-- /ACCOUNT -->
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		 <!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="li_active"><a href="<%= request.getContextPath() + "/trangchu"%>">Trang chủ</a></li>
						<li class="li_active"><a href="<%= request.getContextPath() + "/dienthoai"%>">Điện thoại</a></li>
						<li class="li_active"><a href="<%= request.getContextPath() + "/laptop"%>">Laptop</a></li>
						<li class="li_active"><a href="<%= request.getContextPath() + "/phukien"%>">Phụ kiện</a></li>
						
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
