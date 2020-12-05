<%@page import="java.util.List"%>
<%@page import="utils.Utils"%>
<%@page import="model.CommentModel"%>
<%@page import="model.ProductModel"%>
<%@page import="utils.SessionUtil"%>
<%@page import="model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

									<% UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User"); 
									   ProductModel product = (ProductModel) request.getAttribute("productModel");
									   CommentModel model = (CommentModel) request.getAttribute("comment");
									%>
		
									<div class="row">
										<!-- Rating -->
										<div class="col-md-3">
											<div id="rating">
										
												<div class="rating-avg">
													<span><%=product.getRated()%></span>
													<div class="rating-stars">
														<% 
																double starRating = Math.floor(product.getRated());
																for(int i = 0; i < 5; i++ ){ %>
																<% if(starRating > 0) { 
																	starRating--;
																%>
																<i class="fa fa-star"></i>
																<% } else {%>
																<i class="fa fa-star-o empty"></i>
																<% }
																}
																%>
													</div>
												</div>
												<ul class="rating"> 
												
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
														</div>
														<div class="rating-progress">
															<div style="width: <%= product.getStar5() / product.getTotalStar() * 100 %>%;"></div>
														</div>
														<span class="sum"><%= (int) product.getStar5() %></span>
													</li>
												
													
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: <%= product.getStar4() / product.getTotalStar() * 100 %>%;"></div>
														</div>
														<span class="sum"><%= (int) product.getStar4() %></span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: <%= product.getStar3() / product.getTotalStar() * 100 %>%;"></div>
														</div>
														<span class="sum"><%= (int) product.getStar3() %></span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: <%= product.getStar2() / product.getTotalStar() * 100 %>%;"></div>
														</div>
														<span class="sum"><%= (int) product.getStar2() %></span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: <%= product.getStar1() / product.getTotalStar() * 100 %>%;"></div>
														</div>
														<span class="sum"><%= (int) product.getStar1() %></span>
													</li>
												</ul>
											</div>
										</div>
										<!-- /Rating -->

										<!-- Reviews -->
										<div class="col-md-6">
											<div id="reviews">
												<ul class="reviews">
												<% List<CommentModel> listComment = (List<CommentModel>) request.getAttribute("listComment");  %>
												<% if (product != null) {
														if(product.getListComment().size() > 0){		
														for(CommentModel comment : listComment) {
													%>
													<li>
														<div class="review-heading">
															<h6 class="name"><%=comment.getNameUser() %></h6>
															<p class="date"><%=Utils.formatDate(comment.getCreateDate()) %></p>
															<div class="review-rating">
																<% 
																int star = comment.getStar();
																for(int i = 0; i < 5; i++ ){ %>
																<% if(star > 0) { 
																	star--;
																%>
																<i class="fa fa-star"></i>
																<% } else {%>
																<i class="fa fa-star-o empty"></i>
																<% }
																}
																%>
															</div>
														</div>
														<div class="review-body">
															<div id="cmt-<%=comment.getId()%>">
															<% if(user != null) { 
																if(comment.getUserId().equals(user.getId())) {
															%>
															<div style="float: right; font-size: 10px">
																<button onclick="getEditComment(<%=comment.getId()%>)">Sửa</button>
																<button onclick= "deleteCmt(<%=comment.getId()%>)" >Xóa</button>
															</div>
															<% } else if(user.getRole().equals("admin")) { %>
															<div style="float: right; font-size: 10px">
																<button onclick= "deleteCmt(<%=comment.getId()%>)" >Xóa</button>
															</div>
															<%
															 }
															}
															%>
															<p><%=comment.getContent()%></p>
															</div>
														</div>
													</li>
													<% }
													} else {
														%>
														<li> 
														<div class="review-body">
															<p>Chưa có đánh giá nào cho sản phẩm này</p>
														</div>
														</li>
													<%
													}
												}%>
												</ul>
												<% if(listComment.size() > 0) { 
													int limit = (int) request.getAttribute("limit");
													int length = (int) request.getAttribute("length");
													int first = (int) request.getAttribute("first");
													int pg = (int) request.getAttribute("page");
													int end = first + (length - first) ;
													if(end - limit - first >= 0) end = first + limit - 1;
													int numPage = length / limit; 
													if(numPage == 0) {
														numPage = 1;
													} else if(length % limit != 0){
														numPage = numPage + 1;
													}
														if(numPage > 0) {
												%>
												<ul class="reviews-pagination">
													<% for(int i = 0; i < numPage; i++) { %>
														<% if(pg == (i+1)) { %>
														<li class="active"><%= i + 1 %></li>
													<% } else { %>
														<li ><a onclick="getPage(<%= i * limit + 1%>, <%= i + 1 %>)"><%= i + 1 %></a></li>
													<% }
													}
													%>
												</ul>
												<% } 
												
												}%>
											</div>
										</div>
										<!-- /Reviews -->

										<!-- Review Form -->
										<div class="col-md-3">
										
											<div id="review-form">
											<% if(user != null) {%>
												<% String mess = (String) request.getAttribute("message");%>
												<% if(mess != null){%>
													<div class="alert alert-danger"><%=mess %></div>
												<%} %>
												<div class="review-form">
													<textarea id="content" class="input" placeholder="Đánh giá của bạn"><%= model != null ? model.getContent() : ""%></textarea>
													<div class="input-rating">
														<span>Đánh giá của bạn: </span>
														<div class="stars">
														<% for(int i = 5 ; i > 0; i--) { %>
															<input id="star<%= i %>" name="star" value="<%= i %>" <%= model != null ? (model.getStar() == i ? "checked" : "") : "" %> type="radio"><label for="star<%= i %>"></label>
														<% } %>
														</div>
													</div>
													<input type="hidden" name="productId" value="<%= product != null ? product.getId() : ""%>">
													<input type="hidden" name="userId" value="<%= user != null ? user.getId() : ""%>">
													<input type="hidden" name="option" value="add">
													<button onClick="sendComment()" class="primary-btn">Gửi</button>
												</div>
												<% } else { %>
													<button onclick ="location.href='<%= request.getContextPath() + "/dangnhap" %>'" class="primary-btn">Đăng nhập để bình luận</button>
												<% } %>
											</div>
										</div>
										<!-- /Review Form -->
									</div>
								</div>
								<!-- /tab3  -->
							</div>
							<!-- /product tab content  -->
						</div>
					</div>
					<!-- /product tab -->
				</div>
				<!-- /row -->
</body>
</html>