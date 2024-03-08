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

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet Filter implementation class FilterIsLoggedIn
 */
@WebFilter(
		filterName = "IsLoggedIn", 
		dispatcherTypes = { DispatcherType.REQUEST }
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
			String path = String.format("%s/connexion?targetUrl=%s", httpRequest.getContextPath(),
					httpRequest.getServletPath());
			httpResponse.sendRedirect(path);
		} else {
			try {
				user = UserManager.getUserById(user.getId());
				httpRequest.getSession().setAttribute("userConnected", user);
			} catch (DataException e) {
				System.out.println(e);
				httpResponse.sendError(500);
			}
			chain.doFilter(request, response);
		}
	}
}
