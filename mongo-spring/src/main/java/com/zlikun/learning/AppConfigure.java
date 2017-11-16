package com.zlikun.learning;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 13:27
 */
@Configuration
@EnableMongoRepositories
public class AppConfigure {

    final String host = "192.168.120.74" ;
    final int port = 27017 ;
    final String database = "users" ;

//    /**
//     * 配置意义与 #mongoClient() 等同
//     * @return
//     */
//    @Bean
//    public MongoClientFactoryBean mongo() {
//        MongoClientFactoryBean mongo = new MongoClientFactoryBean() ;
//        mongo.setHost(host);
//        mongo.setPort(port);
//        return mongo ;
//    }

    @Bean
    public MongoClient mongoClient() {

        return new MongoClient(new ServerAddress(host, port) ,new MongoClientOptions.Builder()
                .maxConnectionIdleTime(1500)
                .maxWaitTime(1500)
                .build()) ;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongo) {
        return new SimpleMongoDbFactory(mongo,"users") ;
    }

    @Bean
    public MongoTemplate mongoTemplate(Mongo mongo) {
        MongoTemplate template = new MongoTemplate(mongo, "users");
        return template ;
    }

}
