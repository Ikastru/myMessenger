package com.example.myMessenger.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.myMessenger.domain.Message;
import com.example.myMessenger.domain.User;
import com.example.myMessenger.domain.Views;
import com.example.myMessenger.dto.MessagePageDto;
import com.example.myMessenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("message")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 3;

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.FullMessage.class)
    public MessagePageDto list(
            @AuthenticationPrincipal User user,
            @PageableDefault(size = MESSAGES_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return messageService.findForUser(pageable, user);
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    @JsonView(Views.FullMessage.class)
    public Message create(
            @RequestBody Message message,
            @AuthenticationPrincipal User user
    ) throws IOException {
        return messageService.create(message, user);
    }

    @PutMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) throws IOException {
        return messageService.update(messageFromDb, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }
}

    //    Старый метод для работы с Map, а не с базой данных
//    public Map<String,String> getMessage(String id){
//        return messages.stream()
//                .filter(message->message.get("id").equals(id))
//                .findFirst()
//                .orElseThrow(NotFoundException::new);
//    }

