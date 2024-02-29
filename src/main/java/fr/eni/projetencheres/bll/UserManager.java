package fr.eni.projetencheres.bll;

import fr.eni.projetencheres.bo.User;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.UserDAO;

public class UserManager {
	//on souhaite créer une unique instance du UserManager, de sorte à ce que ce soit toujours le même qui soit utilisé
	private static UserManager instance = null;
	
	public static UserManager getInstance() {
		if(instance==null) {	//On s'assure qu'il n'y a pas déjà d'instance déjà crée
			instance = new UserManager(); //dans ce cas, on créé une instance de l'UserManager. 
		}
		return instance ; 
	}
		
	//création d'une méthode qui se sert de la DAO factory pour créer un nouvel user. 
		public void createUser (User u) {
			DAOFactory.getUserDAO().insert(u);
		}
		
		
	// création d'une méthode pour
		
}
