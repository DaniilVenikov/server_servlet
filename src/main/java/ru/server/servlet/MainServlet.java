package ru.server.servlet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.server.config.JavaConfig;
import ru.server.controller.PostController;
import ru.server.service.PostService;

public class MainServlet {
    public static void main(String[] args) {
        final var context = new AnnotationConfigApplicationContext(JavaConfig.class);

        final var controller = context.getBean("postController");
        final var service = context.getBean("postService");
        final var repository = context.getBean("postRepository");
    }
}
