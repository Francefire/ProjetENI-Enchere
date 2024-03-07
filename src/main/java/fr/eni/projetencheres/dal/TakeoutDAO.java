package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.Bid;
import fr.eni.projetencheres.bo.Takeout;

public class TakeoutDAO {
	private static final String SQL_INSERT_TAKEOUT = "INSERT INTO RETRAITS "
			+ "(no_article, rue, code_postal, ville, date_retrait) " + "VALUES (?, ?, ?, ?, ?)";
	private static final String SQL_SELECT_ALL_TAKEOUTS = "SELECT * FROM RETRAITS";

	public void insertTakeout(Takeout t) throws DataException {
		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_INSERT_TAKEOUT);
			statement.setInt(1, t.getArticleId());
			statement.setString(2, t.getStreet());
			statement.setString(3, t.getZipCode());
			statement.setString(4, t.getCity());
			statement.setDate(5, Date.valueOf(t.getDate()));
			statement.execute();

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'insertion d'un retrait", e.getMessage());
		}
	}

	public List<Takeout> selectAllTakeouts() throws DataException {
		List<Takeout> takeouts = new ArrayList<Takeout>();

		try {
			Connection connection = ConnectionProvider.getConnection();

			PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_TAKEOUTS);

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Takeout takeout = new Takeout();
				takeout.setArticleId(rs.getInt(1));
				takeout.setStreet(rs.getString(2));
				takeout.setZipCode(rs.getString(3));
				takeout.setCity(rs.getString(4));
				takeout.setDate(rs.getDate(5).toLocalDate());
				takeouts.add(takeout);
			}

			connection.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention de tous les retraits", e.getMessage());
		}

		return takeouts;
	}
}
