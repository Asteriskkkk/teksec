package com.infosys;

/**
 * Hello world!
 */
import com.infosys.config.AppConfig;
import com.infosys.service.Library;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Library lib = context.getBean(Library.class);
        int result1 = lib.borrowBooks("Fiction", 1);
        System.out.println(result1);
        int result2 = lib.borrowBooks("Science", 2);
        System.out.println(result2);
        int result3 = lib.borrowBooks("Horror", 1);
        System.out.println(result3);
        context.close();
    }
}
