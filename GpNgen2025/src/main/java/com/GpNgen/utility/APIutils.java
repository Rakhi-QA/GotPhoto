package com.GpNgen.utility;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class APIutils {
	
	public static String getCheckoutUrlFromAPI(String requestBody) {
        Response res = given()
            .header("Content-Type", "application/json")
            .body(requestBody)
            .post("https://staging.production.nextgenphotosolutions.com/Gpservices/pushData");

        res.prettyPrint(); // For debugging
        return res.jsonPath().getString("checkout_url");
}
}