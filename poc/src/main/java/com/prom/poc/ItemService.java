package com.prom.poc;

import io.micrometer.core.instrument.Gauge;
import org.springframework.stereotype.Component;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ItemService {
    private static int bookOrderId = 0;
    private static int movieOrderId = 0;
    private Counter bookCounter = null;
    private Counter movieCounter = null;
    private AtomicInteger activeUsers = null;

    public ItemService(CompositeMeterRegistry meterRegistry) {
        bookCounter = meterRegistry.counter("order.books");
        movieCounter = meterRegistry.counter("order.movies");
        activeUsers = meterRegistry.gauge("number.of.active.users",new AtomicInteger(0));
        Random random = new Random();
        activeUsers.set(random.nextInt());
    }

    public Number fetchActiveUsers(){
        return 5;
    }

    public String orderBook() {
        bookOrderId += 1;
        bookCounter.increment();
        return new String("Ordered Book with id = " + bookOrderId);
    }

    public String orderMovie() {
        movieOrderId += 1;
        movieCounter.increment();
        return new String("Ordered Movie with id = " + movieOrderId);
    }
}
