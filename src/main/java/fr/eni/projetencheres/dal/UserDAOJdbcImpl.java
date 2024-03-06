package fr.eni.projetencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import fr.eni.projetencheres.bll.BusinessException;
import fr.eni.projetencheres.bo.User;

public class UserDAOJdbcImpl implements UserDAO {

	private static final String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) VALUES(?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String SELECT_BY_USERNAME = "SELECT * FROM UTILISATEURS WHERE pseudo=?";
	private static final String SELECT_BY_EMAIL = "SELECT * FROM UTILISATEURS WHERE email=?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM UTILISATEURS";
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	private static final String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur=?";
	private static final String CHECK = " SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";

	@Override
	public void insert(User user) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public User selectById(int id) throws BusinessException {
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
			} else {
				throw new BusinessException(BusinessException.DAL_USER_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new BusinessException(BusinessException.DAL_SELECT_USER_EXCEPTION);
		}
		return user;
	}

	@Override
	public User selectByUsername(String pseudo) {
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
			e.printStackTrace();
		}

		return user;
	}
	
	@Override
	public List<User> selectAllUsers() {
		List<User> users = null;
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	@Override
	public void update(User user) {
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

	// Création d'une méthode pour comparer l'username et le password
	public int check(String username, String password) throws BusinessException {
		int id = 0;
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(CHECK);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				id = rs.getInt("no_utilisateur");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(BusinessException.BLL_LOGIN_USER_EXCEPTION);
		}
		return id;
	}

	@Override
	public User login(String userName, String Password) throws BusinessException {
		return null;
	}
}
