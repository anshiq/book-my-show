package com.bookMyShow.bookmyshow.services.serviceimplementation;

import com.bookMyShow.bookmyshow.config.MongoTemplateUsingDbProps;
import com.bookMyShow.bookmyshow.dataloader.DataLoader;
import com.bookMyShow.bookmyshow.dataloader.DelegatingMap;
import com.bookMyShow.bookmyshow.dto.UserDto;
import com.bookMyShow.bookmyshow.entity.User;
import com.bookMyShow.bookmyshow.exceptions.ErrorResults;
import com.bookMyShow.bookmyshow.exceptions.PostExceptions;
import com.bookMyShow.bookmyshow.repository.UserRepositoryDb1;
import com.bookMyShow.bookmyshow.repository.UserRepositoryDb2;
import com.bookMyShow.bookmyshow.services.UserService;
import com.example.configclient.Config;
import com.example.configclient.ConfigClient;
import com.example.configclient.ConfigService;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.bookMyShow.bookmyshow.Utilities.makeUser;
import static com.bookMyShow.bookmyshow.userfilter.UserContext.getUserThreadId;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    @Qualifier("newdb1MongoTemplate")
    private final MongoTemplate newdb1MongoTemplate;

    @Qualifier("newdb2MongoTemplate")
    private final MongoTemplate newdb2MongoTemplate;


    //    private final com.google.common.cache.LoadingCache<String,Config> cache;
//    private final ConfigClient configClient;
//
//
    private final MongoTemplateUsingDbProps mongoTemplateUsingDbProps;

    private final ConfigClient configClient;

    private final UserRepositoryDb1 userRepositoryDb1;
    private final UserRepositoryDb2 userRepositoryDb2;

//    @Qualifier("newdb1MongoTemplate")
//    private final MongoTemplate newdb1MongoTemplate;
//
//    @Qualifier("newdb2MongoTemplate")
//    private final MongoTemplate newdb2MongoTemplate;
//
//    private final ConfigClient configClient;
//    private final LoadingCache<String, com.example.configclient.Config> cache;
//    private final ConfigService configService;
//    private final UserRepositoryDb1 userRepositoryDb1;
//    private final UserRepositoryDb2 userRepositoryDb2;
//
//    // Correct constructor
//    public UserServiceImpl(
//            @Qualifier("newdb1MongoTemplate") MongoTemplate newdb1MongoTemplate,
//            @Qualifier("newdb2MongoTemplate") MongoTemplate newdb2MongoTemplate,
//            ConfigClient configClient,
//            LoadingCache<String, Config> cache,
//            ConfigService configService,
//            UserRepositoryDb1 userRepositoryDb1,
//            UserRepositoryDb2 userRepositoryDb2) {
//        this.newdb1MongoTemplate = newdb1MongoTemplate;
//        this.newdb2MongoTemplate = newdb2MongoTemplate;
//        this.configClient = configClient;
//        this.cache = cache;
//        this.configService = new ConfigService(cache,this.configClient);
//        this.userRepositoryDb1 = userRepositoryDb1;
//        this.userRepositoryDb2 = userRepositoryDb2;
//    }

    //    public User addUser(UserDto userDto) {
//
//        boolean bool = ifUserExists(userDto.getEmail(), userDto.getPhoneNumber());
//        if (bool) {
//            throw new PostExceptions(ErrorResults.USER_ALREADY_EXISTS);
//        }
//        User user = makeUser(userDto);
//
//        String userId=user.getId();
//        int userIdCharSum=0;
//        for(int i=0;i<userId.length();i++){
//            userIdCharSum+=userId.charAt(i);
//        }
//
//        System.out.println(userIdCharSum);
//        System.out.println(userIdCharSum%2);
//
//        if(userIdCharSum%2==0)return newdb1MongoTemplate.save(user);
//        else return newdb2MongoTemplate.save(user);
//
//    }
    public User addUser(UserDto userDto) throws ExecutionException {

        boolean bool = ifUserExists(userDto.getEmail(), userDto.getPhoneNumber());
        if (bool) {
            throw new PostExceptions(ErrorResults.USER_ALREADY_EXISTS);
        }
        User user = makeUser(userDto);

        String userId = user.getId();
        int userIdCharSum = 0;
        for (int i = 0; i < userId.length(); i++) {
            userIdCharSum += userId.charAt(i);
        }
        System.out.println(userIdCharSum);

        String hashId = "test";
        if (userIdCharSum % 2 != 0) {
            hashId = "test2";
        }
        Config config = configClient.getByDbTypeAndHashId("mongo", hashId);

        MongoTemplate mongoTemplate = mongoTemplateUsingDbProps.mongoTemplate(config.getDbName(), config.getHashId());

        return mongoTemplate.save(user);
    }


    public boolean ifUserExists(String email, String phoneNumber) {
        User user = userRepositoryDb1.ifUserExists(email, phoneNumber);
        User user2 = userRepositoryDb2.ifUserExists(email, phoneNumber);
        return user != null || user2 != null;
    }

    public User findUser() {
        DataLoader<String, User> dataLoader = k -> userRepositoryDb1.findById(k).get();

        String id = getUserThreadId();

        Map<String, User> mp = new HashMap<>();

        DelegatingMap<String, User> dmp = new DelegatingMap<>(dataLoader, mp);
        return dmp.find(id);
    }

}
