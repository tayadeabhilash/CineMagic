package com.scrumandcoke.movietheaterclub.configuration;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
@Slf4j
public class EventBusConfig {

    @Bean
    public EventBus eventBus() {

        final SubscriberExceptionHandler handler = (exception, context) ->
                log.warn("Async Event Bus: Subscriber: exception encountered: " + exception.getMessage(), exception);
        final RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> {
            if (!executor.isShutdown()) {
                log.warn("Async Event Bus: Executor: Rejected execution encountered, running on callee thread");
                r.run();
            } else {
                log.warn("Async Event Bus: Executor: rejected Execution encountered after executor was shutdown");
            }
        };

        final Executor executor = new ThreadPoolExecutor(5, 10, 1, TimeUnit.HOURS,
                new LinkedBlockingQueue<>(), rejectedExecutionHandler);

        return new AsyncEventBus(executor, handler);

    }
}

