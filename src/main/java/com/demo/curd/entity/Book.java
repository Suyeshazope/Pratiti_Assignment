package com.demo.curd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Book {

    @Id
    private Integer bookId ;
    private String bookName ;
    private Double price ;
    private String author ;
    private String description ;

}
