package com.shrimpcolo.springbootrestapplication.controller;


import com.shrimpcolo.springbootrestapplication.SpringBootRestApplication;
import com.shrimpcolo.springbootrestapplication.model.Question;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = createHttpHeaders("user1", "secret1");

    private HttpHeaders createHttpHeaders(String userId, String password) {
        HttpHeaders headers = new HttpHeaders();
        // userid, password, Basic
        // "Authorization", "Basic" + Base64Encoding(userId + ":" + password)
        String auth = userId + ":" + password;

        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String headerValue = "Basic " + new String(encodedAuth);
        headers.add("Authorization", headerValue);

        return headers;
    }

    @Before
    public void setUpJSONAcceptType() {
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void retrieveQuestionById() {

        //String expected = "{id:Question1,description:Largest Country in the World,correctAnswer:Russia,options:[India,Russia,United States,China]}";
        String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";
        String url = "/surveys/Survey1/questions/Question1";

        HttpEntity<String> entity = new HttpEntity<>("DUMMY_DOESNT_MATTER", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(createUrl(url), HttpMethod.GET, entity, String.class);
        String body = responseEntity.getBody();
        System.out.println("TANHQ===> response = " + body);

        try {
            JSONAssert.assertEquals(expected, body, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private String createUrl(String url) {
        return "http://localhost:" + port + url;
    }


    @Test
    public void retrieveQuestionsForSurvey() {
        // step 1. expected string
        //String expected = "[{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]},{\"id\":\"Question2\",\"description\":\"Most Populus Country in the World\",\"correctAnswer\":\"China\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]},{\"id\":\"Question3\",\"description\":\"Highest GDP in the World\",\"correctAnswer\":\"United States\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]},{\"id\":\"Question4\",\"description\":\"Second largest english speaking country\",\"correctAnswer\":\"India\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}]";
        String url = "/surveys/Survey1/questions";

        // step 2. ResponseEntity
        ResponseEntity<List<Question>> responseEntity = restTemplate.exchange(
                createUrl(url),
                HttpMethod.GET,
                new HttpEntity<>("DUMMY_DOESNT_MATTER", headers),
                new ParameterizedTypeReference<List<Question>>() {
                });

        // step 3. JSONAssert
        List<Question> bodyQuestions = responseEntity.getBody();
        System.out.println("TANHQ===> response = " + bodyQuestions);

        Question sampleQuestion = new Question("Question1",
                "Largest Country in the World",
                "Russia",
                Arrays.asList("India", "Russia", "United States", "China"));

        assertTrue(bodyQuestions.contains(sampleQuestion));
    }

    @Test
    public void addQuestionsToSurvey() {
        // step 1. expected, url
        String url = "/surveys/Survey1/questions";

        // step 2. Questioin
        Question question = new Question("DOESN'T MATTER",
                "Smallest Number",
                "1",
                Arrays.asList("1", "2", "3", "4"));

        // step 2. Request & ResponseEntity
        ResponseEntity<String> response = restTemplate.exchange(
                createUrl(url),
                HttpMethod.POST,
                new HttpEntity<>(question, headers),
                String.class);

        // step assert
        assertThat(response.getHeaders().get(HttpHeaders.LOCATION).get(0),
                containsString("/surveys/Survey1/questions/"));

    }

}