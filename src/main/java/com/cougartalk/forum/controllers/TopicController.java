package com.cougartalk.forum.controllers;

import com.cougartalk.forum.entities.Topic;
import com.cougartalk.forum.repositories.AnswerRepository;
import com.cougartalk.forum.repositories.TopicRepository;
import com.cougartalk.forum.repositories.UserRepository;
import com.cougartalk.forum.entities.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class TopicController {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Autowired
    public TopicController(UserRepository userRepository, TopicRepository topicRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("topic/{id}")
    public String displayTopic(@PathVariable String id,  Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        String idUser = userRepository.getUserByUsername(username).getId();

        Topic topic = topicRepository.findTopicById(id);
        List<Answer> answers = answerRepository.findAnswerByTopic_Id(id);

        model.addAttribute("topic", topic);
        model.addAttribute("answers", answers);
        model.addAttribute("idUser", idUser);
        return "topic";
    }

    @PostMapping("topic/{id}")
    public View updateAnswer(@RequestParam String id_topic, @RequestParam String action, @RequestParam String id_answer,
                             @RequestParam(required = false) String state, HttpServletRequest request) {
        switch (action) {
            case "useful" :
                answerRepository.setUsefulForAnswer(!Boolean.valueOf(state), id_answer);
                break;
            case "delete" :
                answerRepository.deleteAnswerById(id_answer);
                break;
        }
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/topic/" + id_topic);
    }

    @PostMapping("topic")
    public View addAnswer(@RequestParam("content") String content,
                          @RequestParam("id_topic") String id_topic, @RequestParam("id_user") String id_user,
                          HttpServletRequest request) {
        Answer answer = new Answer(null);
        answer.setContent(content);
        answer.setCreatedDate(LocalDateTime.now().format(formatter));
        answer.setUseful(false);
        answer.setTopicId(id_topic);
        answer.setUserId(id_user);
        answer.setUsername(userRepository.getUserById(id_user).getUsername());
        answer.setTopicTitle(topicRepository.findTopicById(id_topic).getTitle());

        answerRepository.save(answer);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/topic/" + id_topic);
    }
}