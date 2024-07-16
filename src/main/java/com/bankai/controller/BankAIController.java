package com.bankai.controller;

import com.bankai.model.Payment;
import com.bankai.service.BankAIService;
import jakarta.annotation.PostConstruct;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.QuestionAnswerAdvisor;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class BankAIController {

    private final ChatClient chatClient;


    public BankAIController(ChatClient.Builder builder, VectorStore vectorStore) {
        this.chatClient = builder.
                defaultAdvisors(new QuestionAnswerAdvisor(vectorStore, SearchRequest.defaults())).build();

    }

    @Autowired
    private BankAIService bankAIService;

    @PostMapping("/add")
    public ResponseEntity<String> processData(@RequestBody Payment payment) throws IOException {
        return new ResponseEntity<>(bankAIService.prepareFile(payment), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public String ai(@RequestParam("message")String message){

        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }


}
