package com.zlikun.learning.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 13:22
 */
@Data
@Document(collection = "users")
public class UserInfo {

    @Id
    private Long userId ;
    private String username ;
    private String password ;
    private LocalDateTime ctime ;

}
