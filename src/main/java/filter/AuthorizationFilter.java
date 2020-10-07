package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserModel;
import utils.SessionUtil;

public class AuthorizationFilter implements Filter{
	
	private ServletContext context;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.context = filterConfig.getServletContext();

		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			String url = request.getRequestURI();
			if(url.startsWith(request.getContextPath() + "/admin")) {
				UserModel model = (UserModel) SessionUtil.getInstance().getValue(request, "User");
				if(model != null) {
					if(model.getRole().equals("admin")) {
						
						chain.doFilter(servletRequest, servletResponse);
					} else {
						response.sendRedirect(request.getContextPath() +"/dangnhap?action=login&message=not_permission&alert=danger");
					}
				} else {
					response.sendRedirect(request.getContextPath() +"/dangnhap?action=login&message=not_login&alert=danger");
				}
			} else {
				chain.doFilter(servletRequest, servletResponse);
			}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
