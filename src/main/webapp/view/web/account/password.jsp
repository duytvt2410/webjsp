<%@page import="model.UserModel"%>
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

  <title>SB Admin 2 - Register</title>

  <!-- Custom fonts for this template-->
  <link href="template/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="template/admin/https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="template/admin/css/sb-admin-2.min.css" rel="stylesheet">


	<style type="text/css">
	
/* Style all input fields */
input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
}

/* Style the submit button */
input[type=submit] {
  background-color: #4CAF50;
  color: white;
}


/* The message box is shown when the user clicks on the password field */
#message {
  display:none;
  background: #f1f1f1;
  color: #000;
  position: relative;
  padding: 5px;
  margin-top: 10px;
}

#message p {
  padding: 5px 5px 5px 70px;
  font-size: 12px;
  margin-bottom: 4px;
}

/* Add a green text color and a checkmark when the requirements are right */
.valid {
  color: green;
}

.valid:before {
  position: relative;
  left: -35px;
  content: "✔";
}

/* Add a red text color and an "x" when the requirements are wrong */
.invalid {
  color: red;
}

.invalid:before {
  position: relative;
  left: -35px;
  content: "✖";
}
</style>
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
	    <div class="card o-hidden border-0 shadow-lg my-5">
	      <div class="card-body p-0">
	        <!-- Nested Row within Card Body -->
	        <div class="row">
	          
	          <div class="col-lg-12">
	            <div class="p-5">
	              <div class="text-center">
	                <h1 class="h4 text-gray-900 mb-4">Quên mật khẩu</h1>
	                <% String confirm = (String) request.getAttribute("confirm"); %>
	              </div>
	              <% if(confirm == null) { %>
	              <form onsubmit="return validateForm()" class="user" action="<%= request.getContextPath() + "/quenmatkhau" %>" method="get">
	              <% String message = (String) request.getAttribute("message"); %>
	              
	               <% UserModel user = (UserModel) request.getAttribute("userModel"); %>
							<% if(message != null) {%>
								<div class="col-12 p-3 text-center mb-2" style="background-color: #f8d7da; border-radius: 10px">
								<%= message %>
								</div>
								<% } %>
	                 <div class="form-group">
                      <input type="email" required value="<%= user != null ? user.getEmail() :  "" %>" class="form-control form-control-user" id="exampleInputEmail" name="email" aria-describedby="emailHelp" placeholder="Nhập địa chỉ Email đã đăng ký">
                    </div>
	                <input value="register" name="option" type="hidden">
	                <button type="submit" onclick="" class="btn btn-primary btn-user btn-block">
	                  Xác nhận
	                </button>
	                <hr>
	                
	              </form>
	              
	                
	              <hr>
	              <div class="text-center">
	                <a class="small" href="<%= request.getContextPath() + "/dangnhap"%>">Bạn đã có tài khoản? Đăng nhập!</a>
	              </div>
	              <% } else { %>
	              <div class="bg bg-info p-3 text-center">
                	<p class="text-light">Vùi lòng vào tài khoản Email đăng ký để xác thực.</p>
                </div>
                <% } %>
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
<script>
var myInput = document.getElementById("psw");
var letter = document.getElementById("letter");
var capital = document.getElementById("capital");
var number = document.getElementById("number");
var length = document.getElementById("length");

// When the user clicks on the password field, show the message box
myInput.onfocus = function() {
  document.getElementById("message").style.display = "block";
}

// When the user clicks outside of the password field, hide the message box
myInput.onblur = function() {
  document.getElementById("message").style.display = "none";
}

// When the user starts to type something inside the password field
myInput.onkeyup = function() {
  // Validate lowercase letters
  var lowerCaseLetters = /[a-z]/g;
  if(myInput.value.match(lowerCaseLetters)) {  
    letter.classList.remove("invalid");
    letter.classList.add("valid");
  } else {
    letter.classList.remove("valid");
    letter.classList.add("invalid");
  }
  
  // Validate capital letters
  var upperCaseLetters = /[A-Z]/g;
  if(myInput.value.match(upperCaseLetters)) {  
    capital.classList.remove("invalid");
    capital.classList.add("valid");
  } else {
    capital.classList.remove("valid");
    capital.classList.add("invalid");
  }

  // Validate numbers
  var numbers = /[0-9]/g;
  if(myInput.value.match(numbers)) {  
    number.classList.remove("invalid");
    number.classList.add("valid");
  } else {
    number.classList.remove("valid");
    number.classList.add("invalid");
  }
  
  // Validate length
  if(myInput.value.length >= 8) {
    length.classList.remove("invalid");
    length.classList.add("valid");
  } else {
    length.classList.remove("valid");
    length.classList.add("invalid");
  }
}

$('#exampleRepeatPassword').on('change', function() {
	var pass = $("#psw").val();
	var rePass = $('#exampleRepeatPassword').val();
	if(pass !== rePass) {
		$('#messValidRepass').css("display", "block");
	} else {
		$('#messValidRepass').css("display", "none");
	}
});

function validateForm() {
	var pass = $("#psw").val();
	var rePass = $('#exampleRepeatPassword').val();
	if(pass !== rePass) {
		$('#messValidRepass').css("display", "block");
		return false;
	} else {
		$('#messValidRepass').css("display", "none");
		return true;
	}
	}

</script>
</body>

</html>
