package com.example.mybooklistapi.event.handler;

import com.example.mybooklistapi.event.BookCreatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class BookCreatedListener {
    private static final Logger log = LogManager.getLogger(BookCreatedListener.class);

    @Async
    @EventListener
    public void handle(BookCreatedEvent e) {
        log.info("{} - book was created at {}", e.title(), LocalDateTime.now());
    }
}
