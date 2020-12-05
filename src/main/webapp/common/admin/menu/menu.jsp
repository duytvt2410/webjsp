<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%= request.getContextPath() +"/admin-home"%>">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">Quản trị</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath() + "/admin-home"%>">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>Bảng điều khiển</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">


      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item li_active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-cubes"></i>
          <span>DANH MỤC SP</span>
        </a>
        <div id="collapseTwo" class="collapse collapse_active" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Lựa chọn:</h6>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-category-add"%>">Thêm danh mục</a>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-category-list"%>">Danh sách</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item li_active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-box-open"></i>
          <span>NHÓM SẢN PHẨM</span>
        </a>
        <div id="collapseUtilities" class="collapse collapse_active" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Lựa chọn:</h6>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-classify-add"%>">Thêm nhóm sp</a>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-classify-list"%>">Danh sách</a>
          </div>
        </div>
      </li>
      
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item li_active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#brandCollapse" aria-expanded="true" aria-controls="brandCollapse">
          <i class="fas fa-box-open"></i>
          <span>NHÃN HÀNG</span>
        </a>
        <div id="brandCollapse" class="collapse collapse_active" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Lựa chọn:</h6>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-brand-add"%>">Thêm nhãn hàng</a>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-brand-list"%>">Danh sách</a>
          </div>
        </div>
      </li>
      
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item li_active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#productCollapse" aria-expanded="true" aria-controls="productCollapse">
          <i class="fab fa-product-hunt"></i>
          <span>SẢN PHẨM</span>
        </a>
        <div id="productCollapse" class="collapse collapse_active" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Lựa chọn:</h6>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-product-add"%>">Thêm sản phẩm</a>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-product-list"%>">Danh sách</a>
          </div>
        </div>
      </li>
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item li_active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#billCollapse" aria-expanded="true" aria-controls="billCollapse">
          <i class="fas fa-file-invoice"></i>
          <span>ĐƠN HÀNG</span>
        </a>
        <div id="billCollapse" class="collapse collapse_active" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Lựa chọn:</h6>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-bill-list"%>">Danh sách</a>
          </div>
        </div>
      </li>
      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item li_active">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#userCollapse" aria-expanded="true" aria-controls="userCollapse">
          <i class="fas fa-users"></i>
          <span>NGUỜI DÙNG</span>
        </a>
        <div id="userCollapse" class="collapse collapse_active" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Lựa chọn:</h6>
            <a class="collapse-item" href="<%= request.getContextPath() + "/admin-user-list"%>">Danh sách</a>
          </div>
        </div>
      </li>
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->