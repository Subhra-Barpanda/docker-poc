package com.newtechnology.poc.controller;

import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.User;
import com.newtechnology.poc.service.LibraryAdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@RestController
@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryAdminController {

    private LibraryAdminServiceInterface libraryAdminServiceInterface;

    @Autowired
    public LibraryAdminController(LibraryAdminServiceInterface libraryAdminServiceInterface){
        this.libraryAdminServiceInterface = libraryAdminServiceInterface;
    }

    @POST
    @Path("/book")
    public Response addBook(Book book){
        final Book addedBook = libraryAdminServiceInterface.addBook(book);
        return Response.status(Response.Status.CREATED).entity(addedBook).build();
    }

    @POST
    @Path("/books")
    public Response addBookCollection(List<Book> books){
        final Set<Book> addedBookSet = libraryAdminServiceInterface.addBookCollection(books);
        return Response.status(Response.Status.CREATED).entity(addedBookSet).build();
    }

    @POST
    @Path("/user")
    public Response addUserToLibrary(User user){
        final User addedUser = libraryAdminServiceInterface.addUserToLibrary(user);
        return Response.status(Response.Status.CREATED).entity(addedUser).build();
    }

    @POST
    @Path("/users")
    public Response addUsersListToLibrary(List<User> users){
        final Set<User> addedUserSet = libraryAdminServiceInterface.addUsersListToLibrary(users);
        return Response.status(Response.Status.CREATED).entity(addedUserSet).build();
    }

    @GET
    @Path("/users/defaulted")
    public Response getListOfDefaultedUsers(){
        final Set<User> listOfDefaultedUsers = libraryAdminServiceInterface.getListOfDefaultedUsers();
        return Response.ok(listOfDefaultedUsers).build();
    }

    @GET
    @Path("/books/notIssuedOnce")
    public Response getListOfBooksNeverIssued(){
        final Set<Book> listOfBooksNeverIssued = libraryAdminServiceInterface.getListOfBooksNeverIssued();
        return Response.ok(listOfBooksNeverIssued).build();
    }

    @GET
    @Path("/users/{userType}")
    public Response getListOfAllUsers(@PathParam("userType") String userType){
        final Set<User> setOfAllUsers = libraryAdminServiceInterface.getListOfAllUsers(userType);
        return Response.ok(setOfAllUsers).build();
    }


}
