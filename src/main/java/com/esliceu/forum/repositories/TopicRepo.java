package com.esliceu.forum.repositories;

import com.esliceu.forum.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepo extends JpaRepository <Topic, Integer> {

    List <Topic> findAllByCategoriaId(int id);
}
