package com.wyb.vue.entity;

import lombok.Data;

import java.util.List;

@Data
public class BookVO {
    private Integer total;
    private List<Book> data;
    private Integer pageSize;
}
