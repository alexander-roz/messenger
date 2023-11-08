package com.intellekta.message.controllers;

import com.intellekta.message.MessageApplication;
import com.intellekta.message.model.entities.MessageEntity;
import com.intellekta.message.model.repositories.MessageEntityRepository;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

public class MainController extends SpringBootServletInitializer {

    MessageEntityRepository messageEntityRepository;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(MessageApplication.class);
    }

    @RequestMapping(value = "/")
    public String login(Model model)
    {
        return "login";
    }

    @RequestMapping(value = "/index")
    public String index(Model model){
        ArrayList <MessageEntity> messages = new ArrayList<>(messageEntityRepository.findAll());
        model.addAttribute("messages", messages);
        model.addAttribute("messagesCount", messages.size());
        return "index";
    }
}
