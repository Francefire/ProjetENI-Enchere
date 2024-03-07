package fr.eni.projetencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.BidsManager;
import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.TakeoutManager;
import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.Bid;
import fr.eni.projetencheres.bo.Takeout;
import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletAuctionsTakeout
 */
@WebServlet("/encheres/retrait")
public class ServletAuctionsTakeout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/encheres");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = (Article) request.getAttribute("article");
		
		User user = (User) request.getSession().getAttribute("userConnected");
		
		try {
			Bid bid = BidsManager.getLastBidForArticle(article.getId());

			if (bid.getUserId() == user.getId()) {
				request.getRequestDispatcher("/WEB-INF/jsp/auctions/auctions_takeout.jsp").forward(request,response);
				String paramStreet = request.getParameter("street");
				String paramZipCode = request.getParameter("zipCode");
				String paramCity = request.getParameter("city");
				
				Takeout takeout = new Takeout();
				takeout.setArticleId(article.getId());
				takeout.setStreet(paramStreet);
				takeout.setZipCode(paramZipCode);
				takeout.setCity(paramCity);
				
				TakeoutManager.addTakeout(takeout);
				
				response.sendRedirect(request.getContextPath() + "/encheres");
			} else {
				response.sendRedirect(request.getContextPath() + "/encheres?id="+article.getId());
			}
		} catch (BusinessException e) {
			request.setAttribute("error", e);
			response.sendRedirect(request.getContextPath() + "/encheres?id="+article.getId());
		} catch (DataException e) {
			// TODO Log exception
			System.out.println(e);
			response.sendError(500);	
		}
	}

}
