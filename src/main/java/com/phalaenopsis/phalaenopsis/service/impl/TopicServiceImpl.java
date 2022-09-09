package com.phalaenopsis.phalaenopsis.service.impl;

import com.phalaenopsis.phalaenopsis.domain.Topic;
import com.phalaenopsis.phalaenopsis.repository.TopicRepository;
import com.phalaenopsis.phalaenopsis.service.TopicService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }


    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public List<Topic> getTopicsWithKeyword(String keyword) {
        List<Topic> topics = topicRepository.findAll().stream().filter(x->x.Name.toLowerCase().contains(keyword.toLowerCase())).collect(Collectors.toList());
        topics.addAll(topicRepository.findAllByKeywords(keyword));
        return topics.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public Topic CreateTopic(String Name, String Description, String Url, List<String> keywords) {
        return topicRepository.save(new Topic(Name, Description, Url, keywords));
    }
}
