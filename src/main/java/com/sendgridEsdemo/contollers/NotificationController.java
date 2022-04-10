package com.sendgridEsdemo.contollers;

import com.sendgridEsdemo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/send/")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("static-mail/{emailId}")
    public String sendStaticMail(@PathVariable(value = "emailId", required = true) String emailId){
        return notificationService.sendStaticMail(emailId);
    }

    @GetMapping("dynamic-mail/{emailId}")
    public String sendDynamicMail(@PathVariable(value = "emailId", required = true) String emailId, @RequestBody Map<String, String> templateVariables){
        return notificationService.sendDynamicMail(emailId, templateVariables);
    }
}
