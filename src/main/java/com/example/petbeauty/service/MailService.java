package com.example.petbeauty.service;

import com.example.petbeauty.connection.PropertyReaderUtil;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class MailService {
    private static final Logger logger = LogManager.getLogger(MailService.class);
    public static void sendVerificationEmail(String toEmail, String verificationCode) {
        String host = PropertyReaderUtil.getProperty("mail.smtp.host");
        String port = PropertyReaderUtil.getProperty("mail.smtp.port");
        String username = PropertyReaderUtil.getProperty("mail.smtp.username");
        String password = PropertyReaderUtil.getProperty("mail.smtp.password");
        String auth = PropertyReaderUtil.getProperty("mail.smtp.auth");
        String starttls = PropertyReaderUtil.getProperty("mail.smtp.starttls.enable");

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttls);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Verify your email");
            String verificationLink = "http://localhost:8080/pet_beauty_war_exploded/controller?command=verify&verification_code=" + verificationCode;
            message.setText("Click the following link to verify your account: " + verificationLink);

            Transport.send(message);
        } catch (MessagingException mex) {
            logger.fatal("Error generating or sending message", mex);
        }
    }
}
