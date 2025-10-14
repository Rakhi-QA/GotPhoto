package com.GpNgen.Base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.Base.APIpostRequest.JobData;
import com.GpNgen.utility.GlobalData;

//======================Team images is not present =======================
public class APIpostReq3
{
	public static class JobData {
        public String jobName;
        public String checkoutUrl;
        public static String jobId;
        
        public JobData(String jobName, String checkoutUrl, String jobId) {
            this.jobName = jobName;
            this.checkoutUrl = checkoutUrl;
            this.jobId = jobId;
        }

        public String getJobName() {
            return jobName;
        }

        public String getCheckoutUrl() {
            return checkoutUrl;
        }

        public String getJobId() {
            return jobId;
        }
        
    }
	
	
	public static JobData  sendCreateJobRequest3() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String jobName = "Test_AutoJob_" + timeStamp;
		
        String requestBody = "{\n" +
        		"    \"firstname\": \"Rakhhi\",\n" +
        		"    \"lastname\": \"Doijad\",\n" +
        		"    \"phone\": \"\",\n" +
        		"    \"country\": \"OT\",\n" +
        		"    \"api_key\": \"GP=Ha2xc0Rcc2less2=NG\",\n" +
        		"    \"job_name\": \"" + jobName + "\",\n" +
        		"    \"alias_name\":\"" + jobName + "\",\n" +
        		"    \"email_id\": \"rakhiqa@tiuconsulting.com\",\n" +
        		"    \"editing_request_id\": \"407\",\n" +
        		"    \"redirect_success_url\": \"https://gotphoto.com\",\n" +
        		"    \"players_detail\": {\n" +
        		"        \"0\": {\n" +
        		"            \"first_name\": \"Ana\",\n" +
        		"            \"last_name\": \"pat\",\n" +
        		"            \"team_name\": \"YANKEE\",\n" +
        		"            \"jersey_number\": \"11\",\n" +
        		"            \"team_image\": \"\",\n" +
        		"            \"individual_image1\": \"A.jpg\",\n" +
        		"            \"access_code\": \"12A1\"\n" +
        		"        },\n" +
        		"        \"1\": {\n" +
        		"            \"first_name\": \"Bob\",\n" +
        		"            \"last_name\": \"D\",\n" +
        		"            \"team_name\": \"Team A\",\n" +
        		"            \"jersey_number\": \"12\",\n" +
        		"            \"individual_image1\": \"B.jpg\",\n" +
        		"            \"individual_image2\": \"C.jpg\",\n" +
        		"            \"access_code\": \"12A2\"\n" +
        		"        },\n" +
        		"        \"2\": {\n" +
        		"            \"first_name\": \"abinash\",\n" +
        		"            \"last_name\": \"swan\",\n" +
        		"            \"team_name\": \"KANSAS CITY 7\",\n" +
        		"            \"jersey_number\": \"12\",\n" +
        		"            \"team_image\": \"\",\n" +
        		"            \"individual_image1\": \"D.jpg\",\n" +
        		"            \"individual_image2\": \"\",\n" +
        		"            \"individual_image3\": \"\",\n" +
        		"            \"individual_image4\": \"\",\n" +
        		"            \"individual_image5\": \"\",\n" +
        		"            \"access_code\": \"12A2\"\n" +
        		"        }\n" +
        		"    }\n" +
        		"}";

        RestAssured.baseURI = "https://staging.production.nextgenphotosolutions.com";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .post("/Gpservices/pushData");

        System.out.println("🔍 Full Response Body:\n" + response.asString());
        
       
        if (response.statusCode() != 200) {
            System.out.println("❌ API call failed! Status Code: " + response.statusCode());
            return new JobData(jobName, null, null);
        }
        
      response.prettyPrint(); // Optional: prints full response
      String checkout = response.jsonPath().getString("checkout_url"); 
      GlobalData.checkoutUrl = checkout;
      System.out.println("Generated checkout URL: " + checkout);
       
      String jobId = null;

      if (checkout != null && checkout.contains("jobno=")) {
          jobId = checkout.split("jobno=")[1].split("&")[0];
          System.out.println("✅ Extracted Job ID: " + jobId);
      } else {
          System.out.println("❌ Could not extract Job ID");
      }

      return new JobData(jobName, checkout, jobId);
  }
}
