package com.demo.curd.service;
import com.demo.curd.dao.BookDao;
import com.demo.curd.dto.BookDto;
import com.demo.curd.entity.Book;
import com.demo.curd.exception.BookAlreadyExistException;
import com.demo.curd.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page ;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookDao bookDao;

    private final ModelMapper modelMapper;

    public List<BookDto> getAllBooks(int page , int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> books = bookDao.getAllBooks(pageable);
        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }

    public BookDto getBookById(Integer bookId) {

        Optional<Book> book = bookDao.getBookById(bookId) ;

       if(book.isEmpty()){
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }

        return modelMapper.map(book, BookDto.class);
    }

    public void addBook(List<BookDto> bookDtos) {
        for(BookDto bookDto : bookDtos){
            Book book = modelMapper.map(bookDto, Book.class);

            if(bookDao.existsById(book.getBookId())){
                throw new BookAlreadyExistException("Book already exists with this Id : " + book.getBookId()) ;
            }
            bookDao.saveBook(book);
        }
    }

    public void updateBook(Integer bookId , BookDto book) {
        Optional<Book> book1 = bookDao.getBookById(bookId) ;

        if(book1.isEmpty()){
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }

        if(book1.isPresent()){
            Book existingBook = book1.get() ;
            modelMapper.map(book , existingBook);
            bookDao.saveBook(existingBook);
        }
    }

    public void deleteBook(Integer bookId) {
        Optional<Book> book1 = bookDao.getBookById(bookId) ;

        if(book1.isEmpty()){
            throw new BookNotFoundException("Book not found with ID: " + bookId);
        }
        bookDao.deleteBook(bookId);
    }

    public List<BookDto> getBookByTitle(String title){
        List<Book> books = bookDao.getBookByTitle(title);

        if(books == null){
            throw new BookNotFoundException("Book not found with title : " + title);
        }

        return books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
    }
}
