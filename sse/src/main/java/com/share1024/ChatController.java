package com.share1024;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: 叶胜
 * @createTime: 2024-12-23 16:35
 **/
@CrossOrigin
@RestController
@RequestMapping("/chat")
public class ChatController {

    @PostMapping("/ask")
    public String ask(@RequestParam String question) {
        // 模拟接收问题
        System.out.println("Received question: " + question);
        return "Question received: " + question;
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter stream() {
        SseEmitter emitter = new SseEmitter();
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    emitter.send("Reply " + i + ": This is message " + i);
                    TimeUnit.SECONDS.sleep(1); // 模拟间隔
                }
                emitter.complete(); // 完成推送
            } catch (IOException | InterruptedException e) {
                emitter.completeWithError(e); // 处理错误
            }
        }, 0, 1, TimeUnit.SECONDS);

        return emitter;
    }
}
