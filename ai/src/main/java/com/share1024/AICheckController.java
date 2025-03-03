package com.share1024;

import org.apache.groovy.util.Maps;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @description:
 * @author: 叶胜
 * @createTime: 2025-03-03 15:04
 **/
@RestController
public class AICheckController {


    @Autowired
    private OpenAiChatModel openAiChatModel;

    private ChatClient client;

    public AICheckController(ChatClient.Builder builder) {
        this.client = builder.defaultSystem("你是一个小红书文案编辑者，按照小红书风格输出内容").build();
    }

    @GetMapping("/ai/chat")
    public String chat(@RequestParam(value ="message") String message) {
        SystemMessage systemMessage = new SystemMessage("你是一个小说写手，可以充满想象力的写100字的小说");
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(systemMessage,userMessage);
        return openAiChatModel.call(prompt).getResult().getOutput().getText();
    }


    @GetMapping(value = "/ai/chatFlux",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatFlux(@RequestParam(value ="message") String message) {
        SystemMessage systemMessage = new SystemMessage("你是一个小说写手，可以充满想象力的写100字的小说");
        UserMessage userMessage = new UserMessage(message);
        Prompt prompt = new Prompt(systemMessage,userMessage);
        return openAiChatModel.stream(prompt).map(item->item.getResult().getOutput().getText());
    }
}
