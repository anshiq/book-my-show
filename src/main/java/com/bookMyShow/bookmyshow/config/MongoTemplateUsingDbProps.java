package com.bookMyShow.bookmyshow.config;


import com.example.configclient.Config;
import com.example.configclient.ConfigService;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class MongoTemplateUsingDbProps {

    private final ConfigService configService;

    public MongoClient mongoClient(String dbType,String hashId) throws ExecutionException {
        Config config = configService.getConfigById(dbType,hashId);
        String connectionString = String.format("%s://%s",
                config.getDbType(),
                config.getHost());

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
        return MongoClients.create(settings);
    }


    public MongoTemplate mongoTemplate(String dbType,String hashId) throws ExecutionException {
        return new MongoTemplate(mongoClient(dbType,hashId), hashId);
    }
}


