package com.test.inventory.config.email;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Configuration
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailConstraints emailConstraint;
    @Value("${spring.mail.username}")
    private String username;

    private EmailStatus sendMimeMail(String to, String subject, String body, Boolean isHtml) {
        try {
            Session session = emailConstraint.getSessionInstance();
            MimeMessage message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, isHtml);

            Transport.send(message);
            return new EmailStatus(to, subject, body).emailSuccess();
        } catch (MessagingException e) {
            return new EmailStatus(to, subject, body).emailFailure();
        }
    }

    public EmailStatus sendHtmlMail(String to, String subject, String body) {
        return sendMimeMail(to, subject, body, true);
    }
}