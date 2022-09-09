package com.phalaenopsis.phalaenopsis.MutationTesting;

import com.phalaenopsis.phalaenopsis.domain.Certificate;
import com.phalaenopsis.phalaenopsis.domain.Topic;
import com.phalaenopsis.phalaenopsis.domain.User;
import com.phalaenopsis.phalaenopsis.repository.TopicRepository;
import com.phalaenopsis.phalaenopsis.service.TopicService;
import com.phalaenopsis.phalaenopsis.service.impl.CertificateServiceImpl;
import com.phalaenopsis.phalaenopsis.service.impl.TopicServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TopicServiceImplMutationTest {

    @Mock
    private TopicRepository topicRepository;
    private TopicService topicService;
    private List<Topic> topics;
    private List<String> keywords;

    @BeforeAll
    public void init(){
        MockitoAnnotations.openMocks(this);
        keywords = new ArrayList<>();
        keywords.add("keyword1");
        keywords.add("keyword2");
        keywords.add("keyword3");


        topics = new ArrayList<>();
        topics.add(new Topic("name1", "desc1", "url1", keywords));
        topics.add(new Topic("name2", "desc1", "url1", keywords));
        topics.add(new Topic("name3", "desc1", "url1", keywords));
        topics.add(new Topic("name4", "desc1", "url1", keywords));
        when(topicRepository.findAll()).thenReturn(topics);
        when(topicRepository.findAllByKeywords("2")).thenReturn(new ArrayList<>());

        Topic topic = topics.get(0);
        when(topicRepository.save(topic)).thenReturn(topic);

        topicService = Mockito.spy(new TopicServiceImpl(topicRepository));
    }

    @Test
    public void testFindAll(){
        Assert.assertEquals(topicService.getAllTopics(), topics);
    }


    @Test
    public void testGetTopicsWithKeyword(){
        String keyword = "2";
        Assert.assertEquals(topicService.getTopicsWithKeyword(keyword), topics.stream().filter(x->x.Name.contains(keyword)).collect(Collectors.toList()));
    }

    @Test
    public void creteTopic(){
        Topic topic = topics.get(0);
        Assert.assertEquals(topicService.CreateTopic(topic.Name, topic.Description, topic.Url, keywords), topic);
    }
}
