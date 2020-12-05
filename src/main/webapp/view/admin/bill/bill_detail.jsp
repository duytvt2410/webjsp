<%@page import="model.BillModel"%>
<%@page import="utils.Utils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.BillDetailModel"%>
<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<% ArrayList<BillDetailModel> listBill = (ArrayList<BillDetailModel>) request.getAttribute("listBill");
			
			BillModel billModel = (BillModel) request.getAttribute("billModel");
			int stt = 0;
			%>
			
				
		

			<div class="modal-body">
			<div style="width: 70%; margin: auto; text-align: center;">
			<h3 style="margin-bottom: 50px">Thông tin chi tiết đơn hàng</h3>
				<div class="row">
					<h6 class="col-md-6">Tên nguời nhận:</h6>
					<p class="col-md-6"> <%=(billModel != null ? billModel.getFullname() : "") %></p>
				</div>
				<div class="row">
					<h6 class="col-md-6">Email:</h6>
					<p class="col-md-6"> <%=(billModel != null ? billModel.getEmail() : "") %></p>
				</div>
				<div class="row">
					<h6 class="col-md-6">Số điện thoại:</h6>
					<p class="col-md-6"> <%=(billModel != null ? billModel.getPhone() : "") %></p>
				</div>
				<div class="row">
					<h6 class="col-md-6">Địa chỉ:</h6>
					<p class="col-md-6"> <%=(billModel != null ? billModel.getAddress() : "") %></p>
				</div>
			</div>
				
				<div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th width="2%">STT</th>
                      <th width="15%">Ảnh</th>
                      <th width="15%">Tên sản phẩm</th>
                      <th width="15%">Giá</th>
                      <th title="Số lượng" width="8%"><%= Utils.limitName("Số lượng", 4) %></th>
                      <th width="45%">Khuyến mãi</th>
                      
                    </tr>
                  </thead>
                  <tbody>
                    	<% 
                    	
                    	for(BillDetailModel bill : listBill) {
                    		stt++;
                    	%>
                    	<tr>
                    		<td><%= stt %></td>
                    		<td><img style="height: 70px" src="<%=(bill.getUrlImageProduct() != null ? Utils.getPathImage(bill.getUrlImageProduct()) : "")%>"></td>
                     		<td title="<%=bill.getNameProduct()%>"><%= Utils.limitName(bill.getNameProduct(), 10) %></td>
							<td><%= Utils.convertToVND(bill.getPrice())%></td>
							<td><%= bill.getQty() %></td>
							<td><%= bill.getPromotion() %></td>
							
						</tr>
						<% } %>
                    
                  </tbody>
                </table>
              </div>											
			</div>
			
	<div class="modal-footer">
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>	
	</div>