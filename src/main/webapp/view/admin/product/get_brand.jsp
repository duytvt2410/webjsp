<%@page import="java.util.List"%>
<%@page import="model.BrandModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<BrandModel> listBrand = (List<BrandModel>) request.getAttribute("listBrand");

	String idBrand = (String) request.getAttribute("brand");
	
%>


<label for="brand" class="m-0 text-primary font-weight-bold">Nhãn hàng:</label>
<select class="form-control custom-select-sm" id="brand" required name="brand">
	<%
		if (listBrand != null) {
			for (BrandModel brand : listBrand) {
	%>
	<option <%=(idBrand != null ? (idBrand.equals(brand.getId()) ? "selected" : "") : "")%> value="<%=brand.getId()%>">
		<%=brand.getName()%></option>
	<%
		}
		}
	%>
</select>

