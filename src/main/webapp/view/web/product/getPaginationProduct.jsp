<%@page import="java.util.List"%>
<%@page import="model.UserModel"%>
<%@page import="model.ProductModel"%>
<%@page import="model.Cart"%>
<%@page import="utils.Utils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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
						<% 
						int count = 0;
						for(ProductModel product : listProduct) { 
							count++;
						%>
							<!-- product -->
							<div class="col-md-4 col-sm-6" style="height: 500px">
								<div class="product">
									<div class="product-img">
										<img src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" alt="">
										<div class="product-label">
											<%= (product.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), product.getPricePromotional()) + "</span>" : "" ) %>
											
										</div>
									</div>
									<div class="product-body">
										<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + product.getAlias()%>"><%= Utils.limitName(product.getName(), 40)  %></a></h3>
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
						<span class="store-qty"><%="Hiển thị "+  count+ " trên " + length + " kết quả" %></span>
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
								
</body>
</html>