package com.kms.com.kms.service.impl;

import com.kms.com.kms.entity.User;
import com.kms.com.kms.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author beibei
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<User> get(String id) {
        return reactiveMongoTemplate.findById(id,User.class);
    }

    @Override
    public Flux<User> list() {
        return reactiveMongoTemplate.findAll(User.class);
    }

    @Override
    public Mono<User> add(User user) {
        user.setId(new ObjectId().toString());
        return reactiveMongoTemplate.save(user);
    }

    @Override
    public Mono<UpdateResult> update(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(user.getId()));
        Update update = new Update();
        update.set("name",user.getName());
        return reactiveMongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public Mono<DeleteResult> delete(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return reactiveMongoTemplate.remove(query, User.class);
    }

    @Override
    public Mono<Void> deleteAll() {
        return reactiveMongoTemplate.dropCollection(User.class);
    }
}
