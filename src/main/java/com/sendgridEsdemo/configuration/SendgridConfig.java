package com.sendgridEsdemo.configuration;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendgridConfig {

    @Value("${app.sendgrid.key}")
    private String sendgridKey;
    @Bean
    public SendGrid intiSendGrid(){
        return new SendGrid(sendgridKey);
    }
}
