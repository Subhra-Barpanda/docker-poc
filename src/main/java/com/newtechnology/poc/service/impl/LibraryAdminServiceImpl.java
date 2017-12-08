package com.newtechnology.poc.service.impl;

import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.User;
import com.newtechnology.poc.model.constants.user.UserRole;
import com.newtechnology.poc.repository.BookRepository;
import com.newtechnology.poc.repository.UserRepository;
import com.newtechnology.poc.service.LibraryAdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class LibraryAdminServiceImpl implements LibraryAdminServiceInterface {

    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public LibraryAdminServiceImpl(BookRepository bookRepository,UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    @Override
    public Set<Book> addBookCollection(List<Book> books){
        Set<Book> addedBookSet = new HashSet<>();
        books.forEach((book) -> {
            Book savedBook = addBook(book);
            addedBookSet.add(savedBook);
        });
        return addedBookSet;
    }

    @Override
    public User addUserToLibrary(User user){
        return userRepository.save(user);
    }

    @Override
    public Set<User> addUsersListToLibrary(List<User> users){
        Set<User> addedUserSet = new HashSet<>();
        users.forEach((user) -> {
            User savedUser = addUserToLibrary(user);
            addedUserSet.add(savedUser);
        });
        return addedUserSet;
    }

    @Override
    public Set<User> getListOfDefaultedUsers(){
        Set<User> allStudents = getListOfAllUsers(UserRole.STUDENT.getValue());
        final Set<User> allStudentsWithIssuedBooks = allStudents.stream()
                                                                .filter((student) -> student.getCountOfBooksIssued() > 0)
                                                                .collect(Collectors.toSet());

        final Set<User> allDefaultedStudents = allStudentsWithIssuedBooks.stream().filter((student) -> {
            final Set<Book> defaultedBooks = student.getBookList().stream().filter((book) -> {
                final LocalDate todaysDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                final LocalDate maxmAllowedDateForIssuance = book.getIssuedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(2);
                if (book.getReturnedDate() == null && todaysDate.isAfter(maxmAllowedDateForIssuance)) {
                    return true;
                }
                return false;
            }).collect(Collectors.toSet());
            if (defaultedBooks.isEmpty())
                return false;
            return true;

        }).collect(Collectors.toSet());

        return allDefaultedStudents;
    }

    @Override
    public Set<Book> getListOfBooksNeverIssued(){

        final List<Book> allBookList = bookRepository.findAll();
        final Set<Book> setOfBooksNeverIssued = allBookList.stream()
                                                           .filter((book) -> book.getIssuedDate() == null)
                                                           .collect(Collectors.toSet());
        return setOfBooksNeverIssued;
    }

    @Override
    public Set<User> getListOfAllUsers(String userRole){
        return userRepository.findByRole(UserRole.valueOf(userRole.toUpperCase()));
    }
}
