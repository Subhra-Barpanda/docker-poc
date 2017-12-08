package com.newtechnology.poc.controller;

import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RestController
@Path("/hello/{userName}")
public class HelloController {

    @GET
    public String returnGreetings(@PathParam("userName") String userName){

        return "Hello "+userName;
    }
}
