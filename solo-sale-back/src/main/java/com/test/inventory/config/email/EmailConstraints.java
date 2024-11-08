package com.test.inventory.config.email;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class EmailConstraints {
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private String isAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String isTls;


    private Properties getPropertiesInstance() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", isAuth);
        properties.put("mail.smtp.starttls.enable", isTls);
        return properties;
    }

    public Session getSessionInstance() {
        return Session.getInstance(getPropertiesInstance(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
}