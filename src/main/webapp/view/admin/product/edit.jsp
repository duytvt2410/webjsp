<%@page import="model.ImagesModel"%>
<%@page import="utils.Utils"%>
<%@page import="model.ProductClassifyModel"%>
<%@page import="model.ProductModel"%>
<%@page import="model.ClassifyModel"%>
<%@page import="service.imp.ClassifyService"%>
<%@page import="java.util.List"%>
<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<%@include file="/common/admin/header/tag-head.jsp" %>


<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    	<%@include file="/common/admin/menu/menu.jsp" %>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <%@include file="/common/admin/header/header-template.jsp" %>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          
          <!-- Content Row -->

          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-12 col-lg-12">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Sửa sản phẩm</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Lựa chọn khác:</div>
                      <a class="dropdown-item" href="<%=request.getContextPath() + "/admin-product-list" %>">Xem danh sách sản phẩm</a>
                    </div>
                  </div>
                </div>
                <% List<CategoryModel> listCategory = (List<CategoryModel>) request.getAttribute("listCategory"); 
              		  ProductModel product = (ProductModel) request.getAttribute("productModel");
                %>
                <!-- Card Body -->
                <div class="card-body">
                 <div class="container">
					  <form action="<%=request.getContextPath() + "/admin-product-edit" %>" enctype="multipart/form-data" method="POST" class="needs-validation" novalidate>
					  <div class="form-group">
						  <label for="category" class="text-primary font-weight-bold">Danh mục:</label>
						  <select class="form-control" id="category" required name="category" 
						  onchange="getBrand(this.value, '', '')">
						    <option value="">-- Chọn --</option>
						    <% 
						    	
						    	if(listCategory != null) {
							    	for(CategoryModel category : listCategory) { 
							  %>
							     		<option value="<%=category.getAlias()%>" 
							     		<%= (product != null ? (product.getCategoryAlias().equals(category.getAlias()) ? "selected" : "") : "") %>
							     		><%=category.getName()%></option>
							<% 		} 
						    	}
						    
						    %>
						  </select>
					      <div class="invalid-feedback">Vui lòng chọn danh mục</div>
					      <a href="<%=request.getContextPath() + "/admin-category-add"%>" class="small">- Thêm danh mục</a>
						</div>
						<div class="form-group">
						  <div id="get_brand">
						  <label for="brand" class="text-primary font-weight-bold">Nhãn hàng:</label>
						  <select class="form-control" id="brand" required name="brand" disabled="disabled">
						    <option >-- Chọn --</option>
						  </select>
					      </div>
						</div>
						
						<div class="form-group">
						  <div id="get_classify">
						  <label for="classify" class="text-primary font-weight-bold">Nhóm sản phẩm:</label>
						  <input type="text"  class="form-control" value="Chọn" disabled="disabled">
					      </div>
						</div>
						
					    <div class="form-group">
					      <label for="name" class="text-primary font-weight-bold">Tên sản phẩm:</label>
					      <input type="text" class="form-control" id="name" maxlength="300" 
					      placeholder="Nhập tên sản phẩm. VD: Samsung A5 2017, Iphone 10..." 
					      name="name" required value="<%= (product != null ? product.getName() : "")%>">
					      
					      <div class="invalid-feedback">
					      	<span>- Không được bỏ trống</span> <br>
					      	<span>- Độ dài tối đa 300 ký tự</span>
					      </div>
					    </div>
					    <div class="row">
					    <div class="form-group col-lg-5 col-md-6">
					     <label for="price" class="text-primary font-weight-bold">Giá sản phẩm:</label>
					      <div class="input-group mb-3">
						    <input type="text" id= "price" name="price" class="form-control" placeholder="Nhập giá sản phẩm. VD: 1990000, 6690000" required
						    maxlength="14" value="<%= (product != null ? product.getPrice() : "0")%>">
						    <div class="input-group-append">
						      <span class="input-group-text">VNĐ</span>
						    </div>
						  </div>
					      <div class="invalid-feedback">
					      	<span>- Không được bỏ trống</span> <br>
					      	<span>- Độ dài tối đa 14 ký tự</span>
					      </div>
					    </div>
					    <div class="form-group col-lg-5 col-md-6">
					     <label for="pricePromotional" class="text-primary font-weight-bold">Giá khuyến mãi:</label>
					      <div class="input-group mb-3">
						    <input type="text" id="pricePromotional" name="pricePromotional" class="form-control" placeholder="Nhập giá sản phẩm. VD: 1990000, 6690000" required
						    maxlength="14" value="<%= (product != null ? product.getPricePromotional() : "0")%>">
						    <div class="input-group-append">
						      <span class="input-group-text">VNĐ</span>
						    </div>
						  </div>
					      <div class="invalid-feedback">
					      	<span>- Không được bỏ trống</span> <br>
					      	<span>- Độ dài tối đa 14 ký tự</span>
					      </div>
					    </div>
					     <div class="form-group col-lg-2 col-md-12">
					      <label for="qty" class="text-primary font-weight-bold">Số lượng:</label>
					      <input type="number" name="qty" class="form-control" id="qty" min="0" max = "100" value="<%= (product != null ? product.getQty() : "0")%>">
					      
					      <div class="invalid-feedback">
					      	<span>- Từ 0 đến 100</span>
					      </div>
					    </div>
					    </div>
					    <div class="form-group">
					    <label for="image" class="text-primary font-weight-bold">Ảnh sản phẩm:</label>
						<div id="dropzoneFromOne" class="dropzone"></div>
							<div id="showImage">
								<% if(product != null ) { %>
								<img style="height: 70px" width="auto" src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>" class="img-thumbnail">
								<%} %>
							</div>
							
						</div><br>
						<div class="form-group">
					    <label for="detail-image" class="text-primary font-weight-bold">Ảnh chi tiêt:</label>
						<div id="dropzoneFrom" class="dropzone"></div>
							<div id="showImages">
								<% if(product != null && product.getUrlImagesDetail() != null) { 
									for(String images : product.getUrlImagesDetail()) {
								%>
									
								<img style="height: 70px" width="auto" src="<%= Utils.getPathImage(images) %>" class="img-thumbnail">
								<%	} 
									}
								%>
							</div>
					      
						</div>
						<div class="form-group">
							<label for="promotionInformation" class="text-primary font-weight-bold">Thông tin khuyến mãi:</label>
							<textarea id="promotionInformation" name="promotionInformation" cols="80" rows="10">
								<%= (product != null ? product.getPromotionInformation() : "") %>
							</textarea>
							<script>
								CKEDITOR.replace( 'promotionInformation' );
							 </script>  
						</div>
						<div class="form-group">
							<label for="specifications" class="text-primary font-weight-bold">Thông số kỹ thuật:</label>
							<textarea id="specifications" name="specifications" cols="80" rows="10">
								<%= (product != null ? product.getSpecifications() : "") %>
							</textarea>
							<script>
								CKEDITOR.replace( 'specifications' );
							 </script>  
						</div>
						<div class="form-group">
							<label for="description" class="text-primary font-weight-bold">Bài viết mô tả:</label>
							<textarea id="description" name="description" cols="80" rows="10">
								<%= (product != null ? product.getDescription() : "") %>
							</textarea>
							<script>
								CKEDITOR.replace( 'description' );
							 </script>  
						</div>
						<input type="hidden" name="file-images" id="file-images">
						<input type="hidden" name="file-image" id="file-image">
					    <input type="hidden" value="edit" name="option">
					    <input type="hidden" value="<%=product.getId() %>" name="id">
					    <button type="submit" class="btn btn-primary">Sửa</button>
					  </form>
					</div>
                </div>
              </div>
            </div> 
        </div>
        <!-- /.container-fluid -->
      <!-- End of Main Content -->

      <!-- Footer -->
      <%@include file="/common/admin/footer/footer-template.jsp" %>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->


