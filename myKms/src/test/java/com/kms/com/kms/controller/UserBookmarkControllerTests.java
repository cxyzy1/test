package com.kms.com.kms.controller;

import com.kms.com.kms.entity.User;
import com.kms.com.kms.entity.UserBookmark;
import com.kms.com.kms.tools.TestConstant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBookmarkControllerTests {
    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
    @Autowired
    ApplicationContext context;
    WebTestClient userBookmarkClient;

    @Before
    public void setup() {
        userBookmarkClient = WebTestClient.bindToApplicationContext(context).configureClient()
                .baseUrl(TestConstant.BASE_URL + "/ub")
                .filter(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void testAdd() {
        delAllUserBookmarks();
        String title = "test1";
        String userId = addOneUser().getId();

        UserBookmark userBookmark = userBookmarkClient.post().uri("/add")
                .body(BodyInserters.fromObject(getUserBookmark(userId, title)))
                .exchange().expectBody(UserBookmark.class).returnResult().getResponseBody();
        Assert.assertEquals(title, userBookmark.getTitle());
    }

    @Test
    public void testUpdateState() {
        String state = "finished";
        UserBookmark userBookmark = getOneUserBookmarkFromServer();
        userBookmark.setState(state);
        updateAndCheckModifiedCount(userBookmark);
        checkModifiedStateValue(userBookmark.getId(), state);
    }

    @Test
    public void testUpdateProgress() {
        short progress = 80;
        UserBookmark userBookmark = getOneUserBookmarkFromServer();
        userBookmark.setProgress(progress);
        updateAndCheckModifiedCount(userBookmark);
        checkModifiedProgressValue(userBookmark.getId(), progress);
    }

    private UserBookmark getOneUserBookmarkFromServer() {
        delAllUserBookmarks();
        addUserBookmark("", 1);
        return userBookmarkClient.get().uri("/list").exchange().expectBodyList(UserBookmark.class)
                .returnResult().getResponseBody().get(0);
    }

    private void updateAndCheckModifiedCount(UserBookmark userBookmark) {
        userBookmarkClient.post().uri("/update").accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(userBookmark))
                .exchange().expectBody().jsonPath("$.modifiedCount")
                .isEqualTo(1);
    }

    private User addOneUser() {
        WebTestClient userClient = WebTestClient.bindToApplicationContext(context).configureClient()
                .baseUrl(TestConstant.BASE_URL + "/user")
                .filter(documentationConfiguration(restDocumentation)).build();
        return userClient.post()
                .uri("/add").accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(User.builder().name("test1234").build()))
                .exchange()
                .expectBody(User.class).returnResult().getResponseBody();
    }
    private void checkModifiedStateValue(String id, String state) {
        userBookmarkClient.get().uri("/get?id=" + id).accept(MediaType.APPLICATION_JSON)
                .exchange().expectBody().jsonPath("$.state")
                .isEqualTo(state);
    }

    private void checkModifiedProgressValue(String id, short progress) {
        userBookmarkClient.get().uri("/get?id=" + id).accept(MediaType.APPLICATION_JSON)
                .exchange().expectBody().jsonPath("$.progress")
                .isEqualTo(progress + "");
    }

    @Test
    public void testEmptyList() {
        delAllUserBookmarks();
        userBookmarkClient.get().uri("/list").exchange().expectBody().consumeWith(document(""));
    }

    @Test
    public void testListWithUsers() {
        delAllUserBookmarks();
        int count = 10;
        addUserBookmark("", count);
        userBookmarkClient.get().uri("/list").exchange().expectBodyList(UserBookmark.class).hasSize(count);
    }


    private void addUserBookmark(String userId, int count) {
        for (int i = 0; i < count; i++) {
            userBookmarkClient.post().uri("/add").body(BodyInserters.fromObject(getUserBookmark(userId, i))).exchange();
        }
    }

    private UserBookmark getUserBookmark(String userId, int i) {
        return UserBookmark.builder()
                .userId(userId)
                .title("title_" + i)
                .url("url_" + i)
                .build();
    }

    private UserBookmark getUserBookmark(String userId, String title) {
        return UserBookmark.builder()
                .userId(userId)
                .title(title)
                .url("https://www.baidu.com")
                .build();
    }

    private void delAllUserBookmarks() {
        userBookmarkClient.get().uri("/delAll").exchange();
    }

//    @Test
//    public void getANoneExistedPostShouldReturn404() {
//        userBookmarkClient
//            .get()
//            .uri("/posts/ABC")
//            .exchange()
//            .expectStatus().isNotFound();
//    }
//
//    @Test
//    public void createAPostNotAllowedWhenIsNotAuthorized() {
//        userBookmarkClient
//            .post()
//            .uri("/posts")
//            .body(BodyInserters.fromObject(User.builder().name("Post test").password("123456").build()))
//            .exchange()
//            .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
//
//    @Test
//    public void deletePostsNotAllowedWhenIsNotAdmin() {
//        userBookmarkClient
//            .mutate().filter(basicAuthentication("user", "password")).build()
//            .delete()
//            .uri("/posts/1")
//            .exchange()
//            .expectStatus().isEqualTo(HttpStatus.FORBIDDEN);
//    }
//
//    @Test
//    public void postCrudOperations() {
//        int randomInt = new Random().nextInt();
//        String title = "Post test " + randomInt;
//        FluxExchangeResult<Void> postResult = userBookmarkClient
//            .mutate().filter(basicAuthentication("user", "password")).build()
//            .post()
//            .uri("/posts")
//            .body(BodyInserters.fromObject(Post.builder().title(title).content("content of " + title).build()))
//            .exchange()
//            .expectStatus().isEqualTo(HttpStatus.CREATED)
//            .returnResult(Void.class);
//
//        URI location = postResult.getResponseHeaders().getLocation();
//        assertNotNull(location);
//
//        EntityExchangeResult<byte[]> getResult = userBookmarkClient
//            .get()
//            .uri(location)
//            .exchange()
//            .expectStatus().isOk()
//            .expectBody().jsonPath("$.title").isEqualTo(title)
//            .returnResult();
//
//        String getPost = new String(getResult.getResponseBody());
//        assertTrue(getPost.contains(title));
//    }

}
