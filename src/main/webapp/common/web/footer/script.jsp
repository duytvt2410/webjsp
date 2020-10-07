<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<!-- jQuery Plugins -->
		<script src="template/web/js/jquery.min.js"></script>
		<script src="template/web/js/bootstrap.min.js"></script>
		<script src="template/web/js/slick.min.js"></script>
		<script src="template/web/js/nouislider.min.js"></script>
		<script src="template/web/js/jquery.zoom.min.js"></script>
		<script src="template/web/js/main.js"></script>
		
		<script>
		$(function () {
		    setNavigation();
		});

		function setNavigation() {
		    var path = window.location.pathname;
		   
		    $(".li_active a").each(function () {
		        var href = $(this).attr('href');
		        if (path === href) {
		            $(this).closest('.li_active').addClass('active');
		        }
		    });
		}  	
		
		</script>