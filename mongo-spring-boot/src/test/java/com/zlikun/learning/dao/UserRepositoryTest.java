package com.zlikun.learning.dao;

import com.zlikun.learning.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 16:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository<UserInfo ,Long> userRepository ;

    @Test
    public void test() {
        UserInfo info = new UserInfo() ;
        info.setUserId(1L);
        info.setUsername("zlikun");
        info.setPassword("123456");

        info = userRepository.insert(info) ;
        assertNotNull(info) ;
        assertEquals("123456" ,info.getPassword());

        long elements = userRepository.findAll(new PageRequest(1 ,2)).getTotalElements() ;
        assertEquals(1L ,elements);

        userRepository.delete(1L);
    }

}
