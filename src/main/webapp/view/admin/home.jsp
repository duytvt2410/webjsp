<%@page import="model.ViewsWebModel"%>
<%@page import="java.util.List"%>
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
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">Tổng quát</h1>
            <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
          </div>

          <!-- Content Row -->
          <div class="row">
		<% int totalProduct = (int) request.getAttribute("totalProduct"); %>
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
             <a href="<%= request.getContextPath() + "/admin-product-list" %>" class="more-info-link">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body more-info-div">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">Sản phẩm</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><%= totalProduct %></div>
                    </div>
                    <div class="col-auto more-info-icon">
                      <i class="fab fa-product-hunt fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
              </a>
            </div>
			
			<% long totalViewWeb = (long) request.getAttribute("totalViewWeb"); %>
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body more-info-div">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">Lượt truy cập</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><%= totalViewWeb %></div>
                    </div>
                    <div class="col-auto more-info-icon">
                      <i class="fas fa-map-marker-alt fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
             
            </div>
			<% int totalUser = (int) request.getAttribute("totalUser"); %>
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
            <a href="<%= request.getContextPath() + "/admin-user-list" %>" class="more-info-link">
	              <div class="card border-left-info shadow h-100 py-2">
	                <div class="card-body more-info-div">
	                  <div class="row no-gutters align-items-center">
	                    <div class="col mr-2">
	                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">Tài khoản</div>
	                       <div class="h5 mb-0 font-weight-bold text-gray-800"><%= totalUser %></div>
	                    </div>
	                    <div class="col-auto more-info-icon">
	                      <i class="fas fa-user-circle fa-2x text-gray-300"></i>
	                    </div>
	                  </div>
	                </div>
	              </div>
               </a>
            </div>
           
	<% int totalBill = (int) request.getAttribute("totalBill"); %>
            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
            <a href="<%= request.getContextPath() + "/admin-bill-list" %>" class="more-info-link">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body  more-info-div">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">Đơn hàng</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><%= totalBill %></div>
                    </div>
                    <div class="col-auto more-info-icon">
                      <i class="fas fa-comment-dots fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
               </a>
            </div>
           
          </div>

          <!-- Content Row -->


		<div class="row">
            <!-- Pie Chart -->
            <div class="col-lg-4 col-md-6">
              <div class="card shadow mb-4">
              <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Sản phẩm</h6>
                  
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie">
                  	
                    <canvas id="myPieChart_1"></canvas>
                    
                  </div>
                  
                    <div class="small row mt-3">
	                    <% List<String> listCategoryNameAndColorCodeAndTotal = (List<String>) request.getAttribute("listCategoryNameAndColorCodeAndTotal"); %>
	                    <% for(String s : listCategoryNameAndColorCodeAndTotal) { %>
	                    <span class="col-auto">
	                      <i class="fas fa-circle" style="color: <%= s.split("14082410dt14082410")[1]%>"></i> <%= s.split("14082410dt14082410")[0]%>
	                    </span>
	                  	<% } %>
                    </div>
                </div>
              </div>
            </div>
            
            <!-- Pie Chart -->
            <div class="col-lg-4 col-md-6">
              <div class="card shadow mb-4">
              <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Tài khoản</h6>
                  
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie">
                  	
                    <canvas id="myPieChart_2"></canvas>
                    
                  </div>
                  
                    <div class="small row mt-3">
	                    <% List<String> listRoleAndColorCodeAndTotal = (List<String>) request.getAttribute("listRoleAndColorCodeAndTotal"); %>
	                    <% for(String s : listRoleAndColorCodeAndTotal) { %>
	                    <span class="col-auto">
	                      <i class="fas fa-circle" style="color: <%= s.split("14082410dt14082410")[1]%>"></i> <%= s.split("14082410dt14082410")[0]%>
	                    </span>
	                  	<% } %>
                    </div>
                </div>
              </div>
            </div>
            
            <!-- Pie Chart -->
            <div class="col-lg-4 col-md-6">
              <div class="card shadow mb-4">
              <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Đơn hàng</h6>
                  
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-pie pt-4 pb-2">
                    <canvas id="myPieChart_3"></canvas>
                  </div>
                  <div class="small row mt-3">
	                    <% List<String> listStatusBillAndColorCodeAndTotal = (List<String>) request.getAttribute("listStatusBillAndColorCodeAndTotal"); %>
	                    <% for(String s : listStatusBillAndColorCodeAndTotal) { %>
	                    <span class="col-auto">
	                      <i class="fas fa-circle" style="color: <%= s.split("14082410dt14082410")[1]%>"></i> <%= s.split("14082410dt14082410")[0]%>
	                    </span>
	                  	<% } %>
                    </div>
                </div>
              </div>
            </div>
          </div>
         <div class="row">
            <!-- Area Chart -->
            <div class="col-xl-12 col-lg-12">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">Lượt xem trong năm</h6>
                  
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-bar">
                    <canvas id="myBarChart"></canvas>
                  </div>
                </div>
              </div>
            </div>
		</div>

          <!-- Content Row -->
          

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
  
  <% long viewMax = (long) request.getAttribute("viewMax"); 
  	 List<ViewsWebModel> listViewsWebByYear = (List<ViewsWebModel>) request.getAttribute("listViewsWebByYear");
  	 String dataCharBar = "[";
  	 String labelsCharBar = "[";
  	 for(int i = 0; i < listViewsWebByYear.size(); i++) {
  		 if(i + 1 == listViewsWebByYear.size()) {
  			dataCharBar += listViewsWebByYear.get(i).getViews() + "]";
  			labelsCharBar += "'Tháng " + (i + 1) + "']";
  		 } else {
  			dataCharBar += listViewsWebByYear.get(i).getViews() + ",";
  			labelsCharBar += "'Tháng " + (i + 1)  + "',";
  		 }
  	 }
  %>
  
    <%
  	 String dataCharPie1 = "[";
  	 String labelsCharPie1 = "[";
  	 String colorCharPie1 = "[";
  	 
  	if(listCategoryNameAndColorCodeAndTotal.size() == 0) {
  		dataCharPie1 = "[]";
  		labelsCharPie1 = "[]";
  		colorCharPie1 = "[]";
	 }
  	 
  	 for(int i = 0; i < listCategoryNameAndColorCodeAndTotal.size(); i++) {
  		 String temp = listCategoryNameAndColorCodeAndTotal.get(i);
  		 if(i + 1 == listCategoryNameAndColorCodeAndTotal.size()) {
  			dataCharPie1 += temp.split("14082410dt14082410")[2] + "]";
  			labelsCharPie1 += "'" + temp.split("14082410dt14082410")[0] + "']";
  			colorCharPie1 += "'" + temp.split("14082410dt14082410")[1] + "']";
  		 } else {
  			dataCharPie1 += temp.split("14082410dt14082410")[2] + ",";
  			labelsCharPie1 += "'" + temp.split("14082410dt14082410")[0] + "',";
  			colorCharPie1 += "'" + temp.split("14082410dt14082410")[1] + "',";
  		 }
  	 }
  %>
  
  <%
  	 String dataCharPie2 = "[";
  	 String labelsCharPie2 = "[";
  	 String colorCharPie2 = "[";
  	 
  	if(listRoleAndColorCodeAndTotal.size() == 0) {
  		dataCharPie2 = "[]";
  		labelsCharPie2 = "[]";
  		colorCharPie2 = "[]";
	 }
  	 
  	 for(int i = 0; i < listRoleAndColorCodeAndTotal.size(); i++) {
  		 String temp = listRoleAndColorCodeAndTotal.get(i);
  		 if(i + 1 == listRoleAndColorCodeAndTotal.size()) {
  			dataCharPie2 += temp.split("14082410dt14082410")[2] + "]";
  			labelsCharPie2 += "'" + temp.split("14082410dt14082410")[0] + "']";
  			colorCharPie2 += "'" + temp.split("14082410dt14082410")[1] + "']";
  		 } else {
  			dataCharPie2 += temp.split("14082410dt14082410")[2] + ",";
  			labelsCharPie2 += "'" + temp.split("14082410dt14082410")[0] + "',";
  			colorCharPie2 += "'" + temp.split("14082410dt14082410")[1] + "',";
  		 }
  	 }
  %>
  
   <%
  	 String dataCharPie3 = "[";
  	 String labelsCharPie3 = "[";
  	 String colorCharPie3 = "[";
  	 
  	 if(listStatusBillAndColorCodeAndTotal.size() == 0) {
  		  dataCharPie3 = "[]";
  	  	  labelsCharPie3 = "[]";
  	  	  colorCharPie3 = "[]";
  	 }
  	 
  	 for(int i = 0; i < listStatusBillAndColorCodeAndTotal.size(); i++) {
  		 String temp = listStatusBillAndColorCodeAndTotal.get(i);
  		 if(i + 1 == listStatusBillAndColorCodeAndTotal.size()) {
  			dataCharPie3 += temp.split("14082410dt14082410")[2] + "]";
  			labelsCharPie3 += "'" + temp.split("14082410dt14082410")[0] + "']";
  			colorCharPie3 += "'" + temp.split("14082410dt14082410")[1] + "']";
  		 } else {
  			dataCharPie3 += temp.split("14082410dt14082410")[2] + ",";
  			labelsCharPie3 += "'" + temp.split("14082410dt14082410")[0] + "',";
  			colorCharPie3 += "'" + temp.split("14082410dt14082410")[1] + "',";
  		 }
  	 }
  %>