<%@include file="/common/admin/footer/script.jsp" %>

<script type="text/javascript">
function getBrand(category, brand, classify){
	url = '<%= request.getContextPath() + "/admin-product-getBrand?category=" %>' + category + '&brand=' + brand; 
	$.get(url, function(responseText) {
        $("#get_brand").html(responseText);
	});	
	url = '<%= request.getContextPath() + "/admin-product-getClassify?category=" %>' + category + '&classify=' + classify; 
	
	$.get(url, function(responseText) {
        $("#get_classify").html(responseText);
	});	
}

	function convertToVND(money) {
		var res = 0;
		if(money == "") {
			return 0;
		} else {
			var moneySplitDot = money.split(".");
			var moneyString = "";
			var i;
			for (i = 0; i < moneySplitDot.length; i++) {
				moneyString += moneySplitDot[i];
			}
			if(moneyString != "") {
			 	res = parseInt(moneyString);
			} else {
				res = parseInt(money);
			}
		}
		return res.toLocaleString('el-GR');
	}
	
	$(document).ready(function(){
	  if($('#category').val() != null) {
		  getBrand($('#category').val(), '<%= (product != null ? product.getBrandId() : "") %>' , '<%= (product != null ? product.getStringIdClassify() : "") %>');
	  }
	  
	  if($('#price').val() != null) {
		  	var money = $( "#price" ).val();
			 $( "#price" ).val(convertToVND(money));
	  }
	  
	  if($('#pricePromotional').val() != null) {
		  	var money = $( "#price" ).val();
			 $( "#price" ).val(convertToVND(money));
	  }
	  
	  Dropzone.options.dropzoneFrom = {
	  url: '<%= Utils.getUrlUploadImage("FileUploadServlet")%>',
	  addRemoveLinks: true,
	  parallelUploads: 1000,
	  maxThumbnailFilesize: 100,
	  autoProcessQueue: true,
	  acceptedFiles:".png,.jpg,.gif,.bmp,.jpeg",
	  init: function() {
		  this.on("complete", function (file) {
	          var oldName = $("#file-images").val();
	          if(oldName == "") {
	        	  $( "#file-images" ).val(file.name);
	          } else {
	        	  $( "#file-images" ).val(oldName + "dt14082410dt" + file.name);
	          }
	          
	       });
	      this.on("removedfile", function (file) {
	         $.ajax({
	           url: '<%= Utils.getUrlUploadImage("DeleteFile?name=")%>' + file.name,
	           type: 'GET'
	       });
	         var arr = $("#file-images").val().split('dt14082410dt');
	         var l = arr.length;
	         for(var i = 0; i < arr.length; i++) {
				 if(arr[i] == file.name) {
					 arr.splice(i, 1);
					 l--;
					 break;
	        	 }
	         }
	         var res = "";
	         for(var i = 0; i < arr.length; i++) {
	        	 if(i + 1 == arr.length) {
	        		 res += arr[i];
	        	 } else {
	        		 res += arr[i] + "dt14082410dt";
	        	 }
	        	 
	         }
	         $( "#file-images" ).val(res);
	      });

	}
	 };
	 
	 Dropzone.options.dropzoneFromOne = {
			  url: '<%= Utils.getUrlUploadImage("FileUploadServlet")%>',
			  addRemoveLinks: true,
			  parallelUploads: 1000,
			  maxThumbnailFilesize: 100,
			  autoProcessQueue: true,
			  acceptedFiles:".png,.jpg,.gif,.bmp,.jpeg",
			  maxFiles: 1,
			  init: function() {
					  this.on("maxfilesexceeded", function(file) {
				            this.removeAllFiles();
				            this.addFile(file);
				      });
					  this.on("complete", function (file) {
				          $("#file-image").val(file.name);
				      });
			        this.on("removedfile", function (file) {
			            
			                $.ajax({
			                    url: '<%= Utils.getUrlUploadImage("DeleteFile?name=")%>' + file.name,
			                    type: 'GET'
			                });
			            
			        });
			  }
	 };
	});
	
	$('#price').on('input', function() {
		var money = $( "#price" ).val();
		 $( "#price" ).val(convertToVND(money));
	});
	
	$('#pricePromotional').on('input', function() {
		var money = $( "#pricePromotional" ).val();
		 $( "#pricePromotional" ).val(convertToVND(money));
	});
	
	
	
</script>

</body>

</html>
	