package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Praparing email to send....");
        try {
            SimpleMailMessage mailMessage = createmailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent");
        } catch (MailException e) {
            log.info("Failed to send an email:" + e.getMessage() + e.getCause(), e);
        }
    }

    private  SimpleMailMessage createmailMessage (final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
}
