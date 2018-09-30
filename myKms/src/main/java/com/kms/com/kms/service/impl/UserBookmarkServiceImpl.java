package com.kms.com.kms.service.impl;

import com.kms.com.kms.entity.UserBookmark;
import com.kms.com.kms.service.UserBookmarkService;
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
public class UserBookmarkServiceImpl implements UserBookmarkService {
    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<UserBookmark> get(String id) {
        return reactiveMongoTemplate.findById(id,UserBookmark.class);
    }

    @Override
    public Flux<UserBookmark> list() {
        return reactiveMongoTemplate.findAll(UserBookmark.class);
    }

    /**
     * mongo数据库真是烦。新增记录时不能给字段设置默认值（给书签创建时间和更新时间设置数据库当前时间）。
     * 只能先新增记录，再更新记录，最后还要查询出来返回给客户端。一步变成了三步。
     * 后面如果mongo数据库支持了字段默认值，就不用这么麻烦了。
     * @param userBookmark
     * @return
     */
    @Override
    public Mono<UserBookmark> add(UserBookmark userBookmark) {
        userBookmark.setId(new ObjectId().toString());
        return reactiveMongoTemplate.save(userBookmark)
                .flatMap(this::updateBookmarkTimeAsNow)
                .flatMap(this::queryBookmarkAgain);
    }

    /**
     * 支持对状态和进度的更新
     * @param userBookmark
     * @return
     */
    @Override
    public Mono<UpdateResult> update(UserBookmark userBookmark) {
        Query query = getQueryById(userBookmark);
        Update update = new Update();
        if(userBookmark.getState()!=null)
        {
            update.set("state",userBookmark.getState());
        }
        if(userBookmark.getProgress()!=0)
        {
            update.set(UserBookmark.FIELD_PROGRESS,userBookmark.getProgress());
            update.currentDate(UserBookmark.FIELD_PROGRESS_TIME);
        }
        return reactiveMongoTemplate.updateFirst(query, update, UserBookmark.class);
    }

    @Override
    public Mono<DeleteResult> delete(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return reactiveMongoTemplate.remove(query, UserBookmark.class);
    }

    @Override
    public Mono<Void> deleteAll() {
        return reactiveMongoTemplate.dropCollection(UserBookmark.class);
    }

    /**
     * 在更新完书签的时间后再次查询出来，返回给客户端。
     * @param bookmarkId
     * @return
     */
    private Mono<UserBookmark> queryBookmarkAgain(String bookmarkId) {
        return reactiveMongoTemplate.findById(bookmarkId, UserBookmark.class);
    }

    /**
     * 更新书签的创建和更新时间为数据库当前时间
     * @param userBookmark1
     * @return
     */
    private Mono<String> updateBookmarkTimeAsNow(UserBookmark userBookmark1) {
        Query query = getQueryById(userBookmark1);
        Update update = new Update();
        update.currentDate(UserBookmark.FIELD_CREATED_TIME);
        update.currentDate(UserBookmark.FIELD_UPDATED_TIME);
        update.currentDate(UserBookmark.FIELD_PROGRESS_TIME);
        return reactiveMongoTemplate.updateFirst(query, update, UserBookmark.class).map(updateResult -> userBookmark1.getId());
    }

    /**
     * 获取按照书签id查找的Query
     * @param userBookmark
     * @return
     */
    private Query getQueryById(UserBookmark userBookmark) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(userBookmark.getId()));
        return query;
    }



}
