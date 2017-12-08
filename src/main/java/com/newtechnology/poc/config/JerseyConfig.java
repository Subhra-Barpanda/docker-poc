package com.newtechnology.poc.config;

import com.newtechnology.poc.controller.HelloController;
import com.newtechnology.poc.controller.LibraryAdminController;
import com.newtechnology.poc.controller.LibraryUserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/library")
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    private void init(){
        registerEndPoints();
    }

    private void registerEndPoints(){
        register(LibraryUserController.class);
        register(LibraryAdminController.class);
        register(HelloController.class);
        register(WadlResource.class);//available at application.wadl
    }
}
