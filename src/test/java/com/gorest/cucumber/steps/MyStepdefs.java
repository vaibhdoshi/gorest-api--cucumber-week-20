package com.gorest.cucumber.steps;

import com.gorest.steps.UsersSteps;
import com.gorest.utils.PropertyReader;
import com.gorest.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

public class MyStepdefs {
    static ValidatableResponse response;
    static String token = PropertyReader.getInstance().getProperty("token");
    static String name = null;
    static String email = null;
    static int userID;


    @Steps
    UsersSteps usersSteps;

    @Given("^Gorest Application is running$")
    public void gorestApplicationIsRunning() {
    }

    @When("^I create a new user by providing the information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameEmailGenderStatus(String _name, String gender, String _email, String status) {
        name = TestUtils.getRandomValue() + _name;
        email = TestUtils.getRandomValue() + _email;
        response = usersSteps.createUser(name, gender, email, status, token);
    }

    @Then("^I verify that the user with ID is created$")
    public void iVerifyThatTheUserWithIDIsCreated() {
        response.log().all().statusCode(404);
        response = usersSteps.getUserByID(userID, token);
        userID = response.log().all().extract().path("id");
        System.out.println(userID);
    }

    @And("^I update the user with information name \"([^\"]*)\" email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iUpdateTheUserWithInformationNameEmailGenderStatus(String _name, String gender, String _email, String status) throws Throwable {
        name = name + "_updated";
        email = name + "@email.com";
        response = usersSteps.updateUser(name, gender, userID, email, status, token);
    }

    @And("^I verify that user with ID is updated successfully$")
    public void iVerifyThatUserWithIDIsUpdatedSuccessfully() {
        response = usersSteps.getUserByID(userID, token).statusCode(201);
    }

    @And("^I delete the user$")
    public void iDeleteTheUser() {
        response = usersSteps.deleteUser(userID, token);
    }

    @Then("^I verify user is deleted successfully from the application$")
    public void iVerifyUserIsDeletedSuccessfullyFromTheApplication() {
        response.statusCode(204);
        usersSteps.getUserByID(userID, token).statusCode(404);
    }

    @When("^User sends a Get request$")
    public void userSendsAGetRequest() {
        usersSteps.getUsers();
    }

    @Then("^User should get list of users and must get back a valid staus code 200$")
    public void userShouldGetListOfUsersAndMustGetBackAValidStausCode() {
        response.statusCode(200);
    }

    @Then("^I verify that user is created with userId and must get valid staus code 201$")
    public void iVerifyThatUserIsCreatedWithUserIdAndMustGetValidStausCode() {
        response = usersSteps.getUserByID(userID, token);
        response.statusCode(201);
    }

    @Then("^I verify that user is updated with userId and must get status code 200$")
    public void iVerifyThatUserIsUpdatedWithUserIdAndMustGetStatusCode() {
        response = usersSteps.getUserByID(userID, token);
        System.out.println(userID);
        response.statusCode(200);
    }

    @When("^user is deleted$")
    public void userIsDeleted() {
        usersSteps.deleteUser(userID, token).statusCode(204);

    }

    @Then("^I verify user is deleted with with status code 404$")
    public void iVerifyUserIsDeletedWithWithStatusCode() {
        usersSteps.getUserByID(userID, token).statusCode(404);
    }


}

