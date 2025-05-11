package ApiHelpers;

import Apis.users;
import io.restassured.response.Response;
import pojo.UserPojo;

public class UserHelper {

    public Response createUser(UserPojo pojo) {
        users users = new users();
        return users.callAddUserAPI(pojo);
    }

    public Response getUser(Object userId) {
        users users = new users();
        return users.callGetUserAPI(userId);
    }

    public Response updateUser(Object userId, UserPojo pojo) {
        users users = new users();
        return users.callUpdateUserAPI(userId, pojo);
    }
}
