package fr.eni.projetencheres.dal;

import fr.eni.projetencheres.bo.User;

public interface UserDAO {
	public void insert(User user);
	public User selectById(int id);
	public User selectByUsername(String username);
	public User selectByEmail(String email);
	public void update(User user);
	public void delete(int id);
}
