package com.example.chat.chat;

import org.springframework.stereotype.Controller;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.handler.annotation.MessageMapping;

@Controller
public class ChatController {
    //INFO: @MessageMapping maps the method to the specified destination.
    @MessageMapping("/chat.sendMessage")
    //INFO: @SendTo broadcasts the return value to all subscribers of the specified destination. in WebSocketConfig:
    @SendTo("/topic/public")
    public ChatMessage sendMessage(
        @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(
        @Payload ChatMessage chatMessage,
        //INFO: SimpMessageHeaderAccessor is a helper class to access the headers and session attributes of a message.
        SimpMessageHeaderAccessor headerAccessor
    ) {
        //INFO: Add username in web socket session.
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

}
