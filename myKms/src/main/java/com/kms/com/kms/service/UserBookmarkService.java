package com.kms.com.kms.service;

import com.kms.com.kms.entity.UserBookmark;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author beibei
 */
public interface UserBookmarkService {
    Mono<UserBookmark> get(String id);

    Flux<UserBookmark> list();

    Mono<UserBookmark> add(UserBookmark user);

    Mono<UpdateResult> update(UserBookmark user);

    Mono<DeleteResult> delete(String id);

    Mono<Void> deleteAll();
}
