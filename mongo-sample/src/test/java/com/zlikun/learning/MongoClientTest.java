package com.zlikun.learning;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * MongoClient 基本用法测试
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017-11-15 17:23
 */
@Slf4j
public class MongoClientTest {

    private String host = "192.168.120.74" ;
    private int port = 27017 ;

    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    @Before
    public void init() {
        client = new MongoClient(host, port);
        database = client.getDatabase("books");
        collection = database.getCollection("spring");
    }

    /**
     * 单条插入
     */
    @Test
    public void insertOne() {
        Document doc = new Document();
        doc.append("title", "spring framework manual");
        doc.append("content", "hello spring !");
        doc.append("ctime", new Date());
        doc.append("price", 37.5);
        doc.append("tags", Arrays.asList("spring", "manual", "javaee"));
        doc.append("category", new Document().append("language", "java").append("feature", "server endpoint"));
        collection.insertOne(doc);
    }

    /**
     * 多条插入
     */
    @Test
    public void insertMany() {
        List<Document> list = new ArrayList<Document>();
        for (int i = 0; i < 10; i++) {
            list.add(new Document("name", "name-" + i));
        }
        collection.insertMany(list);
    }

    /**
     * 查询总数
     */
    @Test
    public void count() {
        log.info("count = {}", collection.count());
    }

    @Test
    public void find() {
        FindIterable<Document> iter = collection.find(Filters.eq("name", "name-3"));
        List<Document> list = iter.into(new ArrayList<>());
        assertEquals(list.get(0), iter.first());
        log.info("查询到的第一条记录：{}", iter.first().toJson());

//		iter = collection.find() ;							// 无过滤，查询全部
//		iter = collection.find().limit(10).skip(0) ;		// 分页查询[0 ,10)
//		collection.findOneAndDelete(filter)				    // 查询并删除
//		collection.findOneAndReplace(filter, replacement)	// 查询并替换结果
//		collection.findOneAndUpdate(filter, update)		    // 查询并更新

    }

    @Test
    public void updateOne() {
        collection.updateOne(Filters.eq("_id", new ObjectId("57907e179903f42a20993463")), new Document().append("$set", new Document("age", "18")));
        collection.updateOne(Filters.eq("name", "name-3"), new Document("$set", new Document("joinTime", new Date())));
        Document doc = collection.findOneAndUpdate(Filters.eq("name", "name-3"), new Document("$set", new Document("weight", 60)));
        log.info("doc = {}", doc.toJson());
    }

    @Test
    public void deleteOne() {
        DeleteResult dr = collection.deleteOne(Filters.eq("_id", new ObjectId("57907f9f9903f42b6c095c85")));
        log.info("deleted count : {}", dr.getDeletedCount());
    }

    @After
    public void destroy() {
        if (client != null) client.close();
    }

}
