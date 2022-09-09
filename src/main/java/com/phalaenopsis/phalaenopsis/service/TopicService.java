package com.phalaenopsis.phalaenopsis.service;

import com.phalaenopsis.phalaenopsis.domain.Topic;

import java.util.List;

public interface TopicService {
    public List<Topic> getAllTopics();
    public List<Topic> getTopicsWithKeyword(String keyword);
    public Topic CreateTopic(String Name, String Description, String Url, List<String> keywords);
}
