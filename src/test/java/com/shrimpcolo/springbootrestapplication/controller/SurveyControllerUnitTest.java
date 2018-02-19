package com.shrimpcolo.springbootrestapplication.controller;


import com.shrimpcolo.springbootrestapplication.model.Question;
import com.shrimpcolo.springbootrestapplication.service.SurveyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = SurveyController.class, secure = false) // 不使用security
public class SurveyControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    // Mock @Autowired
    @MockBean
    private SurveyService surveyService;

    @Test
    public void retrieveQuestionById() throws Exception {

        Question mockQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China"));

        Mockito.when(
                surveyService.retrieveQuestion(Mockito.anyString(), Mockito.anyString())).thenReturn(mockQuestion);

        String url = "/surveys/Survey1/questions/Question1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "{\"id\":\"Question1\",\"description\":\"Largest Country in the World\",\"correctAnswer\":\"Russia\",\"options\":[\"India\",\"Russia\",\"United States\",\"China\"]}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }


    @Test
    public void retrieveQuestionsForSurvey() throws Exception {
        // step 1. List<Question>
        List<Question> mockList = Arrays.asList(
                new Question("Question1", "First Alphabet", "A", Arrays.asList("A", "B", "C", "D")),
                new Question("Question2", "Last Alphabet", "Z", Arrays.asList("A", "X", "Y", "Z"))
        );
        System.out.println("TANHQ====> test retrieveQuestions...");

        // step 2. mockito
        Mockito.when(surveyService.retrieveQuestions(Mockito.anyString())).thenReturn(mockList);

        // step 3. RequestBuilder
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/surveys/Survey1/questions")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

//        String expected = "["
//                + "{id:Question1,description:First Alphabet,correctAnswer:A,options:[A,B,C,D]},"
//                + "{id:Question2,description:Last Alphabet,correctAnswer:Z,options:[A,X,Y,Z]}"
//                + "]";

        String expected = "[" +
                "{\"id\":\"Question1\",\"description\":\"First Alphabet\",\"correctAnswer\":\"A\",\"options\":[\"A\",\"B\",\"C\",\"D\"]}," +
                "{\"id\":\"Question2\",\"description\":\"Last Alphabet\",\"correctAnswer\":\"Z\",\"options\":[\"A\",\"X\",\"Y\",\"Z\"]}" +
                "]";

        // step 4. assert.
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    /**
     * test post method
     */
    @Test
    public void addQuestionsToSurvey() throws Exception {
        // url and new question
        String url = "/surveys/Survey1/questions";
        Question mockQuestion = new Question("1", "Smallest Number", "1", Arrays.asList("1", "2", "3", "4"));
        String questionJson = "{\"description\":\"Smallest Number\",\"correctAnswer\":\"1\",\"options\":[\"1\",\"2\",\"3\",\"4\"]}";

        // mockito.when
        Mockito.when(surveyService.addQuestion(Mockito.anyString(), Mockito.any(Question.class))).thenReturn(mockQuestion);

        // request and response
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .content(questionJson)
                .contentType(MediaType.APPLICATION_JSON);

        // MvcResult
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        // assert
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        assertEquals("http://localhost/surveys/Survey1/questions/1", response.getHeader(HttpHeaders.LOCATION));

    }
}