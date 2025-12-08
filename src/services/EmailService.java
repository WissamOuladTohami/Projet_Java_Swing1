/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author cli
 */
public class EmailService {
    
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String FROM_EMAIL = "wissamouladtohami@gmail.com";
    private static final String FROM_PASSWORD = "tfekvrvfdlhkzesy";

    public static void sendPasswordResetEmail(String toEmail, String tempPassword) throws MessagingException {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Réinitialisation de votre mot de passe");
        message.setText("Bonjour,\n\n"
                + "Voici votre nouveau mot de passe temporaire : " + tempPassword + "\n\n"
                + "Merci de vous connecter et de le changer dès que possible.\n\n"
                + "Cordialement.");

        Transport.send(message);
    }
    
}
