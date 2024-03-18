package com.websocket.controllers;

import java.io.IOException;
import java.time.LocalTime;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.websocket.entity.Message;
import com.websocket.entity.OutputMessage;

@Controller
public class SocketController {
    
    @MessageMapping("/trade")
    @SendTo("/topic/trade")
    public OutputMessage  trade(final Message message) throws Exception {
        return new OutputMessage(message.getName(),message.getEmail());
    }
    

}
