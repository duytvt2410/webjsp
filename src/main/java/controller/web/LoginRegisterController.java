package controller.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

import model.UserModel;
import service.IUserService;
import service.imp.UserService;
import utils.SendEmail;
import utils.SessionUtil;
import utils.SystemContain;

@WebServlet(urlPatterns = { "/dangnhap", "/dangky","/xacnhandangky", "/dangxuat", "/dangnhapbangFB", "/dangnhapbangGG", "/quenmatkhau", "/doimatkhau" })
public class LoginRegisterController extends HttpServlet {
	/**
	 * 
	 */
	// FB
	public static String FACEBOOK_APP_ID = "608201396794677";
	public static String FACEBOOK_APP_SECRET = "fca8303172cb94314f2502dce81dc371";
	public static String FACEBOOK_REDIRECT_URL = "https://jspweb.demo.jelastic.com/dangnhapbangFB";
	public static String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/oauth/access_token?client_id=%s&client_secret=%s&redirect_uri=%s&code=%s";

	// GG
	public static String GOOGLE_CLIENT_ID = "762420684607-sso3ff8r1tp1j2uramg24gvvqsnv9g17.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "bA5PL80KtWIYKdDB22vhXZS-";
	public static String GOOGLE_REDIRECT_URI = "https://jspweb.demo.jelastic.com/dangnhapbangGG";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";

	private static final long serialVersionUID = 1L;

	private IUserService userService = UserService.getInstance();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		UserModel user = (UserModel) SessionUtil.getInstance().getValue(request, "User");
		String url = request.getRequestURI();
		if (url.startsWith(request.getContextPath() + "/dangnhapbangFB")) {
			loginUseFB(request, response);
		} else if (url.startsWith(request.getContextPath() + "/dangnhapbangGG")) {
			loginUseGG(request, response);
		} else if (url.startsWith(request.getContextPath() + "/dangnhap")) {	
			if(user != null) {
				response.sendRedirect(request.getContextPath() + "/trangchu");
			} else {
				url = SystemContain.URL_PAGE_LOGIN;
				request.getRequestDispatcher(url).forward(request, response);
			}
		} else if (url.startsWith(request.getContextPath() + "/dangky")) {
			if(user != null) {
				response.sendRedirect(request.getContextPath() + "/trangchu");
			} else {
				url = SystemContain.URL_PAGE_REGISTER;
				request.getRequestDispatcher(url).forward(request, response);
			}
		} else if (url.startsWith(request.getContextPath() + "/dangxuat")) {
			SessionUtil.getInstance().removeValue(request, "User");
			response.sendRedirect(request.getContextPath() + "/trangchu");
		} else if (url.startsWith(request.getContextPath() + "/xacnhandangky")) {
			UserModel model = new UserModel();
			model.setFullName(request.getParameter("fullname"));
			model.setEmail(request.getParameter("email"));
			model.setPassword(request.getParameter("password"));
			register(request, response, model, true);
		}else if (url.startsWith(request.getContextPath() + "/quenmatkhau")) {
			String email = request.getParameter("email");
			if(email != null) {
				if(userService.findOneByEmail(email) == null) {
					request.setAttribute("message", "Email không tồn tại");
				} else {
					SendEmail.sendEmailConfirmForgotPass(email);
					request.setAttribute("confirm", "confirm");
				}
			}
			request.getRequestDispatcher(SystemContain.URL_PAGE_PASSWORD).forward(request, response);
		}else if (url.startsWith(request.getContextPath() + "/doimatkhau")) {
			UserModel u = (UserModel) SessionUtil.getInstance().getValue(request, "User");
			String email = "";
			if(u != null) {
				email = u.getEmail();
			} else {
				email = request.getParameter("email");
			}
			
			request.setAttribute("email", email);
			request.getRequestDispatcher(SystemContain.URL_PAGE_CHANGEPASSWORD).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		UserModel model = new UserModel();

		String option = request.getParameter("option");

		model.setFullName(request.getParameter("fullname"));
		model.setEmail(request.getParameter("email"));
		model.setPassword(request.getParameter("password"));

		if (option.equalsIgnoreCase("register")) {
			register(request, response, model, false);
		} else if (option.equalsIgnoreCase("login")) {
			login(request, response, model);
		} else if (option.equalsIgnoreCase("change")) {
			
			changePass(request, response,  model.getEmail(),  model.getPassword());

			SessionUtil.getInstance().removeValue(request, "User");
			response.sendRedirect(request.getContextPath() + "/dangnhap");		
		} else {
			doGet(request, response);
		}

	}

	private void changePass(HttpServletRequest request, HttpServletResponse response,  String email, String password) {
		userService.updatePassword(email, password);
		
	}

