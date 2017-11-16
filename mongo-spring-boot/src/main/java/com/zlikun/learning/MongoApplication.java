package com.zlikun.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 15:58
 */
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.zlikun.learning.dao")
public class MongoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MongoApplication.class, args);
    }

}
