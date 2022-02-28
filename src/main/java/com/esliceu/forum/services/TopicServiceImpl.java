package com.esliceu.forum.services;

import com.esliceu.forum.models.Topic;
import com.esliceu.forum.repositories.TopicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepo topicRepo;

    @Override
    public void CreateTopic(Topic topic) {
        topicRepo.save(topic);
    }

    @Override
    public List <Topic> getAllByIdCategoria(int id) {

        return topicRepo.findAllByCategoriaId(id);
    }

    @Override
    public Topic getTopicById(int idtopic) {
        return topicRepo.getById(idtopic);
    }
}
