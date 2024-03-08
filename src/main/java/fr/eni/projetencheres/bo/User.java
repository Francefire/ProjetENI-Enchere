package fr.eni.projetencheres.bo;


//Classe permettant de définir un utilisateur, "qu'est ce qui le caractérise" ? 
public class User {
	private int id;
	private String username;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String street;
	private String zipCode;
	private String city;
	private String password;
	private int credit;
	private boolean admin;
	private boolean disabled;

//	notre premier constructeur vide, celui par défaut
	public User() {
		this.credit = 0;
		this.admin = false;

	}

//constructeur qui permettra la création de l'user via l'interface ihm (site internet) : ce constructeur ne servira QUE lors de la création de sa fiche
	public User(String username, String lastName, String firstName, String email, String phoneNumber, String street,
			String zipCode, String city, String password) {
		this();
		this.username = username;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.password = password;

	}

//constructeur qui permettra de créer un user si on connait déjà son id - (lorsqu'il est déjà inscrit, et qu'il se connecte)
	public User(int id, String username, String lastName, String firstName, String email, String phoneNumber,
			String street, String zipCode, String city, String password) {
		this(username, lastName, firstName, email, phoneNumber, street, zipCode, city, password);
		this.id = id;
	}

	// Constructuer qui permet de creer une copie de l'utilisateur
	public User(User u) {
		this(u.id, u.username, u.lastName, u.firstName, u.email, u.phoneNumber, u.street, u.zipCode, u.city,
				u.password);
		this.credit = u.credit;
		this.admin = u.admin;

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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", street=" + street + ", zipCode=" + zipCode
				+ ", city=" + city + ", password=" + password + ", credit=" + credit + ", admin=" + admin + "]";
	}
}
