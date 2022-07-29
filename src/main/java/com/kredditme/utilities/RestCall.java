/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kredditme.utilities;

import com.kredditme.Services.CrowdFundingService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 *
 * @author USER
 */
@Component
public class RestCall {
    
     public String executeInitializeRequest(JSONObject jsonObject) throws JSONException, IOException {
        HttpURLConnection connection = null;
        String endPoint = "https://api.paystack.co/transaction/initialize";
        try {
            
            URL url = new URL(endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer sk_test_6753ccc9800eb4ed4643f1ecaae30071be4c91ef");
            connection.setDoOutput(true);
            String dataToSend = jsonObject.toString();
            
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes(StandardCharsets.UTF_8);
                wr.write(in, 0, in.length);
            }
           
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
           // System.out.println("dataReceived: "+myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            Logger.getLogger(RestCall.class.getName()).log(Level.INFO, "Paystack Error...{0}", e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
     
     
   
     
     
     public String executeRequest(JSONObject js) throws JSONException, IOException {
        HttpURLConnection connection = null;
        try {
            URL url = new URL("https://kreditme.herokuapp.com/api/v1/get_user_with_token");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            String dataToSend = js.toString();
            //System.out.println("dataToSend: "+dataToSend + "url: "+endPoint);
           // JSONObject requestObj = new JSONObject();
            try (OutputStream wr = connection.getOutputStream()){
                byte[] in = dataToSend.getBytes(StandardCharsets.UTF_8);
                wr.write(in, 0, in.length);
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
           // System.out.println("dataReceived: "+myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
     
     
      public String executeBankRequest() throws JSONException, IOException {
        HttpURLConnection connection = null;
        String endPoint = "https://api.paystack.co/bank";
        try {
            URL url = new URL(endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer sk_test_6753ccc9800eb4ed4643f1ecaae30071be4c91ef");
            connection.setDoOutput(true);
           
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
           // System.out.println("dataReceived: "+myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
      
      
      public String executeVerifyPayment(String reference) throws JSONException, IOException {
        HttpURLConnection connection = null;
        String endPoint = "https://api.paystack.co/transaction/verify/"+reference;
        try {
            URL url = new URL(endPoint);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer sk_test_6753ccc9800eb4ed4643f1ecaae30071be4c91ef");
            connection.setDoOutput(true);
            
           
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder myResponse = new StringBuilder();
            String my_response;
            while ((my_response = rd.readLine()) != null) {
                myResponse.append(my_response);
            }
           // System.out.println("dataReceived: "+myResponse.toString());
            return myResponse.toString();
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
      
      
      
       
    
}
