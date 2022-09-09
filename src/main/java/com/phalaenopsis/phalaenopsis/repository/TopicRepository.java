package com.phalaenopsis.phalaenopsis.repository;

import com.phalaenopsis.phalaenopsis.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByKeywords(String keyword);
}
