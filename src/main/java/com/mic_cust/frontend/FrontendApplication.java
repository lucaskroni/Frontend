package com.mic_cust.frontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;

@SpringBootApplication
public class FrontendApplication {

    private final TemplateEngine templateEngine;

    public FrontendApplication(final ServletContext context) {
        super();
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(context);
        resolver.setCacheable(false);

        this.templateEngine =new TemplateEngine();
        this.templateEngine.setTemplateResolver(resolver);
    }

    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "true");
        SpringApplication.run(FrontendApplication.class, args);

    }

}
