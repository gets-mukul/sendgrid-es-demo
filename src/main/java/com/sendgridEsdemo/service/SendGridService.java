package com.sendgridEsdemo.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class SendGridService {


    @Value("${app.sendgrid.key}")
    private String sendgridKey;

    public Response getTemplateById(final String templateId){
        try {
            final SendGrid sg = new SendGrid(sendgridKey);
            final Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint(String.format("/templates/%s", templateId));
            return sg.api(request);
        } catch (final IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
