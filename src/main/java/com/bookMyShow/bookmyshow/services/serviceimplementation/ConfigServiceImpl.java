package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.dto.ConfigDto;
import com.bookMyShow.bookmyshow.entity.Config;
import com.bookMyShow.bookmyshow.services.ConfigService_book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.bookMyShow.bookmyshow.Utilities.makeConfig;


@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService_book {

    @Qualifier("newdb1MongoTemplate")
    private final MongoTemplate newdb1MongoTemplate;

    @Override
    public List<Config> getByDbType(String dbType) {
        Criteria criteria = Criteria.where("dbType").is(dbType);
        Query query = new Query(criteria);
        return newdb1MongoTemplate.find(query,Config.class);
    }

    @Override
    public Config getByDbTypeAndHashId(String dbType,String hashId) {
        Criteria criteria = Criteria.where("id").is(dbType+"_"+hashId);
        Query query = new Query(criteria);

        return newdb1MongoTemplate.find(query,Config.class).get(0);
    }

    @Override
    public Config setConfig(ConfigDto configDto) {
        return newdb1MongoTemplate.save(makeConfig(configDto));
    }

}
