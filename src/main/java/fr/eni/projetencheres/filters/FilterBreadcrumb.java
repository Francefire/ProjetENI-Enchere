package fr.eni.projetencheres.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class FilterBreadcrumb
 */
@WebFilter(
		filterName = "Breadcrumb",
		dispatcherTypes = { DispatcherType.REQUEST }
)
public class FilterBreadcrumb extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		List<String> breadcrumb = Arrays.asList(httpRequest.getServletPath().split("/"));

		request.setAttribute("breadcrumb", breadcrumb);
		chain.doFilter(request, response);
	}

}
