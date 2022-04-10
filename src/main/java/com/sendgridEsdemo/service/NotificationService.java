package com.sendgridEsdemo.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import com.sendgridEsdemo.entity.SendGridEntity;
import com.sendgridEsdemo.repository.SendGridESRepository;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@Slf4j
public class NotificationService {

    @Value("${app.sendgrid.templateId}")
    private String templateId;

    @Value("${app.sendgrid.dynamic}")
    private String dynamicTemplate;

    @Autowired
    private SendGrid sendGrid;

    @Autowired
    private SendGridESRepository sendGridESRepository;

    public String sendStaticMail(final String emailId)  {
        try {
            final Mail mail = prepareMail(emailId, Boolean.FALSE);
            mail.setSubject("Test for static template");
            final Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            final SendGridEntity entity = new SendGridEntity(RandomString.make(), mail.getFrom().getEmail(), mail.getPersonalization().get(0).getTos(), mail.getSubject(), "this is some body", mail.getTemplateId());
            sendGridESRepository.save(entity);
            if(response!=null) {
                log.info("response code from sendgrid {}", response.getHeaders());
            }
        } catch (final IOException e) {
            log.error(e.getMessage());
            return "error in sent mail!";
        }
        return "mail has been sent check your inbox!";

    }

    public String sendDynamicMail(final String emailId, final Map<String, String> templateVariables){
        try{
            final Mail mail = prepareMail(emailId, Boolean.TRUE);
            mail.getPersonalization().get(0).addDynamicTemplateData("name", templateVariables.get("name"));
            mail.getPersonalization().get(0).addDynamicTemplateData("token", templateVariables.get("token"));
            mail.getPersonalization().get(0).addDynamicTemplateData("city", templateVariables.get("city"));
            mail.setSubject("Test for dynamic template");
            final Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendGrid.api(request);
            if(response!=null) {
                log.info("response code from sendgrid {}", response.getHeaders());
            }
        }catch (final Exception e){
            log.error(e.getMessage());
            return "error in sent mail!";
        }
        return "dynamic mail has been sent check your inbox!";
    }

    public Mail prepareMail(final String emailId, final Boolean isDynamic) {
        final Mail mail = new Mail();
        final Email fromEmail = new Email();
        fromEmail.setEmail("mukul.prajapati@licious.com");
        mail.setFrom(fromEmail);
        final Email to = new Email();
        to.setEmail(emailId);
        final Personalization personalization = new Personalization();
        personalization.addTo(to);
        mail.addPersonalization(personalization);
        mail.setTemplateId(dynamicTemplate);
        if(isDynamic){
            mail.setTemplateId(dynamicTemplate);
        }else{
            mail.setTemplateId(templateId);
        }
        return mail;
    }
}
