package com.GpNgen.utility;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class confirmimgtransferAPI {
	public static void main(String[] args) {
        // Step 1: Create Order and extract job_id
        Response createOrderResponse = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body("{ \"api_key\":\"your_create_order_key\", \"other_fields\":\"value\" }") // Replace with actual payload
                .post("https://staging.production.nextgenphotosolutions.com/Gpservices/createorder");

        String jobId = createOrderResponse.jsonPath().getString("nextgen_job_id");

        // Step 2: Use jobId to confirm image transfer
        JSONObject requestBody = new JSONObject();
        requestBody.put("api_key", "GP=Ha2xc0Rcc2less2=NG");
        requestBody.put("job_id", jobId);
        requestBody.put("img_transferred", "Y");

        Response confirmTransferResponse = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post("https://staging.production.nextgenphotosolutions.com/Gpservices/confirmimagetransferred");

        // Print the response
        System.out.println("Image Transfer Response: " + confirmTransferResponse.getBody().asString());
	}

}
