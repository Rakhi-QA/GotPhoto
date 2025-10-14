package com.GpNgen.utility;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImageTransferUtil {
	public static String extractJobIdFromUrl(String checkoutUrl) {
        try {
            URL url = new URL(checkoutUrl);
            String query = url.getQuery();
            
            if (query != null && !query.isEmpty()) {
                String[] params = query.split("&");
            
            for (String param : query.split("&")) {
                if (param.startsWith("jobno=")) {
                    //return param.split("=")[1];
                	 String jobId = param.split("=")[1];
                    System.out.println("✅ Extracted Job ID: " + jobId);
                    return jobId;
                }
            }
        } System.out.println("❌ Job ID not found in the URL.");
            }catch (Exception e) {
            System.out.println("❌ Failed to extract job ID: " + e.getMessage());
        }
        return null;
    }

    public static void confirmImageTransfer(String jobId) {
        String url = "https://staging.production.nextgenphotosolutions.com/Gpservices/confirmimagetransferred";
        try {
        	
        	if (jobId == null || jobId.isEmpty()) {
                System.out.println("❌ Cannot send API request — Job ID is null or empty.");
                return;
            }
            HttpClient client = HttpClient.newHttpClient();

            String requestBody = "{\n" +
                    "    \"api_key\": \"GP=Ha2xc0Rcc2less2=NG\",\n" +
                    "    \"job_id\": \"" + jobId + "\",\n" +
                    "    \"img_transferred\": \"Y\"\n" +
                    "}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("✅ Image transfer API Response: " + response.body());

        } catch (Exception e) {
            System.out.println("❌ Failed to confirm image transfer: " + e.getMessage());
        }
    }
}
