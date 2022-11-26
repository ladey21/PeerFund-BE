package com.peerfund.peerfundapp.services.implementation;

import com.peerfund.peerfundapp.exceptions.PeerFundException;
import com.peerfund.peerfundapp.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value
    ("${spring.mail.username}") private String sender;


    @Override
    @Async
    public void send(String to, String subject, String email) {
        // Try block to check for exception
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(to);
            mailMessage.setText(email);
            mailMessage.setSubject(subject);

            // Sending the mail
            javaMailSender.send(mailMessage);

        }  catch (Exception e) {
            throw new PeerFundException(HttpStatus.BAD_GATEWAY,"mail service failed");
        }
    }
}
