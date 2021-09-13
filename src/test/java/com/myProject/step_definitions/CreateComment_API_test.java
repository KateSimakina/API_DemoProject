package com.myProject.step_definitions;

import com.myProject.utilities.api.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CreateComment_API_test extends ApiUtils{
    Response responseCreateUser;
    Response responseCreatePost;
    Response responseCreateComment;
    int userID;
    int postID;
    int commentID;
    @Given("The user created and the user post created by providing the existing ID")
    public void the_user_created_and_the_user_post_created_by_providing_the_existing_id() {
        responseCreateUser = POST_oneUser();
        userID = responseCreateUser.jsonPath().getInt("data.id");

        responseCreatePost = POST_onePost();
        postID = responseCreatePost.jsonPath().getInt("data.id");

        responseCreatePost.then().statusCode(201);

    }
    @When("I send a POST request to comments endpoint with valid payload by providing the existing post ID")
    public void i_send_a_post_request_to_endpoint_with_valid_payload_by_providing_the_existing_post_id() {

        responseCreateComment = POST_oneComment();
        commentID = responseCreateComment.path("data.id");

    }
    @Then("I should get the {int} Created http status code")
    public void i_should_get_the_created_http_status_code(Integer int1) {

        responseCreateComment.then().statusCode(201);
    }
    @Then("The response payload should contain following fields:")
    public void the_response_payload_should_contain_following_fields(List<String> expectedFields) {

        Map<String, String> JsonObj = responseCreateComment.jsonPath().getMap("data");

        Set<String> keySet= JsonObj.keySet();

        for (String key : keySet) {
            Assert.assertTrue (expectedFields.contains (key));
        }
    }

}
