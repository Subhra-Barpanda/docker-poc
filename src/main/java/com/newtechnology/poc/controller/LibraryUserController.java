package com.newtechnology.poc.controller;

import com.newtechnology.poc.exception.ApplicationRunTimeException;
import com.newtechnology.poc.model.Book;
import com.newtechnology.poc.model.Error;
import com.newtechnology.poc.service.LibraryUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

@RestController
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LibraryUserController {

    private LibraryUserServiceInterface libraryUserServiceInterface;

    @Autowired
    public LibraryUserController(LibraryUserServiceInterface libraryUserServiceInterface){
        this.libraryUserServiceInterface = libraryUserServiceInterface;
    }

    @GET
    @Path("/books")
    public Response listAllBooks(@QueryParam("bookName") String bookName,
                                 @QueryParam("authorName") String authorName,
                                 @QueryParam("subCategory") String subCategory){
        if(bookName != null){
            return listAllBooksByBookName(bookName);
        }else if (authorName != null){
            return listAllBooksByAuthorName(authorName);
        }else if(subCategory != null){
            return listAllAvailableBooksBySubCategory(subCategory);
        }

        List<Book> bookList = libraryUserServiceInterface.listAllBooks();
        return Response.ok().entity(bookList).build();
    }

    private Response listAllBooksByBookName(String bookName){
        Set<Book> booksByBookName = libraryUserServiceInterface.listAllBooksByBookName(bookName);
        return Response.ok().entity(booksByBookName).build();
    }

    private Response listAllBooksByAuthorName(String authorName){
        Set<Book> booksByAuthorName = libraryUserServiceInterface.listAllBooksByAuthorName(authorName);
        return Response.ok().entity(booksByAuthorName).build();
    }

    private Response listAllAvailableBooksBySubCategory(String subCategory){
        Set<Book> availableBooksBySubCategory = libraryUserServiceInterface.listAllAvailableBooksBySubCategory(subCategory);
        return Response.ok().entity(availableBooksBySubCategory).build();
    }

    @GET
    @Path("/student/{rollNumber}/books")
    public Response listBooksIssuedToUser(@PathParam("rollNumber") String rollNumber){
        Set<Book> booksIssuedToUser = libraryUserServiceInterface.listBooksIssuedToUser(rollNumber);
        return Response.ok().entity(booksIssuedToUser).build();
    }

    @POST
    @Path("/student/{studentId}/books/{bookId}/issue")
    public Response issueBook(@PathParam("bookId") long bookId,@PathParam("studentId") long studentId){
        try{
            Book issuedBook = libraryUserServiceInterface.issueBook(bookId, studentId);
            return Response.status(Response.Status.CREATED).entity(issuedBook).build();
        }catch (ApplicationRunTimeException ex){
            Error error = new Error(ex.getErrorCode(),ex.getErrorMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @POST
    @Path("/student/{studentId}/books/{bookId}/return")
    public Response releaseBook(@PathParam("bookId") long bookId,@PathParam("studentId") long studentId){
        try {
            Book releasedBook = libraryUserServiceInterface.releaseBook(bookId, studentId);
            return Response.status(Response.Status.ACCEPTED).entity(releasedBook).build();
        }catch (ApplicationRunTimeException ex){
            Error error = new Error(ex.getErrorCode(),ex.getErrorMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }


}
