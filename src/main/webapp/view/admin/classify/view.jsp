<%@page import="utils.Utils"%>
<%@page import="model.ClassifyModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
			<%ClassifyModel  classify = (ClassifyModel) request.getAttribute("classifyModel"); %>

			<div class="modal-body">
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Tên danh mục</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? classify.getName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Danh mục</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? classify.getCategoryName() : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Trạng thái</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? classify.getStatus() : "") %></div>
					</div>
				</div>		
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Ngày tạo</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? Utils.formatDate(classify.getCreateDate()) : "") %></div>
					</div>
				</div>	
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người tạo</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? classify.getNameCreateBy(classify.getCreateBy()) : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Cập nhật gần nhất</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? Utils.formatDate(classify.getUpdateDate()) : "") %></div>
					</div>
				</div>
				<div class="card">
					<div class="row">
						<div class="col-4 text-right card border-left-primary p-2" style="background-color: #edf3f4; color: #336199">Người cập nhật</div>
						<div class="col-8 text-left p-2"><%=(classify != null ? classify.getNameCreateBy(classify.getUpdateBy()) : "") %></div>
					</div>
				</div>													
			</div>
			
	<div class="modal-footer">
		<button title="Chỉnh sửa" class="btn btn-info" onclick="location.href='<%= request.getContextPath() + "/admin-classify-edit?id="+ classify.getId()%>';"><i class="fas fa-edit"></i>Chỉnh sửa</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>	
	</div>