<%@include file="/common/admin/footer/script.jsp" %>
<!-- Page level custom scripts -->
<script src="template/admin/js/demo/chart-area-demo.js"></script>
<script type="text/javascript">
//Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = 'Nunito', '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#858796';

function number_format(number, decimals, dec_point, thousands_sep) {
  // *     example: number_format(1234.56, 2, ',', ' ');
  // *     return: '1 234,56'
  number = (number + '').replace(',', '').replace(' ', '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + Math.round(n * k) / k;
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '').length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1).join('0');
  }
  return s.join(dec);
}

//Bar Chart Example
var ctx = document.getElementById("myBarChart");
var myBarChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: <%=labelsCharBar%>,
    datasets: [{
      label: "Lượt xem",
      backgroundColor: "#4e73df",
      hoverBackgroundColor: "#2e59d9",
      borderColor: "#4e73df",
      data: <%=dataCharBar%>,
    }],
  },
  options: {
    maintainAspectRatio: false,
    layout: {
      padding: {
        left: 10,
        right: 25,
        top: 25,
        bottom: 0
      }
    },
    scales: {
      xAxes: [{
        time: {
          unit: 'month'
        },
        gridLines: {
          display: false,
          drawBorder: false
        },
        ticks: {
          maxTicksLimit: 7
        },
        maxBarThickness: 25,
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: <%=viewMax%>,
          maxTicksLimit: 5,
          padding: 10,
          // Include a dollar sign in the ticks
          callback: function(value, index, values) {
            return number_format(value);
          }
        },
        gridLines: {
          color: "rgb(234, 236, 244)",
          zeroLineColor: "rgb(234, 236, 244)",
          drawBorder: false,
          borderDash: [2],
          zeroLineBorderDash: [2]
        }
      }],
    },
    legend: {
      display: false
    },
    tooltips: {
      titleMarginBottom: 10,
      titleFontColor: '#6e707e',
      titleFontSize: 14,
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
      callbacks: {
        label: function(tooltipItem, chart) {
          var datasetLabel = chart.datasets[tooltipItem.datasetIndex].label || '';
          return datasetLabel + ': ' + number_format(tooltipItem.yLabel);
        }
      }
    },
  }
});

