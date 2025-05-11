package APITests;

import ApiHelpers.UserHelper;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static utils.ConfigReader.getValueFromConfig;

public class GetUsersTests {

    UserHelper userHelper = new UserHelper();
/*
    I had To Use Static Id As The Endpoint Only Returns Registered Users
 */
    @Test(priority = 1)
    public void getUserWithValidId() {
        Response response = userHelper.getUser("2");
        JSONObject json = new JSONObject(response.asPrettyString());
        String email = json.getJSONObject("data").getString("email");
        String first_name = json.getJSONObject("data").getString("first_name");
        String last_name = json.getJSONObject("data").getString("last_name");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(email, getValueFromConfig("email"), "email mismatch");
        Assert.assertEquals(first_name, getValueFromConfig("first_name"), "first name mismatch");
        Assert.assertEquals(last_name, getValueFromConfig("last_name"), "last name mismatch");
        Assert.assertTrue(response.getTime() < 10000, "Response too slow");
    }

    @Test(priority = 2)
    public void getUserWithEmptyId() {
        Response response = userHelper.getUser("");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void getUserWithNullId() {
        Response response = userHelper.getUser(null);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(priority = 4)
    public void getUserWithNonExistentId() {
        Response response = userHelper.getUser("non_existent_12345");
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(priority = 5)
    public void getUserWithSpecialCharacters() {
        Response response = userHelper.getUser("!@#$%^&*()");
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(priority = 6)
    public void getUserWithLongId() {
        String longId = "id".repeat(1000);
        Response response = userHelper.getUser(longId);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(priority = 7)
    public void getUserWithScriptId() {
        Response response = userHelper.getUser("<script>alert(1)</script>");
        Assert.assertEquals(response.statusCode(), 404);
    }


}
