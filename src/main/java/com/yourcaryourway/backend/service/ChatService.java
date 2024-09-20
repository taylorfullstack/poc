package com.yourcaryourway.backend.service;

import com.yourcaryourway.backend.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final SimpMessagingTemplate messagingTemplate;

    public ChatService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendMessageToUser(String userId, ChatMessage message) {
        messagingTemplate.convertAndSendToUser(userId, "/queue/messages", message);
    }
}
