package com.kms.com.kms;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration
{
    @Override
    public MongoClient reactiveMongoClient() {
        return MongoClients.create();
    }

    @Override
    protected String getDatabaseName() {
        return "demo";
    }

    @Override
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate()
    {
        return new ReactiveMongoTemplate(reactiveMongoClient(),getDatabaseName());
    }
}
