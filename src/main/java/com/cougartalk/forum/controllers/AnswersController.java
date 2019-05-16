package com.cougartalk.forum.controllers;

import com.cougartalk.forum.repositories.AnswerRepository;
import com.cougartalk.forum.entities.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AnswersController {

    private final AnswerRepository answerRepository;

    @Autowired
    public AnswersController(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    @GetMapping("answers/{id}")
    public String displayAnswersByUser(@PathVariable String id, Model model) {
        List<Answer> answers = answerRepository.findAnswerByUser_IdOrderByCreatedDateDesc(id);
        model.addAttribute("answers", answers);
        return "answers";
    }

    @GetMapping("answers/useful/{id}")
    public String displayUsefulAnswersByUser(@PathVariable String id, Model model) {
        List<Answer> answers = answerRepository.findAnswerByUser_IdAndUsefulOrderByCreatedDateDesc(id, true);
        model.addAttribute("answers", answers);
        return "answers";
    }
}
