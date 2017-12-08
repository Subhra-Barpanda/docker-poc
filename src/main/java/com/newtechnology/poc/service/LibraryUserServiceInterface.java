package com.newtechnology.poc.service;

import com.newtechnology.poc.exception.ApplicationRunTimeException;
import com.newtechnology.poc.model.Book;

import java.util.List;
import java.util.Set;

public interface LibraryUserServiceInterface {
    List<Book> listAllBooks();

    Set<Book> listAllBooksByBookName(String bookName);

    Set<Book> listAllBooksByAuthorName(String authorName);

    Set<Book> listAllAvailableBooksBySubCategory(String subCategory);

    Set<Book> listBooksIssuedToUser(String rollNumber);

    Book issueBook(long bookId, long studentId) throws ApplicationRunTimeException;

    Book releaseBook(long bookId, long studentId);
}
