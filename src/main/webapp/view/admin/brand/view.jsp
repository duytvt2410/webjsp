<%@page import="model.BrandModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<% BrandModel  brand = (BrandModel) request.getAttribute("brandModel"); %>

			<div class="modal-body">
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Tên danh mục</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Danh mục</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getCategoryName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Trạng thái</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getStatus() : "") %></div>
					</div>
				</div>		
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Ngày tạo</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getCreateDate() : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người tạo</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getCreateBy() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Cập nhật gần nhất</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getUpdateDate() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người cập nhật</div>
						<div class="col-8 text-left p-2"><%=(brand != null ? brand.getUpdateBy() : "") %></div>
					</div>
				</div>													
			</div>
			
	<div class="modal-footer">
		<button title="Chỉnh sửa" class="btn btn-info" onclick="location.href='<%= request.getContextPath() + "/admin-brand-edit?name="+ brand.getAlias()%>';"><i class="fas fa-edit"></i>Chỉnh sửa</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>	
	</div>