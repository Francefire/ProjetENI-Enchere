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
 * Servlet Filter implementation class FilterIsDisabled
 */
@WebFilter(
		filterName = "IsDisabled", 
		dispatcherTypes = { DispatcherType.REQUEST }
)
public class FilterIsDisabled extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		User user = (User) httpRequest.getSession().getAttribute("userConnected");

		if (user.isDisabled()) {
			httpResponse.sendRedirect(httpRequest.getContextPath());
		} else {
			chain.doFilter(request, response);
		}
	}
}
