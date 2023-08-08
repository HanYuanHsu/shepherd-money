package com.shepherdmoney.interviewproject.controller;

import com.shepherdmoney.interviewproject.model.User;
import com.shepherdmoney.interviewproject.repository.*;

import com.shepherdmoney.interviewproject.vo.request.CreateUserPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    // wire in the user repo
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository ur) {
        userRepository = ur;
    }

    @PutMapping("/user") // handles PUT requests
    public ResponseEntity<Integer> createUser(@RequestBody CreateUserPayload payload) {
        // create a user entity with information given in the payload
        User newUser = new User(payload.getName(), payload.getEmail());

        // store the user entity in the database
        User savedUser = userRepository.save(newUser);

        // create a ResponseEntity with http status 'ok'
        // and return it with the id of the saved user
        return ResponseEntity.ok(savedUser.getId());
    }

    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {
        // TODO: Return 200 OK if a user with the given ID exists, and the deletion is successful
        //       Return 400 Bad Request if a user with the ID does not exist
        //       The response body could be anything you consider appropriate
        return null;
    }

    @GetMapping("/foo")
    public void myFoo() {

    }
}
