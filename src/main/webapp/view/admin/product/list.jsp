<%@page import="model.ProductModel"%>
<%@page import="model.ClassifyModel"%>
<%@page import="utils.Utils"%>
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

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">Danh sách nhóm sản phẩm</h1>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
            	
            	<div class = "row">
            		<div class="form-inline">
					<label for="category_fillter" class="m-0 font-weight-bold text-primary">Danh mục: </label>
						  <select class="form-control ml-1 custom-select-sm" id="category_filter" required name="category_filter">
						  <option value="all" >Tất cả</option>
						  
						  </select>
					<label for="status_fillter" class="m-0 font-weight-bold text-primary ml-2">Trạng thái: </label>
						  <select class="form-control ml-1 custom-select-sm" id="status_filter" required name="status_filter">
						   
						  </select>
						  <button title="Lọc" class="btn btn-primary btn-sm ml-1" onclick="location.href='<%= request.getContextPath() + "/admin-classify-list?categoryAlias="%>'+$('#category_filter').val() + '&status=' + $('#status_filter').val();">Lọc</button>
					</div>
            	</div>
              
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th width="1%"><input type="checkbox"></th>
                      <th width="20%">Tên sản phẩm</th>
                      <th width="20%">Giá sản phẩm</th>
                      <th width="20%">Ảnh sản phẩm</th>
                      <th width="10%">Trạng thái</th>
                      <th width="10%">Người tạo</th>
                      <th width="10%">Ngày tạo</th>
                      <th width="4%"></th>
                    </tr>
                  </thead>
                  <tbody>
                    	<% 
                    	List<ProductModel> listProduct = (List<ProductModel>) request.getAttribute("listProduct");
                    	for(ProductModel product : listProduct) {
                    	%>
                    	<tr>
                    		<td><input type="checkbox"></td>
                     		<td><%= product.getName() %></td>
							<td><%= Utils.convertToVND(product.getPrice()) %></td>
							<td><img style="height: 70px" src="data:image/jpg;base64,<%=product.getImageProduct().getBase64Image()%>"></td>
							<td><%= (product.getStatus().equalsIgnoreCase("active") ? "<i class='fas fa-check-square text-success'></i>" : "<i class='fas fa-ban text-danger'></i>") %></td>
							<td><%= product.getCreateBy() %></td>
							<td><%= product.getCreateDate() %></td>
							<td>
							<div class="hidden-sm hidden-sm btn-group">
								<button title="Xem chi tiết" class="btn btn-success btn-sm" onclick="openViewModal('<%= request.getContextPath() + "/admin-product-view?id="+ product.getId()%>');"><i class="fas fa-search"></i></button>
								<button title="Chỉnh sửa" class="btn btn-info btn-sm" onclick="location.href='<%= request.getContextPath() + "/admin-product-edit?id="+ product.getId()%>';"><i class="fas fa-edit"></i></button>
								<button title="Xóa" class="btn btn-danger btn-sm"><i class="far fa-trash-alt"></i></button>
							</div>
							</td>
						</tr>
						<% } %>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <%@include file="/common/admin/footer/footer-template.jsp" %>
      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->
   
  <!--MESSAGE Modal-->
<div id="viewModal" class="modal fade">
	<div class="modal-dialog modal-confirm">
		<div class="modal-content">
			<div class="modal-header">	
				 <h6 class="m-0 font-weight-bold text-primary">Xem chi tiết</h6>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>

			<div id="getView"></div>
		</div>
	</div>
</div>     

<script type="text/javascript">
function openViewModal(id){
	var params = "view="+id;
	var url = '<%= request.getContextPath() + "/admin-ajax-category?"%>' + params;
	$.get(url, function(responseText) {
        $("#getView").html(responseText);
	});
	$('#viewModal').modal({
		show: true, 
		keyboard: false, 
		backdrop: 'static'
	});		
}
</script>


<%@include file="/common/admin/footer/script.jsp" %>
<script src="template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script src="template/admin/js/demo/datatables-demo.js"></script>

</body>

</html>
	