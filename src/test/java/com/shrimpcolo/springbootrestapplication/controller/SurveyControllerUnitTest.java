package com.shrimpcolo.springbootrestapplication.controller;


import com.shrimpcolo.springbootrestapplication.service.SurveyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 单元测试
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerUnitTest {

    // Mock @Autowired
    @MockBean
    private SurveyService surveyService;

    @Test
    public void testMethod(){
        // step 1. use this specific data
        // step 2. expect this response
    }
}