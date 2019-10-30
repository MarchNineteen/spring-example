package com.wyb.vue.entity;

import lombok.Data;

@Data
public class Book {
	private int id;
	private String name;
	private String author;
	private String publish;
	private int pages;
	private double price;
	private BookCase bookCase;
}
