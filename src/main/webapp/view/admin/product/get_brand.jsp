<%@page import="java.util.List"%>
<%@page import="model.BrandModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<BrandModel> listBrand = (List<BrandModel>) request.getAttribute("listBrand");
	String empty = (String) request.getAttribute("empty");
	
	String idStrBrand = (String) request.getAttribute("brand");
	Long idBrand = Long.parseLong( (idStrBrand != "" ? idStrBrand : "0"));
%>


<label for="brand" class="text-primary font-weight-bold">Nhãn hàng:</label>
<select class="form-control" id="brand" required name="brand"
	<%=(empty.equals("yes") ? "disabled" : "")%>>
	<option>-- Chọn --</option>
	<%
		if (listBrand != null) {
			for (BrandModel brand : listBrand) {
	%>
	<option <%=(idBrand.equals(brand.getId()) ? "selected" : "")%> value="<%=brand.getId()%>">
		<%=brand.getName()%></option>
	<%
		}
		}
	%>
</select>

<div class="invalid-feedback">Vui lòng chọn danh mục</div>
<a href="<%=request.getContextPath() + "/admin-brand-add"%>"
	class="small">- Thêm nhãn hàng</a>