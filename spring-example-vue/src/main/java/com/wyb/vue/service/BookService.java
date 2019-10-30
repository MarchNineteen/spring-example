package com.wyb.vue.service;


import com.wyb.vue.entity.Book;
import com.wyb.vue.entity.BookVO;

public interface BookService {
    public BookVO findByPage(Integer page);

    public Integer save(Book book);

    public Integer deleteById(Integer id);

    public Book findById(Integer id);

    public Integer update(Book book);
}
