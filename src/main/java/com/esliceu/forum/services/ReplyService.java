package com.esliceu.forum.services;

import com.esliceu.forum.models.Reply;

public interface ReplyService {
    void CreateReply(Reply reply);

    Reply getReplyById(int idreply);

    void deleteReply(Reply reply);
}
