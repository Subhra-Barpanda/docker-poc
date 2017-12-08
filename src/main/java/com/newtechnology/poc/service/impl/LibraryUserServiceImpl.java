package com.newtechnology.poc.service.impl;

import com.newtechnology.poc.exception.ApplicationRunTimeException;
import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.User;
import com.newtechnology.poc.model.constants.book.BookAvailability;
import com.newtechnology.poc.model.constants.book.BookSubCategory;
import com.newtechnology.poc.repository.BookRepository;
import com.newtechnology.poc.repository.UserRepository;
import com.newtechnology.poc.service.LibraryUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class LibraryUserServiceImpl implements LibraryUserServiceInterface {

    private BookRepository bookRepository;
    private UserRepository userRepository;

    @Autowired
    public LibraryUserServiceImpl(BookRepository bookRepository, UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Book> listAllBooks(){

        return bookRepository.findAll();
    }

    @Override
    public Set<Book> listAllBooksByBookName(String bookName){
        return bookRepository.findByName(bookName);
    }

    @Override
    public Set<Book> listAllBooksByAuthorName(String authorName){

        return bookRepository.findByAuthor(authorName);
    }

    @Override
    public Set<Book> listAllAvailableBooksBySubCategory(String subCategory){
        return bookRepository.findBySubCategoryAndAvailability(BookSubCategory.valueOf(subCategory.toUpperCase()), BookAvailability.Y);
    }

    @Override
    public Set<Book> listBooksIssuedToUser(String rollNumber){

        User user = userRepository.findByRollNumber(rollNumber);
        return user.getBookList();
    }

    @Override
    public Book issueBook(long bookId, long studentId) throws ApplicationRunTimeException{

        User user = userRepository.findOne(studentId);
        Book book = bookRepository.findOne(bookId);
        //TODO : Move the hardcoded digit 4 to properties file
        if("Y".equalsIgnoreCase(book.getAvailability().toString()) && user.getCountOfBooksIssued() < 4){
            book.setUser(user);
            book.setAvailability(BookAvailability.N);
            book.setIssuedDate(Calendar.getInstance().getTime());
            user.setCountOfBooksIssued(user.getCountOfBooksIssued()+1);
            Set<Book> alreadyIssuedbookList = user.getBookList();
            alreadyIssuedbookList.add(book);
            user.setBookList(alreadyIssuedbookList);
            userRepository.save(user);
        }else{
            //TODO : Throw Separate Exceptions for each of the above scenarios
            throw new ApplicationRunTimeException("1001",
                                                  "Book Not Available/User exceeded his/her issuing limit");
        }

        return bookRepository.save(book);
    }

    @Override
    public Book releaseBook(long bookId, long studentId) throws ApplicationRunTimeException{

        User user = userRepository.findOne(studentId);
        Book book = bookRepository.findOne(bookId);
        if(book.getUser() != null && book.getUser().getId() == studentId){
            book.setAvailability(BookAvailability.Y);
            book.setReturnedDate(Calendar.getInstance().getTime());
            book.setUser(null);
            user.setCountOfBooksIssued(user.getCountOfBooksIssued()-1);
            Set<Book> alreadyIssuedBookList = user.getBookList();
            alreadyIssuedBookList.remove(book);
            user.setBookList(alreadyIssuedBookList);
            userRepository.save(user);

            return bookRepository.save(book);
        }

        throw new ApplicationRunTimeException("1002",
                "Book is not issued to the user");
    }
}
