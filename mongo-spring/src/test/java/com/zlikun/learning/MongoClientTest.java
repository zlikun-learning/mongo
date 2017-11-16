package com.zlikun.learning;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * 使用原生MongoClient实现CURD操作
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-16 15:44
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfigure.class)
public class MongoClientTest {

    @Autowired
    MongoClient client ;

    MongoCollection<Document> collection;

    @Before
    public void init() {
        MongoDatabase database = client.getDatabase("users") ;
        collection = database.getCollection("articles") ;
    }

    @Test
    public void test() {

        Document doc = new Document();
        doc.append("title", "Mongo 学习笔记");
        doc.append("content", "Hello Mongo !");
        doc.append("ctime", new Date());
        doc.append("tags", Arrays.asList("nosql", "mongo", "json"));
        collection.insertOne(doc);

        Document returnDoc = collection.find().limit(1).first() ;
        assertNotNull(returnDoc) ;
        assertThat("Hello Mongo !" ,is(returnDoc.get("content")));

        collection.drop();

    }

}
