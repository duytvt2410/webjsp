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
                  <h6 class="m-0 font-weight-bold text-primary">Thêm nhóm sản phẩm</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Lựa chọn khác:</div>
                      <a class="dropdown-item" href="<%= request.getContextPath() + "/admin-classify-list"%>">Danh sách nhóm sản phẩm</a>
                    </div>
                  </div>
                </div>
                <% List<CategoryModel> listCategory = (List<CategoryModel>) request.getAttribute("listCategory"); %>
                <!-- Card Body -->
                <div class="card-body">
                 <div class="container">
					  <form action="<%=request.getContextPath() + "/admin-classify-add" %>" method="POST" class="needs-validation" novalidate>
					  <div class="form-group">
						  <label for="category">Danh mục:</label>
						  <select class="form-control" id="category" required name="category">
						    <option value="">-- Chọn --</option>
						    <% 
						    	ClassifyModel classify = (ClassifyModel) request.getAttribute("classifyModel");
						    	if(listCategory != null) {
							    	for(CategoryModel category : listCategory) { 
							  %>
							     		<option value="<%=category.getAlias()%>" 
							     		<%= (classify != null ? (classify.getCategoryAlias().equals(category.getAlias()) ? "selected" : "") : "") %>>
							     		<%=category.getName()%></option>
							<% 		} 
						    	}
						    
						    %>
						  </select>
					      <div class="invalid-feedback">Vui lòng chọn danh mục</div>
					      <a href="#" class="small">- Thêm danh mục</a>
						</div>
					    <div class="form-group">
					      <label for="classify_name">Tên nhóm sản phẩm:</label>
					      <input type="text" class="form-control" id="classify_name" maxlength="100" 
					      placeholder="Nhập tên nhóm sản phẩm. VD: Điện thoại giá rẻ, Tai nghe chống ồn,..." 
					      name="classify_name" required  value="<%= (classify != null ? classify.getName() : "")%>">
					      
					      <div class="invalid-feedback">
					      	<span>- Không được bỏ trống</span> <br>
					      	<span>- Độ dài tối đa 100 ký tự</span>
					      </div>
					    </div>
					    
					    <input type="hidden" value="add" name="option">
					    <button type="submit" class="btn btn-primary">Thêm</button>
					  </form>
					</div>
                </div>
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


<%@include file="/common/admin/footer/script.jsp" %>


</body>

</html>
	