	private void login(HttpServletRequest request, HttpServletResponse response, UserModel model)
			throws IOException, ServletException {
		if (userService.findOneByEmailAndPassword(model.getEmail(), model.getPassword()) != null) {
			model = userService.findOneByEmailAndPassword(model.getEmail(), model.getPassword());
			SessionUtil.getInstance().putValue(request, "User", model);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			userService.updateLastLogin(time, model.getEmail());
			if (model.getRole().equals("admin")) {
				response.sendRedirect(request.getContextPath() + "/admin-home");
			} else {
				response.sendRedirect(request.getContextPath() + "/trangchu");
			}
		} else {
			request.setAttribute("message", "Sai email đăng nhập hoặc mật khẩu");
			request.setAttribute("userModel", model);
			request.getRequestDispatcher(SystemContain.URL_PAGE_LOGIN).forward(request, response);
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response, UserModel model, boolean isConfirm)
			throws IOException, ServletException {
		Map<String, String> map = null;
		UserModel userRegister = new UserModel();
		userRegister.setEmail(model.getEmail());
		userRegister.setPassword(model.getPassword());
		map = userService.insert(model, isConfirm);
		Set<String> set = map.keySet();
		String alert = "";
		String message = "";
		for (String key : set) {
			alert = key;
			message = map.get(key);
		}

		if (alert.contentEquals("success")) {
			login(request, response, userRegister);
		} else if(alert.contentEquals("confirm")){
			SendEmail.sendEmailConfirmRegister(model, model.getEmail());
			request.setAttribute("confirm", "confirm");
			request.getRequestDispatcher(SystemContain.URL_PAGE_REGISTER).forward(request, response);
		} else {
			request.setAttribute("alert", alert);
			request.setAttribute("message", message);
			request.setAttribute("userModel", model);
			request.getRequestDispatcher(SystemContain.URL_PAGE_REGISTER).forward(request, response);
		}
	}

	private void loginUseFB(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/dangnhap");
		} else {
			String link = String.format(FACEBOOK_LINK_GET_TOKEN, FACEBOOK_APP_ID, FACEBOOK_APP_SECRET,
					FACEBOOK_REDIRECT_URL, code);
			String res = Request.Get(link).execute().returnContent().asString();
			JsonObject jobj = new Gson().fromJson(res, JsonObject.class);
			String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

			FacebookClient facebookClient = new DefaultFacebookClient(accessToken, FACEBOOK_APP_SECRET, Version.LATEST);
			User userFB = facebookClient.fetchObject("me", User.class);

			UserModel userModel = userService.findOneByEmail(userFB.getId());

			if (userModel == null) {
				userModel = new UserModel();
				userModel.setFullName(userFB.getName());
				userModel.setEmail(userFB.getId());
				userModel.setPassword("123456");
				userService.insertUseFB(userModel);

				userModel = userService.findOneByEmail(userFB.getId());
			}
			SessionUtil.getInstance().putValue(request, "User", userModel);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			userService.updateLastLogin(time, userModel.getEmail());
			response.sendRedirect(request.getContextPath() + "/trangchu");
		}
	}

	private void loginUseGG(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			response.sendRedirect(request.getContextPath() + "/dangnhap");
		} else {
			String res = Request.Post(GOOGLE_LINK_GET_TOKEN)
					.bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID).add("client_secret", GOOGLE_CLIENT_SECRET)
							.add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
							.add("grant_type", GOOGLE_GRANT_TYPE).build())
					.execute().returnContent().asString();
			JsonObject jobj = new Gson().fromJson(res, JsonObject.class);
			String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

			String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
			String respon = Request.Get(link).execute().returnContent().asString();
			GooglePojo userGG = new Gson().fromJson(respon, GooglePojo.class);

			UserModel userModel = userService.findOneByEmail(userGG.getEmail());

			if (userModel == null) {
				userModel = new UserModel();
				userModel.setFullName(userGG.getEmail().split("@")[0]);
				userModel.setEmail(userGG.getEmail());
				userModel.setPassword("123456");
				userService.insertUseFB(userModel);

				userModel = userService.findOneByEmail(userModel.getEmail());
			}
			SessionUtil.getInstance().putValue(request, "User", userModel);
			Timestamp time = new Timestamp(System.currentTimeMillis());
			userService.updateLastLogin(time, userModel.getEmail());
			response.sendRedirect(request.getContextPath() + "/trangchu");
		}
	}


}

class GooglePojo {
	private String id;
	private String email;
	private boolean verified_email;
	private String name;
	private String given_name;
	private String family_name;
	private String link;
	private String picture;

	// getter-setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified_email() {
		return verified_email;
	}

	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

}
