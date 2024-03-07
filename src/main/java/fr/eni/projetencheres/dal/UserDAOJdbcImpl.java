package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.projetencheres.bo.User;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String SELECT_BY_USERNAME = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String UPDATE_CREDITS_FOR_USER = "UPDATE UTILISATEURS SET credit=credit+? WHERE no_utilisateur=?";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String CHECK = "SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String MAIL = "SELECT no_utilisateur FROM UTILISATEURS WHERE email=?";
	private static final String LOGIN = "SELECT * FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";

	@Override
	public void insert(User user) throws DataException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getStreet());
			pstmt.setString(7, user.getZipCode());
			pstmt.setString(8, user.getCity());
			pstmt.setString(9, user.getPassword());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			throw new DataException("l'insertion d'un utilisateur", e.getMessage());
		}
	}

	@Override
	public User selectById(int id) throws DataException {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("no_utilisateur"));
				user.setUsername(rs.getString("pseudo"));
				user.setLastName(rs.getString("nom"));
				user.setFirstName(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("telephone"));
				user.setStreet(rs.getString("rue"));
				user.setZipCode(rs.getString("code_postal"));
				user.setCity(rs.getString("ville"));
				user.setPassword(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdmin(rs.getBoolean("administrateur"));
				user.setDisabled(rs.getBoolean("active"));
			}
		} catch (Exception e) {
			throw new DataException("l'obtention d'un utilisateur par son identifiant", e.getMessage());
		}
		return user;
	}

	@Override
	public User selectByUsername(String pseudo) throws DataException {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_USERNAME);
			pstmt.setString(1, pseudo);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("no_utilisateur"));
				user.setUsername(rs.getString("pseudo"));
				user.setLastName(rs.getString("nom"));
				user.setFirstName(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("telephone"));
				user.setStreet(rs.getString("rue"));
				user.setZipCode(rs.getString("code_postal"));
				user.setCity(rs.getString("ville"));
				user.setPassword(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdmin(rs.getBoolean("administrateur"));
				user.setDisabled(rs.getBoolean("active"));
			}
		} catch (SQLException e) {
			throw new DataException("l'obtention d'un utilisateur par son pseudonyme", e.getMessage());
		}

		return user;
	}

	@Override
	public User selectByEmail(String email) throws DataException {
		User user = null;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_EMAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("no_utilisateur"));
				user.setUsername(rs.getString("pseudo"));
				user.setLastName(rs.getString("nom"));
				user.setFirstName(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("telephone"));
				user.setStreet(rs.getString("rue"));
				user.setZipCode(rs.getString("code_postal"));
				user.setCity(rs.getString("ville"));
				user.setPassword(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdmin(rs.getBoolean("administrateur"));
				user.setDisabled(rs.getBoolean("active"));
			}
		} catch (SQLException e) {
			throw new DataException("l'obtention d'un utilisateur par son email", e.getMessage());
		}

		return user;
	}

	@Override
	public List<User> selectAllUsers() throws DataException {
		List<User> users = new ArrayList<User>();
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL_USERS);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("no_utilisateur"));
				user.setUsername(rs.getString("pseudo"));
				user.setLastName(rs.getString("nom"));
				user.setFirstName(rs.getString("prenom"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNumber(rs.getString("telephone"));
				user.setStreet(rs.getString("rue"));
				user.setZipCode(rs.getString("code_postal"));
				user.setCity(rs.getString("ville"));
				user.setPassword(rs.getString("mot_de_passe"));
				user.setCredit(rs.getInt("credit"));
				user.setAdmin(rs.getBoolean("administrateur"));
				user.setDisabled(rs.getBoolean("active"));
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DataException("l'obtention de tous les utilisateurs", e.getMessage());
		}

		return users;
	}

	@Override
	public void update(User user) throws DataException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getFirstName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhoneNumber());
			pstmt.setString(6, user.getStreet());
			pstmt.setString(7, user.getZipCode());
			pstmt.setString(8, user.getCity());
			pstmt.setString(9, user.getPassword());
			pstmt.setInt(10, user.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataException("la mise à jour d'un utilisateur", e.getMessage());
		}
	}

	@Override
	public void updateCreditsForUser(double credits, int userId) throws DataException {
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDITS_FOR_USER);
			pstmt.setDouble(1, credits);
			pstmt.setInt(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataException("la mise à jour des crédits d'un utilisateur", e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws DataException {
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new DataException("la suppression d'un utilisateur par son identifiant", e.getMessage());
		}
	}

	// la méthode resultSetToUser qui convertit un ResultSet en objet User pour les
	// opérations de sélection *
	private User resultSetToUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("no_utilisateur"));
		user.setUsername(rs.getString("pseudo"));
		user.setLastName(rs.getString("nom"));
		user.setFirstName(rs.getString("prenom"));
		user.setEmail(rs.getString("email"));
		user.setPhoneNumber(rs.getString("telephone"));
		user.setStreet(rs.getString("rue"));
		user.setZipCode(rs.getString("code_postal"));
		user.setCity(rs.getString("ville"));
		user.setPassword(rs.getString("mot_de_passe"));
		return user;
	}

	// Création d'une méthode pour comparer l'username et le password
	public int check(String username, String password) throws DataException {
		int id = 0;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CHECK);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("no_utilisateur");
			}
		} catch (SQLException e) {
			throw new DataException(
					"l'obtention de l'identifiant d'un utilisateur par son pseudonyme et son mot de passe",
					e.getMessage());
		}
		return id;
	}

	public User login(String username, String password) throws DataException {
		User user = null;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(LOGIN);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet res = pstmt.executeQuery();
			if (res.next()) {
				user = new User();
				user.setId(res.getInt("no_utilisateur"));
				user.setUsername(res.getString("pseudo"));
				user.setLastName(res.getString("nom"));
				user.setFirstName(res.getString("prenom"));
				user.setEmail(res.getString("email"));
				user.setPhoneNumber(res.getString("telephone"));
				user.setStreet(res.getString("rue"));
				user.setZipCode(res.getString("code_postal"));
				user.setCity(res.getString("ville"));
				user.setPassword(res.getString("mot_de_passe"));
			}
			cnx.close();
		} catch (SQLException e) {
			throw new DataException("l'obtention d'un utilisateur par son pseudonyme et son mot de passe",
					e.getMessage());
		}
		return user;
	}
//TODO : renommer cette méthode (genre selectIdFromEmail)
	public int mailExist(String email) throws DataException {
		int id = 0;
		try {
			Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement pstmt = cnx.prepareStatement(MAIL);
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("no_utilisateur");
			}
		} catch (Exception e) {
			throw new DataException("erreur lors de la requête vers la base de données", e.getMessage());
		}
		return id;
	}

	public void updateNewPassword(String mailFromUser, String password) throws DataException {
		// on vérifie si l'adresse mail renseignée par l'utilisateur est bien dans la
		// base de données
		if (mailExist(mailFromUser) != 0) {
			try {
				Connection cnx;
				cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement("UPDATE UTILISATEURS SET mot_de_passe=? WHERE email=?");
				pstmt.setString(1, password);
				pstmt.setString(2, mailFromUser);
				pstmt.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
