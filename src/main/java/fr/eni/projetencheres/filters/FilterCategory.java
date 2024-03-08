package fr.eni.projetencheres.filters;

import java.io.IOException;
import java.util.Dictionary;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

import fr.eni.projetencheres.bll.CategoryManager;
import fr.eni.projetencheres.bo.Category;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet Filter implementation class FilterCategory
 */
@WebFilter(
		filterName = "Category", 
		dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD }
		)
public class FilterCategory extends HttpFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		try {
			List<Category> categories = CategoryManager.getInstance().selectAllCategories();
			
			
			request.setAttribute("categories", categories);
		} catch (DataException e) {
			System.out.println("Erreur lors de la récupération des catégories");
			System.out.println(e);
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
