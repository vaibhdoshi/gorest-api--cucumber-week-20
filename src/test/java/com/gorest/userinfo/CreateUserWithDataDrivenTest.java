package com.gorest.userinfo;

import com.gorest.steps.UsersSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.PropertyReader;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

//@Concurrent(threads = "8x")
//@UseTestDataFrom("src/test/java/resources/testdata/userinfo.csv")
//@RunWith(SerenityParameterizedRunner.class)

public class CreateUserWithDataDrivenTest extends TestBase {
    private String name;
    private String email;
    private String gender;
    private String status;
    private String token;
    private String token1= PropertyReader.getInstance().getProperty("token");
    @Steps
    UsersSteps usersSteps;

    @Title("Data driven test for adding multiple users to the application")
    @Test
    public void createMultipleUsers() {

        usersSteps.createUser(name,email,gender,status,token1).statusCode(201);

    }




}
