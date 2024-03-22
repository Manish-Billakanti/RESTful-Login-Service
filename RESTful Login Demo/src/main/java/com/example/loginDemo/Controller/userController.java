package com.example.loginDemo.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.loginDemo.Model.userData;
import com.example.loginDemo.Service.userService;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RestController
public class userController {

    @Autowired
    userService service;

    @RequestMapping(path = "/getUsers", method=RequestMethod.GET)
    public ResponseEntity<List<userData>> getAllUsers() {
            return service.getAllUserData();    
    }

    @RequestMapping(value="/getUser/{username}", method=RequestMethod.GET)
    public ResponseEntity<userData> getUserName(@PathVariable("username") String userName) {
        return service.getUserName(userName);
}

    @RequestMapping(value="/createUser/", method=RequestMethod.POST)
    public ResponseEntity<userData> createUser(@RequestBody userData user) {
            return service.createUser(user);
    }

    @RequestMapping(value="/login/{userName}/{password}", method=RequestMethod.POST)
    public ResponseEntity<String> getLogin(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        return service.login(userName, password);
    }

    @RequestMapping(value="/updatePassword/{username}/{password}", method=RequestMethod.POST)
    public ResponseEntity<String> updatePassword(@PathVariable("username") String userName, @PathVariable("password") String password) {
        return service.updatePassword(userName, password);
    }

    
}
