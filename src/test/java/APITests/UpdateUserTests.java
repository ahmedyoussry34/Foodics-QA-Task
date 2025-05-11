package APITests;

import ApiHelpers.UserHelper;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pojo.UserPojo;

import static utils.ConfigReader.getValueFromConfig;

public class UpdateUserTests {

    UserHelper userHelper = new UserHelper();

    @BeforeTest
    public String createUserAndGetId() {
        UserPojo createPayload = new UserPojo(
                getValueFromConfig("user"),
                getValueFromConfig("job")
        );
        Response createResponse = userHelper.createUser(createPayload);
        return new JSONObject(createResponse.asPrettyString()).getString("id");
    }

    @Test(priority = 1)
    public void updateUserWithValidData() {
        String userId = createUserAndGetId();
        String updatedName = "Updated_" + getValueFromConfig("user");
        String updatedJob = "Updated_" + getValueFromConfig("job");
        UserPojo updatePayload = new UserPojo(updatedName, updatedJob);
        Response updateResponse = userHelper.updateUser(userId, updatePayload);
        JSONObject json = new JSONObject(updateResponse.asPrettyString());
        Assert.assertEquals(updateResponse.statusCode(), 200);
        Assert.assertEquals(json.getString("name"), updatedName);
        Assert.assertEquals(json.getString("job"), updatedJob);
        Assert.assertTrue(updateResponse.getTime() < 3000, "Response too slow");
    }

    @Test(priority = 2)
    public void updateUserInvalidId() {
        UserPojo payload = new UserPojo(getValueFromConfig("user"), getValueFromConfig("job"));
        Response response = userHelper.updateUser("invalid id", payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 3)
    public void updateUserWithEmptyName() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo("", getValueFromConfig("job"));
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 4)
    public void updateUserWithEmptyJob() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo(getValueFromConfig("user"), "");
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 5)
    public void updateUserWithNullJob() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo(getValueFromConfig("user"), null);
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 6)
    public void updateUserWithNullName() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo(null, getValueFromConfig("job"));
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 7)
    public void updateUserWithOnlySpace() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo(" ", " ");
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 8)
    public void updateUserWithLongNameAndJob() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo(
                "LongName ".repeat(1000),
                "LongJob ".repeat(1000)
        );
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 9)
    public void updateUserWithSpecialCharacters() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo("$#$#%", "&^#&**#");
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 10)
    public void updateUserWithScriptsInField() {
        String userId = createUserAndGetId();
        UserPojo payload = new UserPojo(
                "<script>alert('XSS')</script>",
                "'; DROP TABLE users;--"
        );
        Response response = userHelper.updateUser(userId, payload);
        Assert.assertEquals(response.statusCode(), 200);
        JSONObject json = new JSONObject(response.asPrettyString());
        Assert.assertTrue(json.getString("name").contains("<script>"));
    }

}
