package com.cougartalk.forum.controllers;

import com.cougartalk.forum.entities.Topic;
import com.cougartalk.forum.entities.User;
import com.cougartalk.forum.repositories.AnswerRepository;
import com.cougartalk.forum.repositories.TopicRepository;
import com.cougartalk.forum.repositories.UserRepository;

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

@Controller
public class ProfileController {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final AnswerRepository answerRepository;
    private static final String DATE_FORMATTER= "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

    @Autowired
    public ProfileController(UserRepository userRepository, TopicRepository topicRepository, AnswerRepository answerRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("profile")
    public String displayMyProfile(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();

        User user = userRepository.getUserByUsername(username);
        Long points = userRepository.getPoints(user.getId());

        int numberOfTopics = topicRepository.countTopicsByUser_Id(user.getId());
        int numberOfAnswers = answerRepository.countAnswersByUser_Id(user.getId());
        int numberOfHelped = answerRepository.countAnswersByUser_IdAndUseful(user.getId(), true);

        model.addAttribute("user", user);
        model.addAttribute("points", points);
        model.addAttribute("numberOfTopics", numberOfTopics);
        model.addAttribute("numberOfAnswers", numberOfAnswers);
        model.addAttribute("numberOfHelped", numberOfHelped);
        return "profile";
    }

    @GetMapping("profile/{id}")
    public String displayProfileById(@PathVariable String id, Model model) {
        User user = userRepository.getUserById(id);
        Long points = userRepository.getPoints(user.getId());

        int numberOfTopics = topicRepository.countTopicsByUser_Id(id);
        int numberOfAnswers = answerRepository.countAnswersByUser_Id(id);
        int numberOfHelped = answerRepository.countAnswersByUser_IdAndUseful(id, true);

        model.addAttribute("user", user);
        model.addAttribute("points", points);
        model.addAttribute("numberOfTopics", numberOfTopics);
        model.addAttribute("numberOfAnswers", numberOfAnswers);
        model.addAttribute("numberOfHelped", numberOfHelped);

        return "profile";
    }

    @PostMapping("profile")
    public View addTask(@RequestParam("category") String category, @RequestParam("title") String title,
                        @RequestParam("content") String content,
                        @RequestParam("id_user") String id_user, HttpServletRequest request) {
        Topic topic = new Topic(null);
        topic.setCategory(category);

        topic.setContent(content);
        topic.setTitle(title);
        topic.setCreatedDate(LocalDateTime.now().format(formatter).toString());
        topic.setUserId(id_user);
        topic.setUsername(userRepository.getUserById(id_user).getUsername());

        topicRepository.save(topic);
        String contextPath = request.getContextPath();
        return new RedirectView(contextPath + "/profile");
    }
}