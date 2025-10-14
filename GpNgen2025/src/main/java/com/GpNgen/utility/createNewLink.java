package com.GpNgen.utility;

import io.restassured.response.Response;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.RestAssured;

public class createNewLink {

	public static String generateOrderLink() {
        try {
            String jobName = "Auto_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            String apiUrl = "https://staging.production.nextgenphotosolutions.com/Gpservices/pushData";
            String jsonBody = "{\n" +
                    "  \"firstname\": \"Rakhi\",\n" +
                    "  \"lastname\": \"Doijad\",\n" +
                    "  \"api_key\": \"GP=Ha2xc0Rcc2less2=NG\",\n" +
                    "  \"job_name\": \"" + jobName + "\",\n" +
                    "  \"alias_name\": \"" + jobName + "\",\n" +
                    "  \"email_id\": \"rakhiqa@tiuconsulting.com\",\n" +
                    "  \"editing_request_id\": \"111\",\n" +
                    "  \"redirect_success_url\": \"https://gotphoto.com\",\n" +
                    "  \"players_detail\": {\n" +
                    "    \"0\": {\n" +
                    "      \"first_name\": \"rakesh\",\n" +
                    "      \"last_name\": \"pat\",\n" +
                    "      \"team_name\": \"YANKEES\",\n" +
                    "      \"jersey_number\": \"11\",\n" +
                    "      \"team_image\": \"img1.jpg\",\n" +
                    "      \"individual_image1\": \"img2.jpg\",\n" +
                    "      \"access_code\": \"12A1\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";

            URL url = new URL(apiUrl);
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonBody.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                return "https://staging.production.nextgenphotosolutions.com/job/" + jobName;
            } else {
                System.out.println("API failed: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
