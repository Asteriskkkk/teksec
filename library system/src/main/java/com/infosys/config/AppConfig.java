package com.infosys.config;

import com.infosys.model.Book;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("com.infosys")
@PropertySource("classpath:application.properties")
public class AppConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    @Bean
    public List<Book> bookList() {
        return Arrays.asList(
                new Book("Book1", "Author1", "Fiction"),
                new Book("Book2", "Author2", "Fiction"),
                new Book("Book3", "Author3", "Science")
        );
    }
}
