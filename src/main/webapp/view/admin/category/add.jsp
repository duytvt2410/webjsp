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
        <div class="container col-xl-6">

          
          <!-- Content Row -->

            <!-- Area Chart -->
            <div class="col-xl-12 col-lg-12">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Thêm danh mục</h6>
                  <div class="dropdown no-arrow">
                    <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                      <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                      <div class="dropdown-header">Lựa chọn khác:</div>
                      <a class="dropdown-item" href="<%= request.getContextPath() + "/admin-category-list"%>">Xem danh sách các danh mục</a>
                    </div>
                  </div>
                </div>
                <% 
               	 	CategoryModel categoryModel = null;
                	if(request.getAttribute("categoryModel") != null)  categoryModel = (CategoryModel) request.getAttribute("categoryModel");
                %>
                <!-- Card Body -->
                <div class="card-body">
                 <div class="container">
				  <form action="<%=request.getContextPath() +"/admin-category-add" %>" method="POST" class="needs-validation" novalidate>
				    <div class="form-group">
				      <label for="category_name">Tên danh mục:</label>
				      <input type="text" class="form-control" id="category_name" maxlength="30" placeholder="Nhập tên danh mục. VD: Điện thoai, Loa, Tai nghe,..." name="category_name"
				      value="<%= (categoryModel != null ? categoryModel.getName() : "") %>" required >
				      <div class="invalid-feedback">
				      	<span>- Không được bỏ trống</span> <br>
				      	<span>- Độ dài tối đa 30 ký tự</span>
				      </div>
				    </div>
				    <div class="form-group form-check">
				      <label class="form-check-label">
				        <input class="form-check-input" type="checkbox" name="isAccessories" value="yes"> Là phụ kiện.
				      </label>
				    </div>
				    <input type = "hidden" value="add" name="option">
				    <button type= "submit" class="btn btn-primary">Thêm</button>
				  </form>
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
	