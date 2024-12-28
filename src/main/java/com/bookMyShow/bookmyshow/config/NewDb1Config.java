package com.bookMyShow.bookmyshow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.bookMyShow.bookmyshow.repository"
        },
        mongoTemplateRef = NewDb1Config.MONGO_TEMPLATE
)
public class NewDb1Config {
    protected static final String MONGO_TEMPLATE = "newdb1MongoTemplate";
}
