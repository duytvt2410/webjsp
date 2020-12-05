<%@page import="model.UserModel"%>
<%@page import="model.ProductModel"%>
<%@page import="model.Cart"%>
<%@page import="utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<body>
											<% Cart cart = (Cart) session.getAttribute("Cart"); 
											UserModel user = (UserModel) session.getAttribute("User");
												if(cart == null) cart = new Cart();
											%>
							
								
									<a class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
										<i class="fa fa-shopping-cart"></i>
										<span>Giỏ hàng</span>
										<div class="qty"><%= (cart.list() != null ? cart.list().size() : 0) %></div>
									</a>
									<div class="cart-dropdown">
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
									
								
								<!-- /Cart -->
						
</body>
</html>