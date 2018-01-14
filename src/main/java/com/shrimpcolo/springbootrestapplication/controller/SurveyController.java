package com.shrimpcolo.springbootrestapplication.controller;

import com.shrimpcolo.springbootrestapplication.model.Question;
import com.shrimpcolo.springbootrestapplication.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    @Autowired
    private SurveyService service;

    @GetMapping("/{surveyId}/questions")
    public List<Question> retrieveQuestionsForSurvey(@PathVariable String surveyId) {
        System.out.println("TANHQ===>  surveyId = " + surveyId);
        return service.retrieveQuestions(surveyId);
    }

    @PostMapping("/{surveyId}/questions")
    public ResponseEntity<Void> addQuestionsToSurvey(@PathVariable String surveyId, @RequestBody Question newQuestion) {
        System.out.println("TANHQ===>  surveyId = " + surveyId + ", question = " + newQuestion);
        /**
         *
         * id: "Question4",
         description: "some description",
         correctAnswer: "correct Answer",
         options: [
         "India",
         "Russia",
         "correct Answer",
         "China"
         ]
         *
         */
        Question question = service.addQuestion(surveyId, newQuestion);
        if (question == null)
            return ResponseEntity.noContent().build();

        //URI -> /surveys/{surveyId}/questions/{questionId}/
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(question.getId()).toUri();

        System.out.println("TANHQ===> location = " + location.toString());

        //Status
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{surveyId}/questions/{questionId}")
    public Question retrieveQuestionById(@PathVariable String surveyId, @PathVariable String questionId) {
        System.out.println("TANHQ===>  surveyId = " + surveyId + ",  questionId = " + questionId);

        return service.retrieveQuestion(surveyId, questionId);
    }


}