<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="template/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="template/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="template/admin/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="template/admin/vendor/chart.js/Chart.min.js"></script>


<script>
	// Disable form submissions if there are invalid fields
	(function() {
		'use strict';
		window.addEventListener('load', function() {
			// Get the forms we want to add validation styles to
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});
		}, false);
	})();
<%if (request.getAttribute("message") != null) {%>
	$('#notificationModal').modal({
		show : true,
		keyboard : false,
		backdrop : 'static'
	});
<%}%>

function openViewModal(url){
	$.get(url, function(responseText) {
        $("#getView").html(responseText);
	});
	$('#viewModal').modal({
		show: true, 
		keyboard: false, 
		backdrop: 'static'
	});		
}

$(function () {
    setNavigation();
});

function setNavigation() {
    var path = window.location.pathname;
   
    $(".li_active a").each(function () {
        var href = $(this).attr('href');
        if (path === href) {
        	$(this).closest('.collapse_active').addClass('show');
            $(this).addClass('active');
            $(this).closest('.li_active').addClass('active');
        }
    });
}  	


	
</script>