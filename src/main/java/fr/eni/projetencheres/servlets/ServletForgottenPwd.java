package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.EmailSender;

/**
 * Servlet implementation class ServletForgottenPwd
 */
@WebServlet("/ForgottenPwd")
public class ServletForgottenPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ForgottenPwd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("forgottenpwd");
		System.out.println("Email saisi par l'utilisateur : " + email);
		String mailFromUser = request.getParameter("forgottenpwd");
		EmailSender.sendPasswordRecoveryEmail(mailFromUser);
		System.out.println("méthode EmailSender passée");
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ForgottenPwdOk.jsp");
		rd.forward(request, response);
	}
}
