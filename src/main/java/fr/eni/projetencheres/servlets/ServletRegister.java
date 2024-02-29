package fr.eni.projetencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.projetencheres.bll.UserManager;
import fr.eni.projetencheres.bo.User;

/**
 * Servlet implementation class ServletCreateAccount
 */
@WebServlet("/Register")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

//    UserManager utilisateurManager= UserManagerImpl.getInstance();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  this.getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//VERIFICATION DES ENTREES DE L'UTILISATEUR
    	String userName = request.getParameter("UserName");
    	String lastName = request.getParameter("LastName");
    	String firstName = request.getParameter("FirstName");
        String email = request.getParameter("Email");
        String phone = request.getParameter("Phone");
        String street = request.getParameter("Street");
        String zipCode = request.getParameter("ZipCode");
        String city = request.getParameter("City");
        String password = request.getParameter("Password");
        String checkPassword = request.getParameter("CheckPassword");
        //AFFICHAGE DES INFOS SAISIES PAR L'UTILISATEUR
        System.out.println("Pseudo : " + userName);
        System.out.println("Nom de l'utilisateur : " + lastName);
        System.out.println("Prénom de l'utilisateur :" + firstName);
        System.out.println("Email : " + email);
        System.out.println("Telephone : " + phone);
        System.out.println("Rue : " + street);
        System.out.println("Code Postal :" + zipCode);
        System.out.println("Mot de passe : " + password);
        System.out.println("Vérification du mot de passe : " + checkPassword );
        
        // Etape de vérification des contraintes
		//        if(user.getNoUtilisateur()>0) {
		//            request.setAttribute("utilisateur", user);
		//            this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		//        }else{
		//            request.setAttribute ("erreur", "Mot de passe ou pseudo incorrect");
		//            this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request,response);
		//        }
        
        //Création de l'instance de type utilisateur sur la base des renseignements fournis juste au-dessus
        User new_user = new User (userName, lastName, firstName, email, phone, street, zipCode, city, password);
        UserManager.getInstance().createUser(new_user);
        //Ci-dessous, commande pour afficher l'ID de l'utilisateur maintenant crée et inscrit dans la BDD
        System.out.println(new_user.getId());
        // Redirection de l'utilisateur vers la page d'accueil, car il est maintenant connecté
        if (new_user.getId() > 0) {
        	request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
        }
        else {
        	request.setAttribute("error", "Il y a une erreur de base de données");
        	request.getRequestDispatcher("WEB-INF/Register.jsp").forward(request, response);
		}
        
        
    }
}
