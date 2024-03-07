package fr.eni.projetencheres.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.User;

@WebFilter(
		filterName = "IsOwner", 
		dispatcherTypes = { DispatcherType.REQUEST }
)
public class FilterIsOwner implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		Article article = (Article) httpRequest.getAttribute("article");

		User user = (User) httpRequest.getSession().getAttribute("userConnected");
		
		if (article.getUserId() == user.getId() && article.getAuctionState() != "ADDED") {
			httpRequest.setAttribute("article", article);
			chain.doFilter(httpRequest, response);
		} else {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/encheres?id=" + article.getId());
		}
	}
}
