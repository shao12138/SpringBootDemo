package com.nudt.elasticsearch.Controller;

import com.nudt.elasticsearch.Entity.Book;
import com.nudt.elasticsearch.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/save")
    public String save() {
        for (int i = 1; i < 11; i++) {
            Book book = new Book(i, "西游记", "吴承恩", new Random().nextFloat() * 100, 100, "novel");
            bookRepository.save(book);
        }
        return "success";
    }

    @RequestMapping("/findAll")
    public void findAll() {
        for (Book books : bookRepository.findAll()) {
            System.out.println(books.toString());
        }
    }

    @RequestMapping("/update")
    public void update() {
        Book book = bookRepository.findById(1).orElse(null);
        book.setAuthor("明朝：吴承恩");
        bookRepository.save(book);
    }

    @RequestMapping("/delete")
    public void deleteById() {
        bookRepository.deleteById(1);
    }

    @RequestMapping("/deleteAll")
    public void deleteAll() {
        for (Book books : bookRepository.findAll()) {
            bookRepository.deleteById(books.getId());
        }
    }
}