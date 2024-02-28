package fr.eni.projetencheres.bo;

public class User {
	private int id;
	private String username;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String street;
	private String postalCode;
	private String city;
	private String password;
	private int credit;
	private boolean admin;
	
	public User(String username, String lastName, String firstName, String email, String phoneNumber, String street,
			String postalCode, String city, String password) {
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.password = password;
		this.credit = 0;
		this.admin = false;
	}
	
	public User(int id, String username, String lastName, String firstName, String email, String phoneNumber,
			String street, String postalCode, String city, String password) {
		this(username, lastName, firstName, email, phoneNumber, street, postalCode, city, password);
		this.id = id;
	}
	
	public User() {
		this.credit = 0;
		this.admin = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	
	
	
	
	
	
}
