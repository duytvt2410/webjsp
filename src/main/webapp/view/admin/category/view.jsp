<%@page import="utils.Utils"%>
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
						<div class="col-8 text-left p-2"><%=(category != null ? (category.getStatus().equals("active") ? "Hoạt động" : "Không hoạt động") : "") %></div>
					</div>
				</div>		
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Ngày tạo</div>
						<div class="col-8 text-left p-2"><%=(category != null ? Utils.formatDate(category.getCreateDate())  : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người tạo</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getNameCreateBy(category.getCreateBy()) : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Cập nhật gần nhất</div>
						<div class="col-8 text-left p-2"><%=(category != null ? Utils.formatDate(category.getUpdateDate()) : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người cập nhật</div>
						<div class="col-8 text-left p-2"><%=(category != null ? category.getNameCreateBy(category.getUpdateBy()) : "") %></div>
					</div>
				</div>													
			</div>
			
	<div class="modal-footer">
		<button title="Chỉnh sửa" class="btn btn-info" onclick="location.href='<%= request.getContextPath() + "/admin-category-edit?id="+ category.getId()%>';"><i class="fas fa-edit"></i>Chỉnh sửa</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>	
	</div>