package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;

public class CategoriesDAO {

private static final String SQL_SELECT_ALL_LIBELLE = "SELECT libelle FROM CATEGORIES";
	
public List<String> selectAllLibelle() throws BusinessException {
	List<String> categorie = new ArrayList<String>();

	try {
		Connection connection = ConnectionProvider.getConnection();

		PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_LIBELLE);

		ResultSet rs = statement.executeQuery(); //stockage du résultat de la requête, ds rs

		while (rs.next()) {
			System.out.println(rs.getString("libelle"));

			categorie.add(rs.getString("libelle"));
		}

		connection.close();	
	} catch (SQLException e) {
		e.printStackTrace();
		//TODO implementer l'exeption
	}

	return categorie;
}
}
