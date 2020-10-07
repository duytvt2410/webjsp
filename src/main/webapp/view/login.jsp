<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Login</title>

  <!-- Custom fonts for this template-->
  <link href="template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="template/admin/https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="template/admin/css/sb-admin-2.min.css" rel="stylesheet">

</head>

<body class="bg-gradient-primary" style="background-color: #4e73df;
    background-image: linear-gradient(180deg,#15161d 10%,#d10024 100%);
    background-size: cover">

  <div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">
    

      <div class="col-xl-6 col-lg-6 col-md-9">
      						<div class="text-center mt-4">
								<a href="<%= request.getContextPath() + "/trangchu" %>" class="logo">
									<img src="template/web/img/logo.png" alt="">
								</a>
							</div>

        <div class="card o-hidden border-0 shadow-lg my-3">
          <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
              <div class="col-lg-12">
                <div class="p-5">
                  <div class="text-center">
                    <h1 class="h4 text-gray-900 mb-4">Đăng nhập vào tài khoản của bạn</h1>
                  </div>
                  <form class="user" action="<%= request.getContextPath() + "/dangnhap" %>" method="post">
                    <div class="form-group">
                      <input type="email" class="form-control form-control-user" id="exampleInputEmail" name="email" aria-describedby="emailHelp" placeholder="Nhập địa chỉ Email">
                    </div>
                    <div class="form-group">
                      <input type="password" class="form-control form-control-user" id="exampleInputPassword" name="password"  placeholder="Nhập mật khẩu">
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-checkbox small">
                        <input type="checkbox" class="custom-control-input" id="customCheck">
                        <label class="custom-control-label" for="customCheck">Nhớ mật khẩu?</label>
                      </div>
                    </div>
                    <input type="hidden" name="option" value="login">
                    
                    <button type="submit" class="btn btn-primary btn-user btn-block">
                      Đăng nhập
                    </button>
                    <hr>
                    <a href="index.html" class="btn btn-google btn-user btn-block">
                      <i class="fab fa-google fa-fw"></i> Đăng nhập bằng Google
                    </a>
                    <a href="index.html" class="btn btn-facebook btn-user btn-block">
                      <i class="fab fa-facebook-f fa-fw"></i> Đăng nhập bằng Facebook
                    </a>
                  </form>
                  <hr>
                  <div class="text-center">
                    <a class="small" href="forgot-password.html">Quên mật khẩu?</a>
                  </div>
                  <div class="text-center">
                    <a class="small" href="<%= request.getContextPath() + "/dangky"%>">Tạo một tài khoản!</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

      </div>

    </div>

  </div>

  <!-- Bootstrap core JavaScript-->
  <script src="template/admin/vendor/jquery/jquery.min.js"></script>
  <script src="template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="template/admin/js/sb-admin-2.min.js"></script>

</body>

</html>
