package fr.eni.projetencheres.bll;
import java.security.SecureRandom;

public class NewPwdGenerator {

	    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    private static final String DIGITS = "0123456789";
	    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+";

	    public static String generatePassword(int length) {
	        StringBuilder password = new StringBuilder();
	        SecureRandom random = new SecureRandom();

	        // On ajoute au moins un caractère de chaque type
	        password.append(LOWERCASE_LETTERS.charAt(random.nextInt(LOWERCASE_LETTERS.length())));
	        password.append(UPPERCASE_LETTERS.charAt(random.nextInt(UPPERCASE_LETTERS.length())));
	        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
	        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

	        // On complète le reste du mot de passe avec des caractères aléatoires
	        for (int i = 4; i < length; i++) {
	            String charSet = LOWERCASE_LETTERS + UPPERCASE_LETTERS + DIGITS + SPECIAL_CHARACTERS;
	            password.append(charSet.charAt(random.nextInt(charSet.length())));
	        }

	        // On mélange le mot de passe
	        for (int i = 0; i < length; i++) {
	            int randomIndex = random.nextInt(length);
	            char temp = password.charAt(i);
	            password.setCharAt(i, password.charAt(randomIndex));
	            password.setCharAt(randomIndex, temp);
	        }

	        return password.toString();
	    }
}
