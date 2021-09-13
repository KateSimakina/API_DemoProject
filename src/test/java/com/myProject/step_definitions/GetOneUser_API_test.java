package com.myProject.step_definitions;

import com.myProject.utilities.api.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GetOneUser_API_test extends ApiUtils {

    Response responsePost;
    Response responseGet;
    int id;
    String name;

    @DisplayName("Post a user, get user ID and verify that status code is 201")
    @Given("The user with given ID created and exists")
    public void the_user_with_given_id_created_and_exists() {

        responsePost = POST_oneUser();
        id = responsePost.path("data.id");
        name = responsePost.path("data.name");
        assertThat(responsePost.statusCode(), is(201));
    }

    @DisplayName("Get request to /public/v1/users/{userID} and validate the json schema")
    @When("I send a GET request to the end point with the appropriate ID")
    public void i_send_a_get_request_to_the_end_point_with_the_appropriate_id() {
        responseGet = GET_oneUser();
        responseGet.then()
                .body(matchesJsonSchemaInClasspath("oneUser_Schema.json"));
    }

    @DisplayName("Validate the http status code")
    @Then("I should get the {int} OK http status code")
    public void i_should_get_the_ok_http_status_code(Integer int1) {
        assertThat(GET_oneUser().statusCode(), is(int1));
    }

    @DisplayName("Validate the name and ID")
    @Then("My payload contains given name and ID")
    public void my_payload_contains_given_name_and_id() {

        assertAll(
                () -> assertThat(responseGet.jsonPath().getString("data.name"), is(name)),
                () -> assertThat(responseGet.jsonPath().getInt("data.id"), is(id))
        );
    }

}

/*
example of a nested POJO:
        Address_NestedPOJO addressParam = new Address_NestedPOJO("700 N Randolph st", "apt.1018", "Arlington", "VA", "22203", "USA" );
        User_NestedPOJO userPOJO = new User_NestedPOJO("Lenny", "male", faker.internet().emailAddress(), "active", addressParam);
 */
