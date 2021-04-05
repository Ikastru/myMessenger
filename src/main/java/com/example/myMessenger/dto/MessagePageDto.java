package com.example.myMessenger.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.example.myMessenger.domain.Message;
import com.example.myMessenger.domain.Views;

import java.util.List;

//@Data
//@Getter
//@Setter
//@AllArgsConstructor
@JsonView(Views.FullMessage.class)
public class MessagePageDto {
    private List<Message> messages;
    private int currentPage;
    private int totalPages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public MessagePageDto(List<Message> messages, int currentPage, int totalPages) {
        this.messages = messages;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
