package com.zlikun.learning.dao;

import com.zlikun.learning.domain.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 16:28
 */
@Repository
public interface UserRepository<U, L extends Number> extends MongoRepository<UserInfo ,Long> {



}
