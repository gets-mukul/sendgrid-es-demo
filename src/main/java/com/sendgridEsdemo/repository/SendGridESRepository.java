package com.sendgridEsdemo.repository;

import com.sendgridEsdemo.entity.SendGridEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SendGridESRepository extends ElasticsearchRepository<SendGridEntity, String> {
}
