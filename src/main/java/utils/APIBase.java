package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.UserPojo;

import static io.restassured.RestAssured.given;
import static utils.ConfigReader.getValueFromConfig;

public class APIBase {

    public Response POST(String EndPoint, UserPojo pojo) {

        RestAssured.baseURI = getValueFromConfig("base.api.url");
        Response response;
        response = RestAssured.given().log().all()
                .headers(
                        "x-api-key",
                        "reqres-free-v1",
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .body(pojo)
                .post(EndPoint)
                .then()
                .extract().response();
        System.out.printf("Time is %s for %s EndPoint %s%n", response.getTime(), "POST", EndPoint);
        System.out.println(response.asPrettyString());
        return response;

    }

    public Response PUT(String EndPoint, UserPojo pojo) {

        RestAssured.baseURI = getValueFromConfig("base.api.url");
        Response response;
        response = RestAssured.given().log().all()
                .headers(
                        "x-api-key",
                        "reqres-free-v1",
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .body(pojo)
                .put(EndPoint)
                .then()
                .extract().response();
        System.out.printf("Time is %s for %s EndPoint %s%n", response.getTime(), "POST", EndPoint);
        System.out.println(response.asPrettyString());
        return response;
    }

    public Response GET(String EndPoint)
    {
        RestAssured.baseURI = getValueFromConfig("base.api.url");
        Response response = given().log().all()
                .headers(
                        "x-api-key",
                        "reqres-free-v1",
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .get(EndPoint)
                .then()
                .extract().response();
        System.out.printf("Time is %s for %s EndPoint %s%n", response.getTime(), "GET", EndPoint);
        return response;

    }

    public Response PUT(String EndPoint, String jsonBody) {

        RestAssured.baseURI = getValueFromConfig("base.api.url");
        Response response = given()
                .headers(
                        "x-api-key",
                        "reqres-free-v1",
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .when()
                .body(jsonBody)
                .put(EndPoint)
                .then()
                .extract().response();
        System.out.printf("Time is %s for %s EndPoint %s%n", response.getTime(), "PUT", EndPoint);
        return response;
    }

}
