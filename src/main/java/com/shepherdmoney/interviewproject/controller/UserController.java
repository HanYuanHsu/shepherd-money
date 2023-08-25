package com.shepherdmoney.interviewproject.controller;

import com.shepherdmoney.interviewproject.model.User;
import com.shepherdmoney.interviewproject.repository.*;

import com.shepherdmoney.interviewproject.vo.request.CreateUserPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {
    // wire in the user repo
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserRepository ur) {
        this.userRepository = ur;
    }

    /**
     * creates a user entity with information given in the payload, stores the user entity in the database, and
     * return 200 ok response with the id of the saved user
     *
     * @param payload user's information
     * @return 200 ok response with the id of the saved user
     */
    @PutMapping("/user") // handles PUT requests
    public ResponseEntity<Integer> createUser(@RequestBody CreateUserPayload payload) {
        // create a user entity with information given in the payload
        User newUser = new User(payload.getName(), payload.getEmail());

        // store the user entity in the database
        User savedUser = this.userRepository.save(newUser);

        // create a ResponseEntity with http status 'ok'
        // and return it with the id of the saved user
        return ResponseEntity.ok(savedUser.getId());
    }


    /**
     * Deletes the user specified by userId
     * @param userId
     * @return A ResponseEntity with an appropriate HTTP status code and response body.
     *          200 ok: if the user exists and is successfully deleted
     *          400 Bad Request: if the user does not exist
     */
    @DeleteMapping("/user")
    public ResponseEntity<String> deleteUser(@RequestParam int userId) {
        // if userId exists, return 200 OK and delete it from the user repo
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            String responseString = String.format("User with id %d has been successfully deleted.", userId);
            return ResponseEntity.ok(responseString);
        }

        // else, return 400 Bad Request
        String responseString = String.format("User with id %d does not exist.", userId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseString);
    }
}
