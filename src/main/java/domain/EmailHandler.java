package domain;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailHandler {//Change this for testing but remember to change it back afterwards, or change ur e-mail password afterwards
    private static final String SMTP_HOST = "smtp.example.com"; // Change this with the server in question
    private static final String SMTP_PORT = "587"; // (usually 587 or 465)
    private static final String SMTP_AUTH_USER = "your-email@example.com";
    private static final String SMTP_AUTH_PWD = "your-email-password";

    public static void sendEmail(String toEmail, String userId, String password) {
        // Set up the SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a session with the properties and the authenticator
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_AUTH_USER));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Registration Confirmation");

            // Create the email body
            String emailContent = "Dear user,\n\n" +
                    "You have successfully registered with the following details:\n" +
                    "User ID: " + userId + "\n" +
                    "Password: " + password + "\n\n" +
                    "Registration Date and Time: " + java.time.LocalDateTime.now() + "\n\n" +
                    "Best regards,\n" +
                    "Your Company";

            message.setText(emailContent);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
