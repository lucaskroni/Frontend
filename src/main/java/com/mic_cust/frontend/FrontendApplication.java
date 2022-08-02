package com.mic_cust.frontend;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;

@SpringBootApplication
public class FrontendApplication {

    private final TemplateEngine templateEngine;
    private static ConfigurableApplicationContext context;

    public FrontendApplication(final ServletContext context) {
        super();
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setCacheable(false);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(resolver);
    }

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        context = SpringApplication.run(FrontendApplication.class, args);

    }

}
