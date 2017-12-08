package com.newtechnology.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newtechnology.poc.model.constants.user.UserRole;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement
@Entity
@Table(name="T_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    //@JsonIgnore
    private long id;

    @Column(name = "USER_NAME")
    private String name;

    @Column(name = "USER_ROLL_NUM",unique = true)
    private String rollNumber;

    @Column(name = "USER_ROLE")
    private UserRole role;

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private Set<Book> bookList;

    @Column(name = "NO_OF_BOOKS_ISSUED")
    private int countOfBooksIssued;

    public User() {
    }

    public User(String name, String rollNumber, UserRole role, int countOfBooksIssued) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.role = role;
        this.countOfBooksIssued = countOfBooksIssued;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Set<Book> getBookList() {
        return bookList;
    }

    public void setBookList(Set<Book> bookList) {
        this.bookList = bookList;
    }

    public int getCountOfBooksIssued() {
        return countOfBooksIssued;
    }

    public void setCountOfBooksIssued(int countOfBooksIssued) {
        this.countOfBooksIssued = countOfBooksIssued;
    }


}
