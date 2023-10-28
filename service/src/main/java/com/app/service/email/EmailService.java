package com.app.service.email;

public interface EmailService {
    void send(String receiver, String subject, String htmlContent);
}
