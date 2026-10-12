package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test
    public void validLoginTest(){
        LoginPage loginpage= new LoginPage(driver,wait);
        loginpage.login("tomsmith", "SuperSecretPassword!");

        String successMsg = loginpage.getSuccessMessage();
        Assert.assertTrue(
                successMsg.contains("You logged into a secure area!"),
                "Success message not shown after valid login"
        );
    }

    @Test
    public void wrongUsernameTest(){
        LoginPage loginpage= new LoginPage(driver,wait);
        loginpage.login("wronguser", "SuperSecretPassword!");

        String errorMsg=loginpage.getErrorMessage();
        Assert.assertTrue(
                errorMsg.contains("Your username is invalid!"),
        "Expected invalid username error"
        );
    }

    @Test
    public void wrongPasswordTest(){
        LoginPage loginpage= new LoginPage(driver,wait);
        loginpage.login("tomsmith","wrongPassword");

        String errorMsg=loginpage.getErrorMessage();
        Assert.assertTrue(
                errorMsg.contains("Your password is invalid!"),
                "Expected invalid password error"
        );
    }
}
