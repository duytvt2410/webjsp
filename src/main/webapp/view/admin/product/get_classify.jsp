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
	String empty = (String) request.getAttribute("empty");
%>
<label for="classify" class="text-primary font-weight-bold">Nhóm sản phẩm:</label>

<div style="height: <%=(listClassify.size() != 0 ? "200px" : "30px") %>; overflow: auto">
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
			<input type="checkbox" class="ml-3" value="<%=classify.getId()%>" name="classify"><%=nameSplit[1] %> <br>

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
					if (classify.getId() == Long.parseLong(c)) {
						kt = true;
	%>
					<input type="checkbox" class="ml-3" checked value="<%=classify.getId()%>" name="classify"><%=classify.getName()%> <br>
	<%
					}

				}
					if (kt == false) {
	%>
					<input type="checkbox" class="ml-3" value="<%=classify.getId()%>" name="classify"><%=classify.getName()%> <br>
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