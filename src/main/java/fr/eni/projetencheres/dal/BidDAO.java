package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Bid;

public class BidDAO {
	private static final String SQL_INSERT_BID = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	private static final String SQL_SELECT_BIDS_BY_ARTICLE_ID = "SELECT * FROM ENCHERES WHERE no_article=? ORDER BY no_article DESC";

	public void insertBid(Bid b) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_INSERT_BID);
			statement.setInt(1, b.getUserId());
			statement.setInt(2, b.getArticleId());
			statement.setTimestamp(3, Timestamp.valueOf(b.getDateTime()));
			statement.setDouble(4, b.getAmount());
			statement.execute();

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'insertion d'une enchère", e.getMessage());
		}
	}

	public List<Bid> selectBidsByArticleId(int articleId) throws DataException {
		List<Bid> bids = new ArrayList<Bid>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BIDS_BY_ARTICLE_ID);
			statement.setInt(1, articleId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Bid bid = new Bid();
				bid.setUserId(rs.getInt(1));
				bid.setArticleId(rs.getInt(2));
				bid.setDateTime(rs.getTimestamp(3).toLocalDateTime());
				bid.setAmount(rs.getDouble(4));
				bids.add(bid);
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention de toutes les enchères par leurs identifiants d'article",
					e.getMessage());
		}

		return bids;
	}

	public Bid selectLastBidForAticleId(int articleId) throws DataException {
		Bid bid = new Bid();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BIDS_BY_ARTICLE_ID);
			statement.setInt(1, articleId);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				bid.setUserId(rs.getInt(1));
				bid.setArticleId(rs.getInt(2));
				bid.setDateTime(rs.getTimestamp(3).toLocalDateTime());
				bid.setAmount(rs.getDouble(4));
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention de la dernière enchère pour l'article d'identifiant" + articleId,
					e.getMessage());
		}

		return bid;
	}

	// une méthode utilitaire pour convertir un objet ResultSet (résultat d'une
	// requête SQL) en un objet de type Bid *
	public Bid selectBid(ResultSet rs) throws SQLException {
		Bid bid = new Bid();
		bid.setUserId(rs.getInt("no_utilisateur"));
		bid.setArticleId(rs.getInt("no_article"));
		bid.setDateTime(rs.getTimestamp("date_enchere").toLocalDateTime());
		bid.setAmount(rs.getDouble("montant_enchere"));
		return bid;
	}
}
