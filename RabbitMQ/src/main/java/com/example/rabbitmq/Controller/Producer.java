package com.example.rabbitmq.Controller;

import com.example.rabbitmq.Service.MessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Producer {
    private final MessageService messageService;
    public Producer(MessageService messageService){
        this.messageService = messageService;
    }
    @GetMapping("/produce/{message}")
    public String produce(@PathVariable String message){
        messageService.produce(message);
        return "发送消息";
    }
}
