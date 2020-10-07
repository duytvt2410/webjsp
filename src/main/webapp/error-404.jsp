<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404</title>
  <!-- Custom fonts for this template-->
  <link href="<%= request.getContextPath()%>/template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="<%= request.getContextPath()%>/template/admin/https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%= request.getContextPath()%>/template/admin/css/sb-admin-2.min.css" rel="stylesheet">
</head>
<body>
	        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- 404 Error Text -->
          <div class="text-center">
            <div class="error mx-auto" data-text="404">404</div>
            <p class="lead text-gray-800 mb-5">Trang không tìm thấy</p>
            <p class="text-gray-500 mb-0">Trang bạn tìm kiếm không tồn tại</p>
            <a href="#" onclick="history.back()">&larr; Trở về trang trước</a><br>
            <a href="<%= request.getContextPath() + "/trangchu"%>">&larr; Trang chủ</a>
          </div>

        </div>
        <!-- /.container-fluid -->
        
         <!-- Bootstrap core JavaScript-->
  <script src="<%= request.getContextPath()%>/template/admin/vendor/jquery/jquery.min.js"></script>
  <script src="<%= request.getContextPath()%>/template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%= request.getContextPath()%>/template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="<%= request.getContextPath()%>/template/admin/js/sb-admin-2.min.js"></script>

</body>
</html>