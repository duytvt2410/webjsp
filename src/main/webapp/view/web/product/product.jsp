<%@page import="java.util.Set"%>
<%@page import="java.util.Map"%>
<%@page import="model.CommentModel"%>
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
			<%ProductModel product = (ProductModel) request.getAttribute("productModel");
			%>
				<!-- row -->
				<div class="row">
					<!-- Product main img -->
					<div class="col-md-5 col-md-push-2">
						<div id="product-main-img">
						<% if(product != null && product.getUrlImagesDetail() != null) { %>
						<% for(String url : product.getUrlImagesDetail()){  %>
							<div class="product-preview">
								<img src="<%= Utils.getPathImage(url)%>" alt="">
							</div>
						<% } %>
						<% } %>
						</div>
					</div>
					<!-- /Product main img -->

					<!-- Product thumb imgs -->
					<div class="col-md-2  col-md-pull-5">
						<div id="product-imgs">
						<% if(product != null && product.getUrlImagesDetail() != null) { %>
							<% for(String url : product.getUrlImagesDetail()){  %>
							<div class="product-preview">
								<img src="<%= Utils.getPathImage(url) %>" alt="">
							</div>
						<%} %>
						<% } %>
						</div>
					</div>
					<!-- /Product thumb imgs -->

					<!-- Product details -->
					<div class="col-md-5">
						<div class="product-details">
							<h2 class="product-name"><%= product.getName() %></h2>
							<div>
								<div class="product-rating">
									
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
								<a class="review-link" data-toggle="tab" href="#tab3"><%= (int) product.getTotalStar() %> đánh giá | Thêm nhận xét của bạn</a>
							</div>
							<div>
								<h3 class="product-price"> <%= (product.getPricePromotional() != 0 ? Utils.convertToVND(product.getPricePromotional()) : Utils.convertToVND(product.getPrice()))  %> <%= (product.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(product.getPrice()) + "</del>" : "" ) %></h3>
								<span class="product-available"><%= (product.getQty() == 0 ? "Hết hàng" : "") %></span>
							</div>
							<h6 class="text-success">Khuyến mãi</h6>
							<p><%= (product.getPromotionInformation() != null ? product.getPromotionInformation() : "") %></p>

							<div class="add-to-cart">
								<div class="qty-label">
									Số lượng
									<div class="input-number">
										<input id="qtyaddCart" type="number" value="1" min="0" max="3">
										<span class="qty-up">+</span>
										<span class="qty-down">-</span>
									</div>
								</div>
								<button class="add-to-cart-btn" onclick="addCart(document.getElementById('qtyaddCart').value,<%= product.getId()%>)"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
							</div>

							<ul class="product-links">
								<li>Category:</li>
								<li><a href="#">Headphones</a></li>
								<li><a href="#">Accessories</a></li>
							</ul>

							<ul class="product-links">
								<li>Chia sẻ:</li>
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
								<li><a href="#"><i class="fa fa-envelope"></i></a></li>
							</ul>

						</div>
					</div>
					<!-- /Product details -->

					<!-- Product tab -->
					<div class="col-md-12">
						<div id="product-tab">
							<!-- product tab nav -->
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">Thông số kỹ thuật</a></li>
								<li><a data-toggle="tab" href="#tab2">Bài viết mô tả</a></li>
								<li><a data-toggle="tab" href="#tab3">Bình luận, đánh giá</a></li>
							</ul>
							<!-- /product tab nav -->

							<!-- product tab content -->
							<div class="tab-content">
								<!-- tab1  -->
								<div id="tab1" class="tab-pane fade in active">
								<div class="row">
								<div class="col-md-6" style="margin: auto !important; float: none !important">
												<%=product.getSpecifications()%>
										
									</div>
								</div>
										
								</div>
								<!-- /tab1  -->

								<!-- tab2  -->
								<div id="tab2" class="tab-pane fade in">
									<div class="row">
										<div class="col-md-12" style="height: 600px;width: auto; overflow: auto; margin: auto">
												<%=product.getDescription() %>
										</div>
									</div>
								</div>
								<!-- /tab2  -->

								<!-- tab3  -->
								<div id="tab3" class="tab-pane fade in">
									<div id="getReview">
									<div class="row">
										<!-- Rating -->
										<div class="col-md-3">
											<div id="rating">
										
												<div class="rating-avg">
													<span><%=product.getRated()%></span>
													<div class="rating-stars">
														<% 
																 starRating = Math.floor(product.getRated());
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
													
														if(listComment.size() > 0){	
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
																<button onclick= "deleteCmt(<%=comment.getId()%>)">Xóa</button>
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
												<div class="review-form">
													<textarea id="content" class="input" placeholder="Đánh giá của bạn"></textarea>
													<div class="input-rating">
														<span>Đánh giá của bạn: </span>
														<div class="stars">
															<input id="star5" name="star" value="5" type="radio"><label for="star5"></label>
															<input id="star4" name="star" value="4" type="radio"><label for="star4"></label>
															<input id="star3" name="star" value="3" type="radio"><label for="star3"></label>
															<input id="star2" name="star" value="2" type="radio"><label for="star2"></label>
															<input id="star1" name="star" value="1" type="radio"><label for="star1"></label>
														</div>
													</div>
													<input type="hidden" name="productId" value="<%= product != null ? product.getId() : ""%>">
													<input type="hidden" name="userId" value="<%= user != null ? user.getId() : ""%>">
													<input type="hidden" name="option" value="add">
													<button onclick="sendComment()" class="primary-btn">Gửi</button>
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
				</div>
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- Section -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-12">
						<div class="section-title text-center">
							<h3 class="title">Sản phẩm tương tự</h3>
						</div>
					</div>
					
					<% List<ProductModel> listRelatedProduct = (List<ProductModel>) request.getAttribute("listRelatedProduct"); %>
					
					<% for(ProductModel productRelated : listRelatedProduct) { %>
					<!-- product -->
					<div class="col-md-3 col-xs-6">
						<div class="product">
							<div class="product-img">
								<img src="<%= Utils.getPathImage(productRelated.getUrlImageProduct())%>" alt="">
								<div class="product-label">
									<%= (productRelated.getPricePromotional() != 0 ? "<span class='sale'>" + Utils.getDiscoutPercent(product.getPrice(), productRelated.getPricePromotional()) + "</span>" : "" ) %>
								</div>
							</div>
							<div class="product-body">
								<p class="product-category">Category</p>
								<h3 class="product-name"><a href="<%= request.getContextPath() + "/chitietsp?tensp=" + productRelated.getAlias()%>" title="<%=productRelated.getName()%>"><%= Utils.limitName(productRelated.getName(), 40)  %></a></h3>
								<h4 class="product-price"><%= (productRelated.getPricePromotional() != 0 ? Utils.convertToVND(productRelated.getPricePromotional()) : Utils.convertToVND(productRelated.getPrice()))  %> 
								<%= (productRelated.getPricePromotional() != 0 ? "<del class='product-old-price'>" + Utils.convertToVND(productRelated.getPrice()) + "</del>" : "" ) %>
								</h4>
								<div class="product-rating">
								</div>
								<div class="product-btns">
									<button class="quick-view" onclick="location.href='<%= request.getContextPath() + "/chitietsp?tensp=" + productRelated.getAlias()%>'"><i class="fa fa-eye"></i><span class="tooltipp">Xem chi tiết</span></button>
								</div>
							</div>
							<div class="add-to-cart">
										<button class="add-to-cart-btn" onclick="addCart(1, <%=productRelated.getId()%>)"><i class="fa fa-shopping-cart"></i> Thêm vào giỏ</button>
							</div>
						</div>
					</div>
					<!-- /product -->
					<% } %>

					

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /Section -->

		
			<!-- Confirm Modal-->
	<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Bạn có chắc
						muốn xóa chứ?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Chọn "Xóa" để xóa.</div>
				<input type="hidden" id="getIdDelete">
				<div class="modal-footer">
					<button class="btn btn-primary" onclick="confirmDelete()">Xóa</button>
					<button class="btn btn-secondary" id="offModel" type="button" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>


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
	
	function getPage(first, page){
		var url = window.location.href; 
		if(url.includes("?")) {
			url += '&phantrang=yes&first=' + first + '&page=' + page;
		} else {
			url += '?phantrang=yes&first=' + first + '&page=' + page;
		}
		$.get(url, function(responseText) {
	        $("#getReview").html(responseText);
		});
	}
	
	function sendComment(){
		var id =  $('#review-form input[name="idComment"]').val();
		var userId =  $('#review-form input[name="userId"]').val();
		var productId =  $('#review-form input[name="productId"]').val();
		var content =  $('#review-form #content').val();
		var star =  $('#review-form input[name="star"]:checked').val();
		var option =  $('#review-form input[name="option"]').val();
		
		var url = '<%= request.getContextPath() + "/binhluan?id=" %>' + id + '&user_id=' + userId +'&product_id='+productId + '&content='+ content + '&star=' + star +'&option='+option; 
		$.get(url, function(responseText) {
	        $("#getReview").html(responseText);
		});	
	}
	
	function sendEditComment() {
		var id =  $('#editComment input[name="idComment"]').val();
		var userId =  $('#editComment input[name="userId"]').val();
		var productId =  $('#editComment input[name="productId"]').val();
		var content =  $('#editComment #content').val();
		var star =  $('#editComment input[name="star"]:checked').val();
		var option =  $('#editComment input[name="option"]').val();
		
		var url = '<%= request.getContextPath() + "/binhluan?id=" %>' + id + '&user_id=' + userId +'&product_id='+productId + '&content='+ content + '&star=' + star +'&option='+option; 
		$.get(url, function(responseText) {
	        $("#getReview").html(responseText);
		});	
	}
	
	function cancelComment(id) {
		$("#review-form").css("display", "block");
		$("#cmt-" + id +" #editComment").css("display", "none");
		$("#oldComment").css("display", "block");
	}
	
	function getEditComment(id){
		$("#review-form").css("display", "none");
		
		var url = '<%= request.getContextPath() + "/suabinhluan?id=" %>' + id; 
		$.get(url, function(responseText) {
	        $("#cmt-" + id).html(responseText);
		});	
	}
	
	 function deleteCmt(id) {
			$('#getIdDelete').val(id);
				$('#confirmModal').modal({
					show: true, 
					keyboard: false, 
					backdrop: 'static'
				});		
		}
	  

	  function confirmDelete() {
		  var id = $('#getIdDelete').val();
		  
			$('#offModel').click();	
		   
		  var url = '<%= request.getContextPath() + "/binhluan?product_id="+ product.getId() + "&option=delete&id="%>' + id; 
			$.get(url, function(responseText) {
		        $("#getReview").html(responseText);
			});	
		}
	  
	  $('#qtyaddCart').on("change", function(){
		  var num = parseInt($("#qtyaddCart").val());
		  if(num < 0) {
			  $("#qtyaddCart").val("1");
		  } else if(num > 3){
			  $("#qtyaddCart").val("3");
		  }
	  });
	
	</script>
	
	</body>
</html>
