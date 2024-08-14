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
    Integer bookId ;
    String bookName ;
    Double price ;
    String author ;
    String description ;

}
