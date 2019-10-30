package com.wyb.vue.repository;


import com.wyb.vue.entity.BookCase;

public interface BookCaseRepository {
    public BookCase findById(Integer id);
}
