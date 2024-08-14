package com.demo.curd.dao;

import com.demo.curd.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page ;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

import com.demo.curd.entity.Book;

@Component
@RequiredArgsConstructor
public class BookDao {
    private final BookRepo bookRepo ;

    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    public Optional<Book> getBookById(Integer bookId) {
        return bookRepo.findById(bookId) ;
    }

    public void saveBook(Book book) {
        bookRepo.save(book) ;
    }

    public void deleteBook(Integer bookId) {
       bookRepo.deleteById(bookId);
    }

    public List<Book> getBookByTitle(String title){
        return bookRepo.findByTitle(title) ;
    }

    public boolean existsById(Integer bookId) {
        return bookRepo.existsById(bookId) ;
    }
}
