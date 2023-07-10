package com.nudt.elasticsearch.Controller;

import com.nudt.elasticsearch.Entity.Book;
import com.nudt.elasticsearch.Repository.BookRepository;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class PageAble {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/pageList")
    public void fetchPageCustomers() {
        Pageable pageable = PageRequest.of(1, 3, Sort.Direction.DESC, "price");
        Page<Book> book = bookRepository.findByBookName("西游记", pageable);
        for (Book page : book) {
            System.out.println(page.toString());
        }
    }

    @RequestMapping("/pageList2")
    public void fetchPage2Customers() {
        QueryBuilder customerQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("bookName", "西游记"));
        Page<Book> book = bookRepository.search(customerQuery, PageRequest.of(0, 3));
        for (Book page : book) {
            System.out.println(page.toString());
        }
    }

    @RequestMapping("/singleFind")
    public void singleFind() {
        //不分词查询 参数1： 字段名，参数2：字段查询值，因为不分词，所以汉字只能查询一个字，英语是一个单词
        QueryBuilder queryBuilder = QueryBuilders.termQuery("bookName", "西游记");
        Page<Book> book = bookRepository.search(queryBuilder, PageRequest.of(0, 3));
        for (Book page : book) {
            System.out.println(page.toString());
        }
        //分词查询，采用默认的分词器
        QueryBuilder queryBuilder2 = QueryBuilders.matchQuery("bookName", "西游记");
        Page<Book> book2 = bookRepository.search(queryBuilder2, PageRequest.of(0, 3));
        for (Book page : book2) {
            System.out.println(page.toString());
        }
    }
}
