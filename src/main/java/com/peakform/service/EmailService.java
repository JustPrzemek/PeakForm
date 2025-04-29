package com.peakform.service;

import com.peakform.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendVerificationEmail(User user, String verificationUrl){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Verification Email");
        message.setText("Your verification code is " + verificationUrl);
        mailSender.send(message);
    }

    public void sendPasswordResetEmail(User user, String passwordResetUrl){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Password Reset Email");
        message.setText("Your password reset link is " + passwordResetUrl + "\n The link is valid for 24 hours. ");
        mailSender.send(message);
    }
}
