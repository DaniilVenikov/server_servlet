package ru.server;

import ru.server.controller.PostController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.server.service.PostService;

public class Main {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext("ru.server");

        final var repository = context.getBean("postRepository");
        final var service = context.getBean(PostService.class);
        final var controller = context.getBean(PostController.class);
    }
}
