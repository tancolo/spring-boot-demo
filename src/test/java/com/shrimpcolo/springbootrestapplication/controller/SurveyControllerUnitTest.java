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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
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


}