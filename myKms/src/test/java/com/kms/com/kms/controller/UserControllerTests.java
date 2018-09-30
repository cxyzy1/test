package com.kms.com.kms.controller;

import com.kms.com.kms.entity.User;
import com.kms.com.kms.tools.TestConstant;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {

    @Rule
    public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    ApplicationContext context;

    WebTestClient userClient;

    @Before
    public void setup() {
        userClient = WebTestClient.bindToApplicationContext(context).configureClient()
                .baseUrl(TestConstant.BASE_URL+"/user")
                .filter(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    public void testEmptyList() {
        delAllUsers();
        userClient.get().uri("/list").exchange().expectBody().consumeWith(document(""));
    }
    @Test
    public void testListWithUsers() {
        delAllUsers();
        int count = 10;
        addUser(count);
        userClient.get().uri("/list").exchange().expectBodyList(User.class).hasSize(count);
    }

    @Test
    public void testAdd() {
        delAllUsers();
        String name= "test";
        userClient.post().uri("/add")
                .body(BodyInserters.fromObject(User.builder().name(name).build()))
                .exchange().expectBody().jsonPath("$.name")
                .isEqualTo(name);
    }

    public String addUser() {
        userClient.post().uri("/add")
                .body(BodyInserters.fromObject(User.builder().name("user").build()))
                .exchange();

        EntityExchangeResult<List<User>> result = userClient.get().uri("/add")
                .exchange().expectStatus().isOk().expectBodyList(User.class)
                .returnResult();
        return result.getResponseBody().get(0).getId();
    }


    private void addUser(int count)
    {
        for(int i=0;i<count;i++)
        {
            userClient.post().uri("/add")
                .body(BodyInserters.fromObject(User.builder().name("user_"+i).build()))
                .exchange();
        }
    }
    private void delAllUsers()
    {
        userClient.get().uri("/delAll").exchange();
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
