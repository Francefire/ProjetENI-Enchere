package fr.eni.projetencheres.dal;

public abstract class DAOFactory {
	
	public static UserDAO getUserDAO() {
		return new UserDAOJdbcImpl();
	}
}
