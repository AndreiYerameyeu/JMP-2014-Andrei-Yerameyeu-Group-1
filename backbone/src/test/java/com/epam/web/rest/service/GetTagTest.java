package com.epam.web.rest.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.service.TagService;

@RunWith(org.junit.runners.JUnit4.class)
public class GetTagTest {
    
    private HttpClient httpClient;
    private HttpMethod httpMethod;
    
    @Autowired
    private TagService tagService;
    
    @Before
    public void configure() {
        httpClient = new HttpClient();
        httpMethod = new GetMethod();
        httpMethod.setPath("http://localhost:8080/Bookmark_App/tag");
    }
    
    @Test
    public void testGet() {
        /*try {
            httpClient.executeMethod(httpMethod);
            String response = httpMethod.getResponseBodyAsString();
            System.out.println(response);
            List<Tag> tags = tagService.findAll();
            
            Gson g = new Gson();
            String json = g.toJson(tags);
            System.out.println(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
    
}
