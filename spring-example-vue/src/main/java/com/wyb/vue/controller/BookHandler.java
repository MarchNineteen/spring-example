package com.wyb.vue.controller;

import com.wyb.vue.entity.Book;
import com.wyb.vue.entity.BookVO;
import com.wyb.vue.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookHandler {

    @Autowired
    private BookService bookService;

    @GetMapping("/findByPage/{page}")
    public BookVO findByPage(@PathVariable("page") Integer page){
        return bookService.findByPage(page);
    }

    @PostMapping("/save")
    public Integer save(@RequestBody Book book){
        return bookService.save(book);
    }

    @DeleteMapping("/deleteById/{id}")
    public Integer deleteById(@PathVariable("id") Integer id){
        return bookService.deleteById(id);
    }

    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable("id") Integer id){
        return bookService.findById(id);
    }

    @PutMapping("/update")
    public Integer update(@RequestBody Book book){
        return bookService.update(book);
    }
}
