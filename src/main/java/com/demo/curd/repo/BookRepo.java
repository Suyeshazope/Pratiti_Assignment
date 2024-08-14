package com.demo.curd.repo;

import com.demo.curd.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:title%")
    List<Book> findByTitle(@Param("title") String title);
}
