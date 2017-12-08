package com.newtechnology.poc.service;

import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.User;

import java.util.List;
import java.util.Set;

public interface LibraryAdminServiceInterface {
    Book addBook(Book book);

    Set<Book> addBookCollection(List<Book> books);

    User addUserToLibrary(User user);

    Set<User> addUsersListToLibrary(List<User> users);

    Set<User> getListOfDefaultedUsers();

    Set<Book> getListOfBooksNeverIssued();

    Set<User> getListOfAllUsers(String userRole);
}
