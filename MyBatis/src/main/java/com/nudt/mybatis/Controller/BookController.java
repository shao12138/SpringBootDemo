package com.nudt.mybatis.Controller;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.nudt.mybatis.Mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookMapper bookMapper;

    @GetMapping("/addBook")
    public void addBook() {

        try {
            bookMapper.addBook("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        } catch (DataIntegrityViolationException e) {
            System.out.println(e);
        }
    }
}
