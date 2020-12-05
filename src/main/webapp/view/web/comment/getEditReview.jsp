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
									   ProductModel product = (ProductModel) request.getAttribute("product");
									   CommentModel model = (CommentModel) request.getAttribute("comment");
									%>
		
			
											<% if(user != null) {%>
												<% String mess = (String) request.getAttribute("message");%>
												<% if(mess != null){%>
													<div class="alert alert-danger"><%=mess %></div>
												<%} %>
												<div class="review-form" id="editComment">
													<textarea id="content" class="input" placeholder="Đánh giá của bạn"><%= model != null ? model.getContent() : ""%></textarea>
													<div class="input-rating">
														<span>Đánh giá của bạn: </span>
														<div class="stars">
														<% for(int i = 5 ; i > 0; i--) { %>
															<input id="star<%= i %>" name="star" value="<%= i %>" <%= model.getStar() == i ? "checked" : "" %> type="radio"><label for="star<%= i %>"></label>
														<% } %>
														</div>
													</div>
													<input type="hidden" name="idComment" value="<%= model.getId()%>">
													<input type="hidden" name="userId" value="<%= model.getUserId()%>">
													<input type="hidden" name="option" value="edit">
													<input type="hidden" name="productId" value="<%= model.getProductId()%>">
													<button onclick="sendEditComment()" class="primary-btn">Gửi</button>
													<button onclick="cancelComment(<%= model.getId() %>)" class="primary-btn">Hủy</button>
												</div>
												<div id="oldComment" style="display: none">
												 	<% if(user != null) { 
														if(model.getUserId().equals(user.getId())) {
													%>
												<div style="float: right; font-size: 10px">
													<button onclick="getEditComment(<%=model.getId()%>)">Sửa</button>
													<button onclick= "deleteCmt(<%=model.getId()%>)">Xóa</button>
												</div>
												<% } else if(user.getRole().equals("admin")) { %>
														<div style="float: right; font-size: 10px">
															<button onclick= "deleteCmt(<%=model.getId()%>)">Xóa</button>
															</div>
														<%
														}
													}
													%>
															<p><%=model.getContent()%></p>
												</div>
												<% } %>
								
</body>
</html>