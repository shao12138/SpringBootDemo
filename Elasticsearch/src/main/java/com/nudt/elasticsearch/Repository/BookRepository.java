package com.nudt.elasticsearch.Repository;

import com.nudt.elasticsearch.Entity.Book;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import javax.annotation.Resource;

@Resource
public interface BookRepository extends ElasticsearchRepository<Book, Integer> {
    Page<Book> findByBookName(String name, Pageable pageable);
}