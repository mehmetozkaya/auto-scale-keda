package com.prom.poc;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Timed
public class HelloController {

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    @Timed("api")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/books")
    @Timed("books.api")
    public String orderBook() {
        return itemService.orderBook();
    }

    @PostMapping("/movies")
    @Timed("movies.api")
    public String orderMovie() {
        return itemService.orderMovie();
    }
}
