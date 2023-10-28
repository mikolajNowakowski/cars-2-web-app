package com.app.service.email.impl;


import com.app.service.email.EmailConfiguration;
import com.app.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final Mailer mailer;
    private final EmailConfiguration emailConfiguration;
    private static final Logger logger = LogManager.getLogger("DebugLogger");
    @Override
    public void send(String receiver, String subject, String htmlContent) {
        var email = EmailBuilder
                .startingBlank()
                .from(emailConfiguration.fromName(), emailConfiguration.fromAddress())
                .withSubject(subject)
                .withHTMLText(htmlContent)
                .to(receiver)
                .buildEmail();
        logger.debug("Sending mail to" + receiver);
        mailer
                .sendMail(email)
                .thenAccept(result -> {});
    }
}
