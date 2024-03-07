package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bll.EmailSender;
import fr.eni.projetencheres.bll.NewPwdGenerator;
import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.dal.DataException;

/**
 * Servlet implementation class ServletForgottenPwd
 */
@WebServlet("/oublie")
public class ServletForgottenPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/forgottenPwd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mailFromUser = request.getParameter("forgottenpwd");
//		EmailSender.sendPasswordRecoveryEmail(mailFromUser);

//		**********CHATGPT**************
		String newPassword = NewPwdGenerator.generatePassword(8); // Générer un nouveau mot de passe

		try {
			boolean exist = UserManager.mailExist(mailFromUser); // Récupère l'id qui correspond au no_utilisateur associé au mail fourni
			if (exist) {
				// Si l'e-mail existe, mettre à jour le mot de passe
				UserManager.updateNewPassword(mailFromUser, newPassword);
				EmailSender.sendPasswordRecoveryEmail(mailFromUser, newPassword);
			}
			
			//		***************	CHATGPT ***********	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/forgottenPwdOk.jsp");
			rd.forward(request, response);
		} catch (BusinessException e) {
			request.setAttribute("error", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/forgottenPwdOk.jsp").forward(request, response);
		}
		catch (DataException e) {
			System.out.println(e);
			response.sendError(500);
		}
	}
}
