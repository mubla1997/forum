package com.esliceu.forum.services;

import com.esliceu.forum.models.Reply;
import com.esliceu.forum.repositories.ReplyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService{

    @Autowired
    ReplyRepo replyRepo;

    @Override
    public void CreateReply(Reply reply) {
        replyRepo.save(reply);
    }

    @Override
    public Reply getReplyById(int idreply) {
        return replyRepo.getById(idreply);
    }

    @Override
    public void deleteReply(Reply reply) {
        replyRepo.delete(reply);
    }
}
