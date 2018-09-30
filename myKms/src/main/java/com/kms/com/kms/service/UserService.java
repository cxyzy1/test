package com.kms.com.kms.service;

import com.kms.com.kms.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author beibei
 */
public interface UserService {
    Mono<User> get(String id);
    Flux<User> list();
    Mono<User> add(User user);
    Mono<UpdateResult> update(User user);
    Mono<DeleteResult> delete(String id);

    Mono<Void> deleteAll();
}
