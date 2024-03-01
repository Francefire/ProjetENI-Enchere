package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Article;

public class ArticleDAO {
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS "
			+ "(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES (?, ?, ?, ?, ? , ?, ? , ?, ?)";
	private static final String SQL_UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS"
			+ "SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_categorie=?"
			+ "WHERE no_article=?";
	private static final String SQL_SELECT_ARTICLE_BY_ARTICLE_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	private static final String SQL_SELECT_ALL_ARTICLES = "SELECT * FROM ARTICLES_VENDUS";
	private static final String SQL_DELETE_ARTICLE_BY_ID = "DELETE FROM ARTICLES_VENDUS WHERE no_article=?";

	public void insertArticle(Article a) throws BusinessException {
		try {
			Connection connection = ConnectionProvider.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(SQL_INSERT_ARTICLE);
			statement.setString(1, a.getName());
			statement.setString(2, a.getDescription());
			statement.setDate(3, Date.valueOf(a.getBidStartDate()));
			statement.setDate(4, Date.valueOf(a.getBidEndDate()));
			statement.setDouble(5, a.getInitialPrice());
			statement.setDouble(6, a.getSellingPrice());
			statement.setInt(7, a.getUserId());
			statement.setInt(8, a.getCategoryId());
			statement.execute();	
			
			connection.close();
		} catch (SQLException e) {
			throw new BusinessException(BusinessException.DAL_INSERT_ARTICLE_SQLEXCEPTION);
		}
	}

	public void updateArticle(Article a) throws BusinessException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ARTICLE);
			statement.setString(1, a.getName());
			statement.setString(2, a.getDescription());
			statement.setDate(3, Date.valueOf(a.getBidStartDate()));
			statement.setDate(4, Date.valueOf(a.getBidEndDate()));
			statement.setDouble(5, a.getInitialPrice());
			statement.setDouble(6, a.getSellingPrice());
			statement.setInt(7, a.getCategoryId());
			statement.setInt(8, a.getId());
			statement.executeUpdate();

			connection.close();	
		} catch (SQLException e) {
			throw new BusinessException(BusinessException.DAL_UPDATE_ARTICLE_SQLEXCEPTION);
		}
	}

	public Article selectArticleByArticleId(int articleId) throws BusinessException {
		Article article = null;

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ARTICLE_BY_ARTICLE_ID);
			statement.setInt(1, articleId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				article = new Article();
				article.setId(rs.getInt(1));
				article.setName(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setBidStartDate(rs.getDate(4).toLocalDate());
				article.setBidEndDate(rs.getDate(5).toLocalDate());
				article.setInitialPrice(rs.getDouble(6));
				article.setSellingPrice(rs.getDouble(7));
				article.setUserId(rs.getInt(8));
				article.setCategoryId(rs.getInt(9));
			}

			connection.close();
		} catch (SQLException e) {
			throw new BusinessException(BusinessException.DAL_SELECT_ARTICLE_SQLEXCEPTION);
		}

		return article;
	}

	public List<Article> selectAllArticles() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_ARTICLES);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt(1));
				article.setName(rs.getString(2));
				article.setDescription(rs.getString(3));
				article.setBidStartDate(rs.getDate(4).toLocalDate());
				article.setBidEndDate(rs.getDate(5).toLocalDate());
				article.setInitialPrice(rs.getDouble(6));
				article.setSellingPrice(rs.getDouble(7));
				article.setUserId(rs.getInt(8));
				article.setCategoryId(rs.getInt(9));
				articles.add(article);
			}

			connection.close();	
		} catch (SQLException e) {
			throw new BusinessException(BusinessException.DAL_SELECT_ARTICLE_SQLEXCEPTION);
		}

		return articles;
	}

	public void deleteArticle(int articleId) throws BusinessException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ARTICLE_BY_ID);
			statement.setInt(1, articleId);
			statement.execute();

			connection.close();
		} catch (Exception e) {
			throw new BusinessException(BusinessException.DAL_DELETE_ARTICLE_SQLEXCEPTION);
		}
	}
}
