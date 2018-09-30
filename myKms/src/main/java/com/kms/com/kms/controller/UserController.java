package com.kms.com.kms.controller;

import com.kms.com.kms.entity.User;
import com.kms.com.kms.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author beibei
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/get")
    public Mono<ResponseEntity<User>> get(@RequestParam(value = "id") String id) {
        return userService.get(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/list")
    public Flux<User> list() {
        return userService.list();
    }

    @PostMapping(value = "/add")
    public Mono<User> add(@RequestBody User user) {
        System.out.println(user.toString());
        return userService.add(user);
    }

    @PostMapping(value = "/update")
    public Mono<ResponseEntity<UpdateResult>> update(@RequestBody User user) {
        return userService.update(user)
                .map(updateResult -> new ResponseEntity<>(updateResult, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/del")
    public Mono<DeleteResult> del(@RequestParam(value = "id") String id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/delAll")
    public Mono<Void> delAll() {
        return userService.deleteAll();
    }

}
