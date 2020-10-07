<%@page import="model.CategoryModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<%CategoryModel category = (CategoryModel) request.getAttribute("categoryModel"); %>

			<div class="modal-body">
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Tên danh mục</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Loại danh mục</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getIsAccessories() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Trạng thái</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getStatus() : "") %></div>
					</div>
				</div>		
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Ngày tạo</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getCreateDate() : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người tạo</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getCreateBy() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Cập nhật gần nhất</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getUpdateDate() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người cập nhật</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getUpdateBy() : "") %></div>
					</div>
				</div>													
			</div>
			
	<div class="modal-footer">
		<button title="Chỉnh sửa" class="btn btn-info" onclick="location.href='<%= request.getContextPath() + "/admin-category-edit?name="+ category.getAlias()%>';"><i class="fas fa-edit"></i>Chỉnh sửa</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>	
	</div>