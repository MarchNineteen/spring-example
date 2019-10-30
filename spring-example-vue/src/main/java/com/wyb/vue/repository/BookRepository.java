package com.wyb.vue.repository;


import com.wyb.vue.entity.Book;

import java.util.List;

public interface BookRepository {
    public List<Book> find(Integer index, Integer limit);

    public Integer count();

    public Integer save(Book book);

    public Integer deleteById(Integer id);

    public Book findById(Integer id);

    public Integer update(Book book);
}
