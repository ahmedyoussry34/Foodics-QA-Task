package Apis;

import io.restassured.response.Response;
import pojo.UserPojo;
import base.APIBase;

import static utils.ConfigReader.getValueFromConfig;


public class users extends APIBase {

    public Response callAddUserAPI(UserPojo pojo){
        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name + "\n");
        String endPoint = getValueFromConfig("user.endpoint");
         return POST(endPoint, pojo);
        }

    public Response callUpdateUserAPI(Object id, UserPojo pojo){

        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name + "\n");
        String endPoint = getValueFromConfig("user.endpoint")+"/"+id;
        return PUT(endPoint, pojo);

    }

    public Response callGetUserAPI(Object id){

        String name = new Object() {
        }.getClass().getEnclosingMethod().getName();
        System.out.println(name + "\n");
        String endPoint = getValueFromConfig("user.endpoint")+"/"+id;
        Response response = GET(endPoint);
        System.out.println(response.asPrettyString());
        return response;

    }

}
