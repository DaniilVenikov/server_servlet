package ru.server.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.server.controller.PostController;
import ru.server.service.PostService;

public class MainServlet {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext("ru.server");

        final var repository = context.getBean("postRepository");
        final var service = context.getBean(PostService.class);
        final var controller = context.getBean(PostController.class);
    }
}
