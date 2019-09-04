package eu.tcitsolutions.dietApp.core.mail.service;

public interface EmailService {
    void sendEmail(String to, String subject, String content);
}
