/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Account;

/**
 *
 * @author PhuongNH
 */
public class Mailler {

    public static void sendAccount(Account account, String emailTo) {
        String emailForm = "phuong09041997@gmail.com";
        String password = "imhamien";

        //get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailForm, password);
            }
        });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailForm));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("[FROM ASSIGNMENT PRJ - HE150172] Welcome!");
            String content = "Notice: This is your account to login at Human "
                    + "Resource(PRJ301)-HE150172: [E-mail: "+account.getUsername()+" / Password: "+ account.getPassword()+"].";
            message.setText(content);

            //send message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendOTP(String emailTo, String OTP) {
        String emailForm = "phuong09041997@gmail.com";
        String password = "imhamien";

        //get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        Session session;
        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailForm, password);
            }
        });
        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailForm));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            message.setSubject("[FROM ASSIGNMENT PRJ - HE150172] OTP Code");
            String content = "Please verify your account using this OTP code: " + OTP;
            message.setText(content);

            //send message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
