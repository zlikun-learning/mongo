package com.zlikun.learning;

import com.mongodb.WriteResult;
import com.zlikun.learning.domain.UserInfo;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * 示例演示了Xml配置和Annotation两种配置方法
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 13:39
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration("classpath:beans.xml")
@ContextConfiguration(classes = AppConfigure.class)
public class MongoTemplateTest {

    @Autowired
    MongoTemplate template ;

    @After
    public void destroy() {
        template.dropCollection(UserInfo.class);
    }

    @Test
    public void test() {

        insert();

        update();

        find();

        findOne();

        remove();

    }

    public void insert() {
        // @Id 注解将指定`_id`字段，否则将自动生成
        UserInfo info = new UserInfo() ;
        info.setUserId(1L);
        info.setUsername("zlikun");
        info.setPassword("123456");
        info.setCtime(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));   // 题外问题，怎么一直显示的是格林威治时间
//        template.insert(info ,"users");
        template.insert(info);
    }

    public void find() {
        Optional<UserInfo> optional = template.find(new BasicQuery("{\"username\":\"zlikun\"}") ,
                    UserInfo.class ,
                    "users")
                .stream()
                .findFirst() ;
        assertNotNull(optional);
        assertTrue(optional.isPresent());
        UserInfo info = optional.get() ;

        assertNotNull(info);
        assertEquals("123456" ,info.getPassword());
    }

    public void findOne() {
        UserInfo info = template.findOne(new BasicQuery("{\"username\":\"zlikun\"}") ,
                UserInfo.class ,
                "users") ;
        assertNotNull(info);
        assertEquals("123456" ,info.getPassword());
    }

    public void update() {
        WriteResult result = template.updateFirst(new Query(where("username").is("zlikun")) ,
                new Update().set("gender" ,"MALE") ,
                UserInfo.class) ;
        assertNotNull(result) ;
    }

    public void remove() {
        WriteResult result = template.remove(new Query(where("username").is("zlikun")) ,UserInfo.class) ;
        assertNotNull(result) ;
    }

}
