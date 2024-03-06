package fr.eni.projetencheres.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bo.User;

/**
 * Servlet Filter implementation class FilterIsLoggedIn
 */
@WebFilter(
		filterName = "IsLoggedIn", 
		dispatcherTypes = { DispatcherType.REQUEST }, 
		urlPatterns = { 
				"/auctions/bid",
				"/auctions/delete", 
				"/auctions/edit", 
				"/auctions/new", 
				"/encheres/encherir", 
				"/encheres/supprimer",
				"/encheres/modifier",
				"/encheres/nouvelle",
				"/user",
				"/user/edit",
				"/credits",
		}
)
public class FilterIsLoggedIn extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		User user = (User) httpRequest.getSession().getAttribute("userConnected");

		if (user == null) {
			String path = String.format("%s/login?targetUrl=%s", httpRequest.getContextPath(),
					httpRequest.getServletPath());
			httpResponse.sendRedirect(path);
		} else {
			chain.doFilter(request, response);
		}
	}
}
