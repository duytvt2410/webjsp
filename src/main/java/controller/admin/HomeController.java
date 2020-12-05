package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryModel;
import model.ViewsWebModel;
import service.IBillServicce;
import service.ICategoryService;
import service.IProductService;
import service.IUserService;
import service.IViewsWebService;
import service.imp.BillService;
import service.imp.CategoryService;
import service.imp.ProductService;
import service.imp.UserService;
import service.imp.ViewsWebService;
import utils.Utils;

@WebServlet("/admin-home")
public class HomeController extends HttpServlet{
	   /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private IViewsWebService view = ViewsWebService.getInstance();
		private IProductService productService = ProductService.getInstance();
		private ICategoryService categoryService = CategoryService.getInstance();
		private IUserService userServie = UserService.getInstance();
		private IBillServicce billService = BillService.getInstance();
		

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			List<ViewsWebModel> listViewsWebByYear = view.findAllByYear(2020);
			List<CategoryModel> listCategory = categoryService.findAll();
			
			long viewMax = Utils.getMaxViews(listViewsWebByYear);
			int totalProduct = productService.countAll();
			long totalViewWeb = view.findAllView().getViews();
			int totalUser = userServie.countAll();
			int totalBill = billService.countAll();
			
			List<String> listCategoryNameAndColorCodeAndTotal = new ArrayList<String>();
			
			if(totalProduct != 0) {
				for(int i = 0; i < listCategory.size(); i++) {
					CategoryModel c = listCategory.get(i);
					String nameCategory = c.getName();
					int total = productService.countAllByCategory(c.getAlias());
					
					
			        listCategoryNameAndColorCodeAndTotal.add(nameCategory + "14082410dt14082410" + Utils.arrColorCode()[i]+"14082410dt14082410" +total);
				}
			}
			
			List<String> listRoleAndColorCodeAndTotal = new ArrayList<String>();
			
			if(totalUser != 0) {
				String role[] = {"admin", "user"};
				for(int i = 0; i < role.length; i++) {
					int total = userServie.countByRole(role[i]);
					String ro = role[i];
					if(ro.equals("admin")) ro = "Admin";
					if(ro.equals("user")) ro = "User";
					
					listRoleAndColorCodeAndTotal.add(ro + "14082410dt14082410" + Utils.arrColorCode()[i]+"14082410dt14082410" +total);
				}
			}
			
			
			List<String> listStatusBillAndColorCodeAndTotal = new ArrayList<String>();
			
			if(totalBill != 0) {
				String status[] = {"confirm", "inprocess", "done", "cancel"};
				for(int i = 0; i < status.length; i++) {
					String sta = status[i];
					int total = billService.countByStatus(sta);
					if(sta.equals("confirm")) sta = "Đã xác nhận";
					if(sta.equals("inprocess")) sta = "Đang giao";
					if(sta.equals("done")) sta = "Đã giao";
					if(sta.equals("cancel")) sta = "Đã hủy";
					
					listStatusBillAndColorCodeAndTotal.add(sta + "14082410dt14082410" + Utils.arrColorCode()[i]+"14082410dt14082410" +total);
				}
			}
			
			request.setAttribute("listCategoryNameAndColorCodeAndTotal", listCategoryNameAndColorCodeAndTotal);
			request.setAttribute("listRoleAndColorCodeAndTotal", listRoleAndColorCodeAndTotal);
			request.setAttribute("listStatusBillAndColorCodeAndTotal", listStatusBillAndColorCodeAndTotal);
			
			request.setAttribute("viewMax", viewMax);
			request.setAttribute("totalProduct", totalProduct);
			request.setAttribute("totalViewWeb", totalViewWeb);
			request.setAttribute("totalUser", totalUser);
			request.setAttribute("totalBill", totalBill);
			request.setAttribute("listViewsWebByYear", listViewsWebByYear);
			
			request.getRequestDispatcher("view/admin/home.jsp").forward(request, response);
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	    }
	    
}
