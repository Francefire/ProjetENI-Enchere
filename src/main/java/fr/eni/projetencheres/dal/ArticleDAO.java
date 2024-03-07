package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Article;
import fr.eni.projetencheres.bo.User;

public class ArticleDAO {
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS "
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, url_image, no_utilisateur, no_categorie) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS "
			+ "SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, url_image=?, no_categorie=? "
			+ "WHERE no_article=?";
	private static final String SQL_UPDATE_ARTICLE_SELLING_PRICE = "UPDATE ARTICLES_VENDUS SET prix_vente=? WHERE no_article=?";
	private static final String SQL_UPDATE_ARTICLE_AUCTION_STATE = "UPDATE ARTICLES_VENDUS SET etat_vente=? WHERE no_article=?";
	private static final String SQL_SELECT_ARTICLE_BY_ARTICLE_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String SQL_SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS ORDER BY no_article DESC";
	private static final String SQL_SELECT_TOP_ARTICLES = "SELECT TOP (?) * FROM ARTICLES_VENDUS ORDER BY no_article DESC";
	private static final String SQL_DELETE_ARTICLE_BY_ID = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";

	// méthode pour insérer un article
	public void insertArticle(Article a) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ARTICLE,
					PreparedStatement.RETURN_GENERATED_KEYS);
			statement.setString(1, a.getName());
			statement.setString(2, a.getDescription());
			statement.setDate(3, Date.valueOf(a.getStartDate()));
			statement.setDate(4, Date.valueOf(a.getEndDate()));
			statement.setDouble(5, a.getInitialPrice());
			statement.setDouble(6, a.getSellingPrice());
			statement.setString(7, a.getImageUrl());
			statement.setInt(8, a.getUserId());
			statement.setInt(9, a.getCategoryId());
			statement.execute();

			ResultSet rs = statement.getGeneratedKeys();

			if (rs.next()) {
				a.setId(rs.getInt(1));
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'insertion d'un article", e.getMessage());
		}
	}

	// Méthode pour mettre à jour un article
	public void updateArticle(Article a) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ARTICLE);
			statement.setString(1, a.getName());
			statement.setString(2, a.getDescription());
			statement.setDate(3, Date.valueOf(a.getStartDate()));
			statement.setDate(4, Date.valueOf(a.getEndDate()));
			statement.setDouble(5, a.getInitialPrice());
			statement.setDouble(6, a.getSellingPrice());
			statement.setString(7, a.getImageUrl());
			statement.setInt(8, a.getCategoryId());
			statement.setInt(9, a.getId());
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new DataException("la mise à jour d'un article", e.getMessage());
		}
	}

	public void updateArticleSellingPrice(int articleId, double sellingPrice) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ARTICLE_SELLING_PRICE);
			statement.setDouble(1, sellingPrice);
			statement.setInt(2, articleId);
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new DataException("la mise à jour du prix de vente d'un article", e.getMessage());
		}
	}

	public void updateArticleAuctionState(int articleId, String auctionState) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ARTICLE_AUCTION_STATE);
			statement.setInt(1, articleId);
			statement.setString(2, auctionState);
			statement.executeUpdate();

			connection.close();
		} catch (SQLException e) {
			throw new DataException("la mise à jour de l'état de vente d'un article", e.getMessage());
		}
	}

	public Article selectArticleByArticleId(int articleId) throws DataException {
		Article article = null;

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ARTICLE_BY_ARTICLE_ID);
			statement.setInt(1, articleId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				article = resultSetToArticle(rs);
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention d'un article par son identifiant", e.getMessage());
		}

		return article;
	}

	public List<Article> selectAllArticles() throws DataException {
		List<Article> articles = new ArrayList<Article>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ARTICLES);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Article article = resultSetToArticle(rs);
				articles.add(article);
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention de tous les articles", e.getMessage());
		}

		return articles;
	}

	public List<Article> selectTopArticles(int top) throws DataException {
		List<Article> articles = new ArrayList<Article>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TOP_ARTICLES);
			statement.setInt(1, top);
			statement.setInt(2, top);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Article article = resultSetToArticle(rs);
				articles.add(article);
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention des articles du top " + top, e.getMessage());
		}

		return articles;
	}

	public List<Article> selectArticlesWhere(String name, int categoryId, LocalDate startDate, LocalDate endDate)
			throws DataException {
		List<Article> articles = new ArrayList<Article>();

		/*
		 * try { Connection connection = ConnectionProvider.getConnection();
		 * 
		 * StringBuilder query = new StringBuilder(SQL_SELECT_ALL_ARTICLES + " WHERE ");
		 * 
		 * if (name != aull && !name.isEmpty()) {
		 * query.append("nom_article LIKE '%?%'"); }
		 * 
		 * if (categoryId > 0) { query.append("no_categorie=?"); }
		 * 
		 * if (startDate != null) { query.append("date_debut_encheres=?"); }
		 * 
		 * if (endDate != null) { query.append("date_fin_encheres=?"); }
		 * 
		 * PreparedStatement statement = connection.prepareStatement(query.toString());
		 * statement.setString(1, name); statement.setInt(2, categoryId);
		 * statement.setDate(3, Date.valueOf(startDate)); statement.setDate(4,
		 * Date.valueOf(startDate));
		 * 
		 * ResultSet rs = statement.executeQuery();
		 * 
		 * while (rs.next()) { Article article = new Article();
		 * article.setId(rs.getInt(1)); article.setName(rs.getString(2));
		 * article.setDescription(rs.getString(3));
		 * article.setStartDate(rs.getDate(4).toLocalDate());
		 * article.setEndDate(rs.getDate(5).toLocalDate());
		 * article.setInitialPrice(rs.getDouble(6));
		 * article.setSellingPrice(rs.getDouble(7)); article.setUserId(rs.getInt(8));
		 * article.setCategoryId(rs.getInt(9)); articles.add(article); }
		 * 
		 * connection.close(); } catch (SQLException e) { throw new
		 * DataException(DataException.DAL_SELECT_ARTICLE_SQLEXCEPTION); }
		 */
		return articles;
	}

	public void deleteArticleByArticleId(int articleId) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ARTICLE_BY_ID);
			statement.setInt(1, articleId);
			statement.execute();

			connection.close();
		} catch (Exception e) {
			throw new DataException("la suppression d'un article par son identifiant", e.getMessage());
		}
	}
	
	private Article resultSetToArticle(ResultSet rs) throws SQLException {
		Article article = new Article();
		article.setId(rs.getInt(1));
		article.setName(rs.getString(2));
		article.setDescription(rs.getString(3));
		article.setStartDate(rs.getDate(4).toLocalDate());
		article.setEndDate(rs.getDate(5).toLocalDate());
		article.setInitialPrice(rs.getDouble(6));
		article.setSellingPrice(rs.getDouble(7));
		article.setAuctionState(rs.getString(8));
		article.setImageUrl(rs.getString(9));
		article.setUserId(rs.getInt(10));
		article.setCategoryId(rs.getInt(11));
		
		return article;
	}
}
