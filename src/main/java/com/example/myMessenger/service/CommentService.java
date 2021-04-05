package com.example.myMessenger.service;

import com.example.myMessenger.domain.Comment;
import com.example.myMessenger.domain.User;
import com.example.myMessenger.domain.Views;
import com.example.myMessenger.dto.EventType;
import com.example.myMessenger.dto.ObjectType;
import com.example.myMessenger.repo.CommentRepo;
import com.example.myMessenger.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}

