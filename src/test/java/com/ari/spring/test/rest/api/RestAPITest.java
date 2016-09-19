/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ari.spring.test.rest.api;

import com.ari.spring.test.SpringTestApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ari-prasetiyo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
@WebIntegrationTest
public class RestAPITest {

    //Required to Generate JSON content from Java objects
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //Test RestTemplate to invoke the APIs.
    private RestTemplate restTemplate = new TestRestTemplate();

    //curl localhost:8080/iwantworkinrealtime/oauth/token -X POST 
    //-d "grant_type=password&scope=read&username=Prasetiyo&password=ari" 
    //-u client_storage:Victornya123
    //curl localhost:8080/iwantworkinrealtime/oauth/token -X POST 
    //-d "client_id=client_storage&client_secret=Victornya123&grant_type=password
    //&scope=read&username=Prasetiyo&password=ari" 
 
    HttpHeaders createHeaders() {
        return new HttpHeaders() {
            {
                String auth = "client_storage" + ":" + "Victornya123";
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes());
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
                set("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            }
        };
    }

    @Test
    public void testLogin() throws JsonProcessingException {

        Map<String, Object> requestBody = new HashMap<String, Object>();
        requestBody.put("client_id", "client_storage");
        requestBody.put("client_secret", "Victornya123");
        
        requestBody.put("scope", "read");
        requestBody.put("username", "Ari");
        requestBody.put("password", "ari");
        requestBody.put("grant_type", "password");
        
        HttpEntity<String> httpEntity
                = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody),  createHeaders());

        //Invoking the API
        Map<String, Object> apiResponse
                = restTemplate.postForObject("http://localhost:8080/iwantworkinrealtime/oauth/token", httpEntity, Map.class, Collections.EMPTY_MAP);

        System.out.println("======================================");
        System.out.println(apiResponse.get("access_token") );
        for (int a = 0; a < apiResponse.size(); a++) {
            System.out.println("" + apiResponse.values());
        }
        assertNotNull(apiResponse);

    }
}
