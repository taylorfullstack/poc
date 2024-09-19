package com.yourcaryourway.backend.controller;

import com.yourcaryourway.backend.model.ChatMessage;
import com.yourcaryourway.backend.service.ChatService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/chat")
    public void processMessage(ChatMessage chatMessage) {
        chatService.sendMessageToUser(chatMessage.getRecipientId(), chatMessage);
    }
}
