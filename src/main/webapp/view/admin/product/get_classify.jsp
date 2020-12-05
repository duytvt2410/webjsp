<%@page import="model.ProductClassifyModel"%>
<%@page import="model.ProductModel"%>
<%@page import="java.util.Comparator"%>
<%@page import="java.util.Collections"%>
<%@page import="model.ClassifyModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String idClassify = (String) request.getAttribute("classify");

	String idClassifyChoose[] = idClassify.split("dt14082410dt");
	List<ClassifyModel> listClassify = (List<ClassifyModel>) request.getAttribute("listClassify");
	Collections.sort(listClassify, new Comparator<ClassifyModel>() {
		@Override
		public int compare(ClassifyModel c2, ClassifyModel c1) {

			return c1.getName().compareTo(c2.getName());
		}
	});
%>
<label for="classify" class="text-primary font-weight-bold">Nhóm sản phẩm:</label>

<div style="max-height: 300px; overflow: auto; border: 1px solid; border-radius: 10px; padding: 15px">
	<%
	if (idClassify == "") {
		if (listClassify.size() != 0) {
			String title = "";
				for (ClassifyModel classify : listClassify) {
					
						String nameSplit[] = classify.getName().split(":");
						if (!nameSplit[0].equals(title)) {
							title = nameSplit[0];
	%>
						<span class="font-weight-bold"><%= title %></span> <br>
	<%
						}
						
	%>		
	<input type="checkbox" id="<%=classify.getId() %>" class="ml-3" value="<%=classify.getId()%>" name="classify">
	<label for="<%=classify.getId() %>"><%=nameSplit[1] %></label> <br>
			

	<%
				}
		}
	} else {
		if (listClassify.size() != 0) {
			String title = "";
			for (ClassifyModel classify : listClassify) {
				String nameSplit[] = classify.getName().split(":");
				if (!nameSplit[0].equals(title)) {
					title = nameSplit[0];
	%>
					<span class="font-weight-bold"><%= title %></span> <br>
	<%
					}
				boolean kt = false;
				for (String c : idClassifyChoose) {
					if (classify.getId().equals(c)) {
						kt = true;
	%>
					<input type="checkbox" checked id="<%=classify.getId() %>" class="ml-3" value="<%=classify.getId()%>" name="classify">
					<label for="<%=classify.getId() %>"><%=nameSplit[1] %></label> <br>
	<%
					}

				}
					if (kt == false) {
	%>
					<input type="checkbox" id="<%=classify.getId() %>" class="ml-3" value="<%=classify.getId()%>" name="classify">
					<label for="<%=classify.getId() %>"><%=nameSplit[1] %></label> <br>
	<%
					}
			}
		}
	}
	%>
</div>
<div class="invalid-feedback">Vui lòng chọn danh mục</div>
<a href="<%=request.getContextPath() + "/admin-classify-add"%>"
	class="small">- Thêm nhóm sản phẩm</a>