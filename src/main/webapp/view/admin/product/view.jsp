<%@page import="utils.Utils"%>
<%@page import="model.ProductClassifyModel"%>
<%@page import="model.ProductModel"%>
<%@page import="model.ClassifyModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<% ProductModel  product = (ProductModel) request.getAttribute("productModel"); %>

			<div class="modal-body">
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Danh mục</div>
						<div class="col-8 text-left p-2"><%=(product != null ? product.getCategoryName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Nhãn hàng</div>
						<div class="col-8 text-left p-2"><%=(product != null ? product.getBrandName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Nhóm sản phẩm</div>
						<div class="col-8 text-left p-2">
						<%if(product.getListClassify() != null) {
							for(ProductClassifyModel productClassify : product.getListClassify()) {
							%>
						
						<li><%= productClassify.getClassifyName() %></li>
						<% }
						}
						%>
						</div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Ảnh sản phẩm</div>
						<div class="col-8 text-left p-2"><img style="height: 70px" src="<%=(product.getUrlImageProduct() != null ? Utils.getPathImage(product.getUrlImageProduct()) : "")%>"></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Giá niêm yết</div>
						<div class="col-8 text-left p-2"><%=(product != null ? Utils.convertToVND(product.getPrice())  : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Giá khuyến mãi</div>
						<div class="col-8 text-left p-2"><%=(product != null ? Utils.convertToVND(product.getPricePromotional()) : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Số lượng</div>
						<div class="col-8 text-left p-2"><%=(product != null ? product.getQty() : "") %></div>
					</div>
				</div>		
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Ngày tạo</div>
						<div class="col-8 text-left p-2"><%=(product != null ? Utils.formatDate(product.getCreateDate())  : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người tạo</div>
						<div class="col-8 text-left p-2"><%=(product != null ? product.getNameCreateBy(product.getCreateBy()) : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Cập nhật gần nhất</div>
						<div class="col-8 text-left p-2"><%=(product != null ? Utils.formatDate(product.getUpdateDate())  : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người cập nhật</div>
						<div class="col-8 text-left p-2"><%=(product != null ? product.getNameCreateBy(product.getUpdateBy())  : "") %></div>
					</div>
				</div>													
			</div>
			
	<div class="modal-footer">
		<button title="Chỉnh sửa" class="btn btn-info" onclick="location.href='<%= request.getContextPath() + "/admin-product-edit?id="+ product.getId()%>';"><i class="fas fa-edit"></i>Chỉnh sửa</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>	
	</div>