//Pie Chart Example
var ctx_1 = document.getElementById("myPieChart_1");
var myPieChart_1 = new Chart(ctx_1, {
	 type: 'doughnut',
	  data: {
	    labels: <%=labelsCharPie1%>,
	    datasets: [{
	      data: <%=dataCharPie1%>,
	      backgroundColor: <%=colorCharPie1%>,
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});

//Pie Chart Example
var ctx_2 = document.getElementById("myPieChart_2");
var myPieChart_2 = new Chart(ctx_2, {
	type: 'doughnut',
	  data: {
	    labels: <%=labelsCharPie2%>,
	    datasets: [{
	      data: <%=dataCharPie2%>,
	      backgroundColor: <%=colorCharPie2%>,
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});

//Pie Chart Example
var ctx_2 = document.getElementById("myPieChart_3");
var myPieChart_2 = new Chart(ctx_2, {
	type: 'doughnut',
	  data: {
	    labels: <%=labelsCharPie3%>,
	    datasets: [{
	      data: <%=dataCharPie3%>,
	      backgroundColor: <%=colorCharPie3%>,
	      hoverBorderColor: "rgba(234, 236, 244, 1)",
	    }],
	  },
  options: {
    maintainAspectRatio: false,
    tooltips: {
      backgroundColor: "rgb(255,255,255)",
      bodyFontColor: "#858796",
      borderColor: '#dddfeb',
      borderWidth: 1,
      xPadding: 15,
      yPadding: 15,
      displayColors: false,
      caretPadding: 10,
    },
    legend: {
      display: false
    },
    cutoutPercentage: 80,
  },
});
</script>

</body>

</html>
