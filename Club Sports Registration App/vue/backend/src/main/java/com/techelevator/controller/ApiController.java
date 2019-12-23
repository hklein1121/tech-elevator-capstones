package com.techelevator.controller;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.authentication.UnauthorizedException;
import com.techelevator.model.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping("/api/clubsports")
@CrossOrigin
public class ApiController {

    @Autowired
    private AuthProvider authProvider;

    @GetMapping("/")
    public String authorizedOnly() throws UnauthorizedException {
    	
    	User user = authProvider.getCurrentUser();
        System.out.println("Username: " + user.getUsername() );
        System.out.println("User Role: " + user.getRole());
    	
        /*
        You can lock down which roles are allowed by checking
        if the current user has a role.
        
        In this example, if the user does not have the admin role
        we send back an unauthorized error.
        */
        if (!authProvider.userHasRole(new String[] { "admin" })) {
            throw new UnauthorizedException();
        }
        return "Success";
    }
}