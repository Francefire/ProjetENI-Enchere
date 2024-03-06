package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.Bid;

public class BidDAO {
	private static final String SQL_INSERT_BID = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	private static final String SQL_SELECT_BIDS_BY_ARTICLE_ID = "SELECT no_utilisateur, date_enchere, montant_enchere FROM ENCHERES WHERE no_article=?";

	public void insertBid(Bid b) throws BusinessException {
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
			throw new BusinessException(BusinessException.DAL_INSERT_BID_SQLEXCEPTION);
		}
	}

	public List<Bid> selectBidsByArticleId(int articleId) throws BusinessException {
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
			e.printStackTrace();
			throw new BusinessException(BusinessException.DAL_SELECT_BID_SQLEXCEPTION);
		}

		return bids;
	}
}
