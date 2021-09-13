package com.myProject.utilities.api;

import com.github.javafaker.Faker;
import com.myProject.POJOs.NewComment_POJO;
import com.myProject.POJOs.NewPost_POJO;
import com.myProject.POJOs.NewUser_POJO;
import com.myProject.utilities.common.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    int userID;
    int postID;
    int commentID;

    Faker faker = new Faker();

    public RequestSpecification requestSpecification() {
        RequestSpecification requestSpecification = given()
                .auth()
                .oauth2(ConfigurationReader.getProperty("token"))
                .contentType(ContentType.JSON);

        return requestSpecification;
    }

    public NewUser_POJO newUserPojo() {

        return new NewUser_POJO(faker.funnyName().name(), faker.demographic().sex(), faker.internet().emailAddress(), "active");
    }

    public Response POST_oneUser() {

        Response query =
                given().
                    spec(requestSpecification()).
                    body(newUserPojo()).
                when().
                    post(ConfigurationReader.getProperty("allUsersEndpoint"));

        userID = query.path("data.id");

        return query;
    }

    public Response GET_oneUser() {
        return given()
                .spec(requestSpecification())
                .pathParam("userID", userID).
               when()
                .get(ConfigurationReader.getProperty("oneUserEndpoint"));
    }

    public NewPost_POJO newPostPojo(){
        return new NewPost_POJO(String.valueOf(userID), faker.harryPotter().book(),faker.harryPotter().quote());
    }

    public Response POST_onePost(){

       Response query =
               given()
                    .spec(requestSpecification()).
                    body(newPostPojo()).
               when()
                    .post(ConfigurationReader.getProperty("allPostsEndpoint"));
       postID = query.path("data.id");
       return query;
    }

    public NewComment_POJO newCommentPojo(){
        return new NewComment_POJO(String.valueOf(postID), faker.name().fullName(), faker.internet().emailAddress(), faker.harryPotter().quote());
    }

    public Response POST_oneComment(){
        Response query =
        given().
                spec(requestSpecification()).
                body(newCommentPojo()).
        when()
                .post(ConfigurationReader.getProperty("allCommentsEndpoint"));

        commentID = query.path("data.id");
        return query;
    }
}
