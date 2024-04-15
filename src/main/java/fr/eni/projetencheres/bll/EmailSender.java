package fr.eni.projetencheres.bll;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
//	****************CHATGPT********************
	public static void sendPasswordRecoveryEmail(String mailFromUser, String newPassword) {
//	****************CHATGPT********************

//    public static void sendPasswordRecoveryEmail(String mailFromUser) {
        final String username = "eni-projets@hotmail.com"; // Adresse email de l'expéditeur
        final String password = "********"; // Mot de passe de l'expéditeur
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // Serveur SMTP Microsoft
        props.put("mail.smtp.port", "587"); 
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Adresse email de l'expéditeur
            message.setRecipients(Message.RecipientType.TO,
            		InternetAddress.parse(mailFromUser)); // Adresse email du destinataire
            message.setSubject("Réinitialisation de mot de passe"); // Objet du mail
            message.setText("Bonjour,\n\n"
                + "Vous avez demandé à réinitialiser votre mot de passe. Voici votre nouveau mot de passe : " + newPassword + " Vous pouvez utiliser ce nouveau mot de passe en vous rendant ici :\n"
                + "http://localhost:8080/ProjetENI-Enchere\n\n"
                + "Ceci est un email automatique, merci de ne pas répondre.");

            Transport.send(message);

            System.out.println("Email envoyé avec succès à : " + mailFromUser);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
