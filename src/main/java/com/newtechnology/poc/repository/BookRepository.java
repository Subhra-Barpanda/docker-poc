package com.newtechnology.poc.repository;

import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.User;
import com.newtechnology.poc.model.constants.book.BookAvailability;
import com.newtechnology.poc.model.constants.book.BookCategory;
import com.newtechnology.poc.model.constants.book.BookSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findByName(@Param("bookName") String bookName);
    Set<Book> findByAuthor(@Param("bookAuthor")String bookAuthor);
    Set<Book> findByAvailability(@Param("bookAvailability")BookAvailability bookAvailability);
    Set<Book> findByCategory(@Param("bookCategory")BookCategory bookCategory);
    Set<Book> findBySubCategory(@Param("bookSubCategory")BookSubCategory bookSubCategory);
    Set<Book> findByUser(@Param("user")User user);
    Set<Book> findBySubCategoryAndAvailability(@Param("bookSubCategory")BookSubCategory bookSubCategory,@Param("bookAvailability")BookAvailability bookAvailability);
}
