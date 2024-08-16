package com.demo.curd.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookDto {

    @NotNull(message="Book id can not be null")
    private Integer bookId;
    private String bookName;

    @Min(value = 1, message = "price should not be negative")
    private Double price;
    private String author;

    @Size(min = 5 , max = 200 , message = "Description must be between 5 to 200 characters")
    private String description;

}
