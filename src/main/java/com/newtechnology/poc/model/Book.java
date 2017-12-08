package com.newtechnology.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.newtechnology.poc.model.constants.book.BookAvailability;
import com.newtechnology.poc.model.constants.book.BookCategory;
import com.newtechnology.poc.model.constants.book.BookSubCategory;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "T_BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID",nullable = false)
    //@JsonIgnore
    private long id;

    @Column(name = "BOOK_NAME",nullable = false)
    private String name;

    @Column(name = "BOOK_PRICE",nullable = false)
    private int price;

    @Column(name = "BOOK_AUTHOR",nullable = false)
    private String author;

    @Column(name = "BOOK_AVAILABLE",nullable = false,length = 1)
    private BookAvailability availability;

    @Column(name = "BOOK_CATEGORY",nullable = false)
    private BookCategory category;

    @Column(name = "BOOK_SUB_CATEGORY",nullable = false)
    private BookSubCategory subCategory;

    @Column(name = "BOOK_ISSUED_DATE")
    @Temporal(TemporalType.DATE)
    private Date issuedDate;

    @Column(name = "BOOK_RETURNED_DATE")
    @Temporal(TemporalType.DATE)
    private Date returnedDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="id")
    private User user;


    public Book() {
    }

    public Book(String name, int price, String author, BookAvailability availability, BookCategory category, BookSubCategory subCategory) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.availability = availability;
        this.category = category;
        this.subCategory = subCategory;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public BookSubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(BookSubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public BookAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(BookAvailability availability) {
        this.availability = availability;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getId() != book.getId()) return false;
        if (getPrice() != book.getPrice()) return false;
        if (!getName().equals(book.getName())) return false;
        if (!getAuthor().equals(book.getAuthor())) return false;
        if (!getAvailability().equals(book.getAvailability())) return false;
        if (getCategory() != book.getCategory()) return false;
        return getSubCategory() == book.getSubCategory();
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPrice();
        result = 31 * result + getAuthor().hashCode();
        result = 31 * result + getAvailability().hashCode();
        result = 31 * result + getCategory().hashCode();
        result = 31 * result + getSubCategory().hashCode();
        return result;
    }
}
