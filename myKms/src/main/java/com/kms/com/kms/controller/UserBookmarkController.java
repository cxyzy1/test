package com.kms.com.kms.controller;

import com.kms.com.kms.entity.UserBookmark;
import com.kms.com.kms.service.UserBookmarkService;
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
@RequestMapping("/ub")
public class UserBookmarkController {

    @Autowired
    private UserBookmarkService userBookmarkService;

    @GetMapping(value = "/get")
    public Mono<ResponseEntity<UserBookmark>> get(@RequestParam(value = "id") String id) {
        return userBookmarkService.get(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/list")
    public Flux<UserBookmark> list() {
        return userBookmarkService.list();
    }

    @PostMapping(value = "/add")
    public Mono<UserBookmark> add(@RequestBody UserBookmark userBookmark) {
        return userBookmarkService.add(userBookmark);
    }

    @PostMapping(value = "/update")
    public Mono<ResponseEntity<UpdateResult>> update(@RequestBody UserBookmark userBookmark) {
        return userBookmarkService.update(userBookmark)
                .map(updateResult -> new ResponseEntity<>(updateResult, HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/del")
    public Mono<DeleteResult> del(@RequestParam(value = "id") String id) {
        return userBookmarkService.delete(id);
    }

    @GetMapping(value = "/delAll")
    public Mono<Void> delAll() {
        return userBookmarkService.deleteAll();
    }

}
