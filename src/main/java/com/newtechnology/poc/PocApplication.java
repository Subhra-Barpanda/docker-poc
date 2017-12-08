package com.newtechnology.poc;

import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.User;
import com.newtechnology.poc.model.constants.book.BookAvailability;
import com.newtechnology.poc.model.constants.book.BookCategory;
import com.newtechnology.poc.model.constants.book.BookSubCategory;
import com.newtechnology.poc.model.constants.user.UserRole;
import com.newtechnology.poc.repository.BookRepository;
import com.newtechnology.poc.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}

	private Set<Book> seededBookSet;
	private Set<User> seededUserSet;

	private void populateSeededData(){

	    seededBookSet = new HashSet<>();
	    seededUserSet = new HashSet<>();

        Book book = new Book("Head First Java",450,"Kathy Siera", BookAvailability.Y, BookCategory.TECHNICAL, BookSubCategory.JAVA);
        seededBookSet.add(book);
        book = new Book("Thinking in Java",800,"Bruce Eckel",BookAvailability.Y, BookCategory.TECHNICAL, BookSubCategory.JAVA);
        seededBookSet.add(book);
        book = new Book("Head First SQL",390,"Unknown",BookAvailability.Y, BookCategory.TECHNICAL, BookSubCategory.SQL);
        seededBookSet.add(book);
        book = new Book("Autocar India",150,"Autocar",BookAvailability.Y, BookCategory.LEISURE, BookSubCategory.AUTO);
        seededBookSet.add(book);
        book = new Book("Game of Thrones",1000,"Forgot His Name",BookAvailability.Y, BookCategory.LEISURE, BookSubCategory.BRITISH);
        seededBookSet.add(book);

        User user = new User("Ram","2010-CS-A9", UserRole.STUDENT,0);
        seededUserSet.add(user);
        user = new User("Shyam","2012-CS-B45", UserRole.STUDENT,0);
        seededUserSet.add(user);
        user = new User("Hari","N/A", UserRole.ADMIN,0);
        seededUserSet.add(user);

    }

	@Bean
    CommandLineRunner initialize(UserRepository userRepository,BookRepository bookRepository){
	    return (args) -> {
	        populateSeededData();
            seededUserSet.forEach((user) -> userRepository.save(user));
            userRepository.findAll().forEach(System.out::println);
            seededBookSet.forEach((book) -> bookRepository.save(book));
            bookRepository.findAll().forEach(System.out::println);
        };
    }
}
