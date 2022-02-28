package com.esliceu.forum.services;

import com.esliceu.forum.models.Topic;

import java.util.List;

public interface TopicService {
    void CreateTopic(Topic topic);
    List <Topic> findAll();
    List<Topic> getAllByIdCategoria(int idCategoria);
    Topic getTopicById(int idtopic);
}
