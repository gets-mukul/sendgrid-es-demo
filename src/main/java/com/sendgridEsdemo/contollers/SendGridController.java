package com.sendgridEsdemo.contollers;

import com.sendgrid.Response;
import com.sendgridEsdemo.service.SendGridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sendgrid/")
public class SendGridController {

    @Autowired
    private SendGridService sendGridService;

    @GetMapping(value = "template/{templateId}")
    public Response getTemplateById(@PathVariable(value = "templateId", required = true) String templateId){
        return sendGridService.getTemplateById(templateId);
    }
}
