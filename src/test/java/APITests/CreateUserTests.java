package APITests;

import ApiHelpers.UserHelper;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.UserPojo;

import static utils.ConfigReader.getValueFromConfig;


public class CreateUserTests {

    UserHelper userHelper = new UserHelper();

    @Test(priority = 1)
    public void createUserWithValidData() {
        String userFromConfig = getValueFromConfig("user");
        String jobFromConfig = getValueFromConfig("job");
        UserPojo payload = new UserPojo(userFromConfig,getValueFromConfig("job"));
        Response response = userHelper.createUser(payload);
        JSONObject jsonObject = new JSONObject(response.asPrettyString());
        String id = jsonObject.getString("id");
        String name = jsonObject.getString("name");
        String job = jsonObject.getString("job");
        Assert.assertFalse(id.isEmpty(),"'id' is empty");
        Assert.assertEquals(name,userFromConfig,"name does not match");
        Assert.assertEquals(job,jobFromConfig,"job does not match");
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertTrue(response.getTime() < 10000,"response took to long");
    }

    @Test(priority = 2)
    public void createUserWithEmptyName() {
        UserPojo payload = new UserPojo("", getValueFromConfig("job"));
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 3)
    public void createUserWithEmptyJob() {
        UserPojo payload = new UserPojo(getValueFromConfig("user"), "");
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority =4)
    public void createUserWithNullJob() {
        UserPojo payload = new UserPojo(getValueFromConfig("user"), null);
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority =5)
    public void createUserWithNullName() {
        UserPojo payload = new UserPojo(null, getValueFromConfig("job"));
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 6)
    public void createUserWithOnlySpace() {
        UserPojo payload = new UserPojo(" ", " ");
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 7)
    public void createUserWithLongNameAndJob() {
        UserPojo payload = new UserPojo("Name Limit Test".repeat(1000), "Job Limit Test".repeat(1000));
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 8)
    public void createUserWithSpecialCharacters() {
        UserPojo payload = new UserPojo("&^$(&%%)", "#$%^^#^&");
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }
    @Test(priority = 9)
    public void createUserWithScriptsInField() {
        UserPojo payload = new UserPojo("<img src=x onerror=alert(1)>", "<img src=x onerror=alert(1)>");
        Response response = userHelper.createUser(payload);
        Assert.assertEquals(response.statusCode(), 201);
    }

}
