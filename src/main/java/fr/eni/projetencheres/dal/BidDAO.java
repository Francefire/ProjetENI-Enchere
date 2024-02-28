package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Bid;

public class BidDAO {
	private static final String SQL_INSERT_BID = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	private static final String SQL_SELECT_BY_ARTICLE_ID = "SELECT * FROM ENCHERES WHERE no_article=?";

	public void insertBid(Bid b) throws SQLException {
		Connection connection = ConnectionProvider.getConnection();

		PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BID);
		statement.setDate(1, Date.valueOf(b.getDate()));
		statement.setDouble(2, b.getTotal());
		statement.execute();

		connection.close();
	}

	public Bid selectBidByArticleId(int articleId) throws SQLException {
		Bid bid = new Bid();

		Connection connection = ConnectionProvider.getConnection();

		PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ARTICLE_ID);
		statement.setInt(1, articleId);

		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			bid.setUserId(rs.getInt(1));
			bid.setArticleId(rs.getInt(2));
			bid.setDate(rs.getDate(3).toLocalDate());
			bid.setTotal(rs.getDouble(4));
		}

		connection.close();

		return bid;
	}
}
