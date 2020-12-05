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
		
		<%if (request.getAttribute("message") != null) {%>
			$('#notificationModal').modal({
				show : true,
				keyboard : false,
				backdrop : 'static'
			});
		<%}%>

		function setNavigation() {
		    var path = window.location.pathname;
		   
		    $(".li_active a").each(function () {
		        var href = $(this).attr('href');
		        if (path === href) {
		            $(this).closest('.li_active').addClass('active');
		        }
		    });
		}  	
		 function addCart(qty, id) {
			  var url = '<%= request.getContextPath() + "/giohang?option=add&id="%>' + id + "&qty=" + qty; 
				$.get(url, function(responseText) {
			        $("#getMiniCart").html(responseText);
			        $('#notificationAddItemCartModal').modal({
						show : true,
						keyboard : false,
						backdrop : 'static'
					});
				});	
			}
		 
		 function removeItemCart(id) {
			  var url = '<%= request.getContextPath() + "/giohang?option=remove&id="%>' + id; 
				$.get(url, function(responseText) {
			        $("#getMiniCart").html(responseText);
				});	
			}
		 
		 function limitQty(qty, idQty) {
			  var num = parseInt(qty);
			  var id = "#qty-" + idQty;
			  if(num < 0) {
				  $(id).val("1");
			  } else if(num > 3){
				  $(id).val("3");
			  }
		}
		</script>
		