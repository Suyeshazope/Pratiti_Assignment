package com.demo.curd.controller;

import com.demo.curd.dto.BookDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.demo.curd.service.BookService;

import java.util.List;

@RestController
//@Validated
@RequestMapping("/library")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping("getbooks")
    public ResponseEntity<List<BookDto>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size) {
        List<BookDto> books = bookService.getAllBooks(page , size);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("getbook/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
            BookDto book = bookService.getBookById(id) ;
            return ResponseEntity.ok(book) ;
    }

    @PostMapping("save")
    public ResponseEntity<String> saveBook(@RequestBody @Valid List<BookDto> books , BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("validation failed") ;
        }

            bookService.addBook(books);
        return ResponseEntity.ok("book saved") ;
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateBook(@PathVariable @Valid int id , @RequestBody BookDto book , BindingResult bindingResult) {
        bookService.updateBook(id , book);

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("validation Failed") ;
        }

        return ResponseEntity.ok("Book updated successfully") ;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book Deleted Successfully") ;
    }

    @GetMapping("getbookbytitle/{title}")
    public ResponseEntity<List<BookDto>> getBookByTitle(@PathVariable String title) {
        List<BookDto> book = bookService.getBookByTitle(title) ;
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
