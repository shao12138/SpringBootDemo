package com.nudt.elasticsearch.Controller;

import com.alibaba.fastjson.JSON;
import com.nudt.elasticsearch.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EsRestTemplate {
    private static final String Book_INDEX = "book";
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @RequestMapping("/index")
    public void index() {
        Book book = new Book();
        book.setId(101);
        book.setBookName("雪中悍刀行");
        book.setAuthor("烽火戏诸侯");
        book.setCategory("网络小说");
        book.setPrice(300);
        book.setPage(5000);

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(book.getId() + "")
                .withObject(book).build();

        String documentId = elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of(Book_INDEX));

        System.out.println(documentId);
    }

    @RequestMapping("/bulkIndex")
    public void bulkIndex() {
        Book book = new Book();
        book.setId(10);
        book.setBookName("盗墓笔记");
        book.setAuthor("南派三叔");
        book.setCategory("网络小说");
        book.setPrice(200);
        book.setPage(4000);

        List<Book> books = new ArrayList<>();
        books.add(book);

        List<IndexQuery> queries = books.stream()
                .map(book1 -> new IndexQueryBuilder().withId(book1.getId()+"").withObject(book1).build())
                .collect(Collectors.toList());
        List<String> documentIds = elasticsearchRestTemplate.bulkIndex(queries, IndexCoordinates.of(Book_INDEX));
        for (String documentId : documentIds) {
            System.out.println(documentId);
        }
    }
    @RequestMapping("/update")
    public void update() {
        Book book = new Book();
        book.setId(10);
        book.setBookName("盗墓笔记");
        book.setAuthor("南派三叔");
        book.setCategory("网络小说");
        book.setPrice(300);
        book.setPage(4000);

        // 构造updateQuery
        UpdateQuery updateQuery = UpdateQuery.builder("10")
                // 如果不存在就新增，默认为false
                .withDocAsUpsert(true)
                .withDocument(Document.parse(JSON.toJSONString(book)))
                .build();
        UpdateResponse response = elasticsearchRestTemplate.update(updateQuery, IndexCoordinates.of(Book_INDEX));

        System.out.println(JSON.toJSONString(response));
    }
}