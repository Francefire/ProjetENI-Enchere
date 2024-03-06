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

import fr.eni.projetencheres.bll.ArticlesManager;
import fr.eni.projetencheres.bo.Article;

/**
 * Servlet Filter implementation class FilterParseArticleId
 */
@WebFilter(
		filterName = "ParseArticleId", 
		dispatcherTypes = { DispatcherType.REQUEST }, 
		urlPatterns = { 
				"/encheres/encherir",
				"/encheres/supprimer", 
				"/encheres/modifier",
		}
)
public class FilterParseArticleId extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String id = (String) httpRequest.getParameter("id");
		
		if (id == null || id.isEmpty()) {
			httpResponse.sendError(404);
		} else {
			try {
				int articleId = Integer.parseInt(id);
				
				Article article = ArticlesManager.getArticleByArticleId(articleId);
				
				if (article == null) {
					httpResponse.sendError(404);
				} else {
					httpRequest.setAttribute("article", article);
					chain.doFilter(httpRequest, response);
				}
			} catch (Exception e) {
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/encheres");
			}
		}
	}
}
