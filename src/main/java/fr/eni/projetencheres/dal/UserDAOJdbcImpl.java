package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.projetencheres.bo.User;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String INSERT = "INSERT INTO Users(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM Users WHERE id=?";
	private static final String SELECT_BY_PSEUDO = "SELECT * FROM Users WHERE pseudo=?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM Users WHERE email=?";
	private static final String UPDATE = "UPDATE Users SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE id=?";
	private static final String DELETE = "DELETE FROM Users WHERE id=?";

	@Override
	public void insert(User user) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getStreet());
			pstmt.setString(7, user.getPostalCode());
			pstmt.setString(8, user.getCity());
			pstmt.setString(9, user.getPassword());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User selectById(int id) {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User selectByPseudo(String pseudo) {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_PSEUDO);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	@Override
	public User selectByEmail(String email) {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return user;
	}

	@Override
	public void update(User user) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, user.getPseudo());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getStreet());
			pstmt.setString(7, user.getPostalCode());
			pstmt.setString(8, user.getCity());
			pstmt.setString(9, user.getPassword());
			pstmt.setInt(10, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
