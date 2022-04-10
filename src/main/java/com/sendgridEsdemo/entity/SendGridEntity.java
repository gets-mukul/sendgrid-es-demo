package com.sendgridEsdemo.entity;

import com.sendgrid.helpers.mail.objects.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;


@Data
@Document(indexName= "notification_tracker")
@AllArgsConstructor
@NoArgsConstructor
public class SendGridEntity {

    @Id
    private String id;

    private String emailFrom;

    private List<Email> emailTo;

    private String subject;

    private String body;

    private String templateId;


}
