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
 * Servlet Filter implementation class FilterIsAdmin
 */
@WebFilter(
		filterName = "IsAdmin", 
		dispatcherTypes = { DispatcherType.REQUEST }
)
public class FilterIsAdmin extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		User user = (User) httpRequest.getSession().getAttribute("userConnected");
		
		if (user.isAdmin()) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath());
		}
	}
}
