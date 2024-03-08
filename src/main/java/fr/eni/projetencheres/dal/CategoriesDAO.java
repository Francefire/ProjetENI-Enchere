package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Category;

public class CategoriesDAO {

	private static final String SQL_SELECT_ALL_LABELS = "SELECT libelle FROM CATEGORIES";
	private static final String SELECT_ALL_FROM_CATEGORIES = "SELECT * FROM CATEGORIES";
	private static final String INSERT_CATEGORY = "INSERT INTO CATEGORIES(libelle) VALUES(?)";
    private static final String UPDATE_CATEGORY = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM CATEGORIES WHERE no_categorie = ?";

	public List<String> selectAllLabels() throws DataException {
		List<String> categories = new ArrayList<String>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_LABELS);

			ResultSet rs = statement.executeQuery(); // stockage du résultat de la requête, ds rs

			while (rs.next()) {
				categories.add(rs.getString("libelle"));
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("la récupération des libellés de catégorie", e.getMessage());
		}

		return categories;
	}

	public List<Category> selectAllCategories() throws DataException {
		List<Category> categories = new ArrayList<Category>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SELECT_ALL_FROM_CATEGORIES);

			ResultSet rs = statement.executeQuery(); // stockage du résultat de la requête, ds rs

			while (rs.next()) {
				Category cate = new Category(rs.getInt("no_categorie"), rs.getString("libelle"));
				categories.add(cate);
			}

			connection.close();
		} catch (SQLException e) {
			// Logguer l'exception *
			throw new DataException("la récupération des libellés de catégorie", e.getMessage());
		}

		return categories;
	}
	
			public void createCategory(Category category) throws DataException {
	        try (Connection connection = ConnectionProvider.getConnection();
	             PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY)) {
	            statement.setString(1, category.getLibelle());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	        	throw new DataException("Erreur lors de la création de la catégorie", e.getMessage());
	        }
		}
			
			public void updateCategory(Category category) throws DataException {
		        try (Connection connection = ConnectionProvider.getConnection();
		             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
		            statement.setString(1, category.getLibelle());
		            statement.setInt(2, category.getId());
		            statement.executeUpdate();
		        } catch (SQLException e) {
		            throw new DataException("Erreur lors de la mise à jour de la catégorie", e);
		        }
			}
			
			
			public void deleteCategory(int categoryId) throws DataException {
		        try (Connection connection = ConnectionProvider.getConnection();
		             PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY)) {
		            statement.setInt(1, categoryId);
		            statement.executeUpdate();
		        } catch (SQLException e) {
		            throw new DataException("Erreur lors de la suppression de la catégorie", e.getMessage());
		        }
			
			}
					
}
