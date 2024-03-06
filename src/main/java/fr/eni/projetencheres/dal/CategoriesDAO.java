package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Category;

public class CategoriesDAO {

private static final String SQL_SELECT_ALL_LIBELLE = "SELECT libelle FROM CATEGORIES";
private static final String SELECT_ALL_FROM_CATEGORIES = "SELECT * FROM CATEGORIES";
	
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
		// Logguer l'exception *
		throw new BusinessException("Erreur lors de la récupération des libellés de catégorie", e);
	}

	return categorie;
}
public List<Category> selectAllCategory() throws BusinessException {
	List<Category> categoryList = new ArrayList<Category>();

	try {
		Connection connection = ConnectionProvider.getConnection();

		PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_CATEGORIES);

		ResultSet rs = statement.executeQuery(); //stockage du résultat de la requête, ds rs

		while (rs.next()) {
			System.out.println(rs.getString("no_categorie"));
			Category cate = new Category(rs.getInt("no_categorie"),rs.getString("libelle"));
			categoryList.add(cate);
		}

		connection.close();	
	} catch (SQLException e) {
		// Logguer l'exception *
		throw new BusinessException("Erreur lors de la récupération des libellés de catégorie", e);
	}

	return categoryList;
}
}