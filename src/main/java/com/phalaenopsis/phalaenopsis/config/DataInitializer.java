package com.phalaenopsis.phalaenopsis.config;


import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.service.TopicService;
import com.phalaenopsis.phalaenopsis.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    private final UserService userService;

    private final TopicService service;

    public DataInitializer(UserService userService, TopicService service) {
        this.userService = userService;
        this.service = service;
    }


    @PostConstruct
    public void initData() {
        this.userService.create("user" + 0, "pass" + 0, "pass" + 0, "ROLE_USER", "Name", "Surname");
//        for (int i = 1; i < 6; i++) {
//            this.userService.create("user" + i, "pass" + i, "USER", "Name of user " + i, "Surname of user" + i);
//        }

        List<String> keywords = new ArrayList<>();
//keywords.add("корен");keywords.add("орхидеја");

        //air roots
        keywords.add("воздушни");keywords.add("воздушен");keywords.add("корења");keywords.add("боја");keywords.add("корен");keywords.add("орхидеја");
        this.service.CreateTopic("Воздушни корења", "Како да се грижите околу вашите воздушни корења",
                "/roots_air", keywords);
        keywords.clear();

        //black roots
        keywords.add("црни");keywords.add("црн");keywords.add("корења");keywords.add("боја");keywords.add("корен");keywords.add("орхидеја");
        this.service.CreateTopic("Црни корења", "Што да направите доколку вашата орхидеја има црни корења",
                "/roots_black", keywords);
        keywords.clear();

        //white roots
        keywords.add("кафени");keywords.add("кафен");keywords.add("кафеав");keywords.add("кафеави");keywords.add("корења");keywords.add("боја");keywords.add("корен");keywords.add("орхидеја");
        this.service.CreateTopic("Кафеави корења", "Што да направите доколку вашата орхидеја има кафеави корења",
                "/roots_brown", keywords);
        keywords.clear();

        //green roots
        keywords.add("зелени");keywords.add("зелен");keywords.add("корења");keywords.add("боја");keywords.add("корен");keywords.add("орхидеја");
        this.service.CreateTopic("Зелени корења", "Што значи доколку вашата орхидеја има зелени корења",
                "/roots_green", keywords);
        keywords.clear();

        //yellow roots
        keywords.add("жолти");keywords.add("жолт");keywords.add("корења");keywords.add("боја");keywords.add("корен");keywords.add("орхидеја");
        this.service.CreateTopic("Жолти корења", "Што значи доколку вашата орхидеја има жолти корења",
                "/roots_yellow", keywords);
        keywords.clear();

        //white roots
        keywords.add("бел");keywords.add("бели");keywords.add("корења");keywords.add("боја");keywords.add("корен");keywords.add("орхидеја");
        this.service.CreateTopic("Бели корења", "Што да направите доколку вашата орхидеја има бели корења",
                "/roots_white", keywords);
        keywords.clear();



        //leaves
        keywords.add("лист");keywords.add("листови");keywords.add("листовите");keywords.add("исушени");keywords.add("пожолтен");keywords.add("орхидеја");
        keywords.add("пожолтени");
        this.service.CreateTopic("Листови", "Како да се грижите за листовите на вашата орхидеја",
                "/leaves", keywords);
        keywords.clear();


        //stem
        keywords.add("стебло");keywords.add("стеблото");keywords.add("стеблата");keywords.add("скршено");keywords.add("скршени");keywords.add("орхидеја");
        keywords.add("прачка");keywords.add("светлина");
        this.service.CreateTopic("Стебло", "Како да се грижите за стеблото на вашата орхидеја",
                "/stem", keywords);
        keywords.clear();

        //flowers
        keywords.add("цвет");keywords.add("цветови");keywords.add("цветовите");keywords.add("исушени");keywords.add("боја");keywords.add("орхидеја");
        this.service.CreateTopic("Цветови", "Како да се грижите за цветовите на вашата орхидеја",
                "/flowers", keywords);
        keywords.clear();

    }
}
