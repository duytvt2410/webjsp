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
          <h1 class="h3 mb-2 text-gray-800">Danh sách danh mục</h1>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
            	<% 
	            	String isAccessories = (String) request.getAttribute("isAccessories");
	            	String status = (String) request.getAttribute("status");
      
            		List<CategoryModel> listCategory = (List<CategoryModel>) request.getAttribute("listCategory");
            	%>
            		
            		<form action="<%=request.getContextPath() + "/admin-category-list" %>" method="post">
            		<div class="row">
            		<div class="form-group ml-1">
					<label for="category_fillter" class="m-0 font-weight-bold text-primary">Danh mục: </label>
						  <select class="form-control ml-1 custom-select-sm" id="category_filter" required name="isAccessories">
						  <option value="all" <%= (isAccessories.equalsIgnoreCase("all") ? "selected" : "" ) %>>Tất cả</option>
						  <option value="yes" <%= (isAccessories.equalsIgnoreCase("yes") ? "selected" : "" ) %>>Phụ kiện</option>
						  <option value="no" <%= (isAccessories.equalsIgnoreCase("no") ? "selected" : "" ) %>>Không phải phụ kiện</option>
						  </select>
					</div>
					<div class="form-group ml-1">
					<label for="status_fillter" class="m-0 font-weight-bold text-primary">Trạng thái: </label>
						  <select class="form-control ml-1 custom-select-sm" id="status_filter" required name="status">
						    <option value="all" <%= (status.equalsIgnoreCase("all") ? "selected" : "" ) %>>Tất cả</option>
						    <option value="active" <%= (status.equalsIgnoreCase("active") ? "selected" : "" ) %>>Hoạt động</option>
						    <option value="in_active" <%= (status.equalsIgnoreCase("in_active") ? "selected" : "" ) %>>Không hoạt động</option>
						  </select>
						 
					</div>
					<div class="form-group ml-1">
						<br>
						<button title="Lọc" class="btn btn-primary btn-sm" type="submit">Lọc</button>
						 
					</div>
					</div>
					</form>
            	
              
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th width="1%"><input type="checkbox"></th>
                      <th width="20%">Tên danh mục</th>
                      <th width="20%">Loại</th>
                      <th width="10%">Trạng thái</th>
                      <th width="10%">Người tạo</th>
                      <th width="10%">Ngày tạo</th>
                      <th width="4%"></th>
                    </tr>
                  </thead>
                  <tbody>
                    	<% 
                    	
                    	for(CategoryModel category : listCategory) {
                    	%>
                    	<tr>
                    		<td><input type="checkbox"></td>
                     		<td><%= category.getName() %></td>
							<td><%= (category.getIsAccessories().equalsIgnoreCase("yes") ? "Phụ kiện" : "Không phải phụ kiện") %></td>
							<td><%= (category.getStatus().equalsIgnoreCase("active") ? "<i class='fas fa-check-square text-success'></i>" : "<i class='fas fa-ban text-danger'></i>") %></td>
							<td><%= category.getCreateBy() %></td>
							<td><%= category.getCreateDate() %></td>
							<td>
							<div class="hidden-sm hidden-sm btn-group">
								<button title="Xem chi tiết" class="btn btn-success btn-sm" onclick="openViewModal('<%= request.getContextPath() +"/admin-category-view?id=" + category.getId() %>');"><i class="fas fa-search"></i></button>
								<button title="Chỉnh sửa" class="btn btn-info btn-sm" onclick="location.href='<%= request.getContextPath() + "/admin-category-edit?id="+ category.getId() %>';"><i class="fas fa-edit"></i></button>
								<button title="Xóa" class="btn btn-danger btn-sm" onclick="location.href='<%= request.getContextPath() + "/admin-category-delete?id="+ category.getId() %>';"><i class="far fa-trash-alt"></i></button>
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


<%@include file="/common/admin/footer/script.jsp" %>
  <script src="template/admin/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="template/admin/vendor/datatables/dataTables.bootstrap4.min.js"></script>
  <script src="template/admin/js/demo/datatables-demo.js"></script>

</body>

</html>
	