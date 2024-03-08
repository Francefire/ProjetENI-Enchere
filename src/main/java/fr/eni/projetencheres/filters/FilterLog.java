package fr.eni.projetencheres.filters;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * Servlet Filter implementation class FilterLog
 */
@WebFilter(
		filterName = "Log",
		dispatcherTypes = { DispatcherType.REQUEST },
		urlPatterns = { "/*" }
)
public class FilterLog extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;
	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		System.out.printf("[%s] %s -> %s %s\n", LocalDateTime.now().format(formatter), httpRequest.getRemoteHost(), httpRequest.getMethod(), httpRequest.getServletPath());
		
		chain.doFilter(request, response);
	}
}
