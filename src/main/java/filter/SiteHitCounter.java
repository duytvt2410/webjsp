package filter;

import java.sql.Timestamp;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import model.ViewsWebModel;
import service.IViewsWebService;
import service.imp.ViewsWebService;

public class SiteHitCounter implements Filter {

	private Long hitCount;
	private IViewsWebService view = ViewsWebService.getInstance();

	public void init(FilterConfig config) throws ServletException {
		// Reset hit counter.
		hitCount = 0L;

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {

		// increase counter by one
		hitCount++;
		Timestamp time = new Timestamp(System.currentTimeMillis() - 28800000L);
		
		ViewsWebModel oldView = view.findOneByDayAndMonthAndYear(time.toString().split(" ")[0]);

		if (oldView == null) {
			ViewsWebModel newView = new ViewsWebModel();
			hitCount = 1L;
			newView.setViews(hitCount);
			time = new Timestamp(System.currentTimeMillis());
			newView.setDate(time);
			view.insert(newView);
		} else {
			hitCount = oldView.getViews() + 1;
			view.update(hitCount, oldView.getId());
		}

		// Pass request back down the filter chain
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}