package com.example.librarysystem;

import com.example.librarysystem.component.Library;
import com.example.librarysystem.config.LibraryConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LibrarySystemApplication {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LibraryConfig.class)) {
            Library library = context.getBean(Library.class);

            printBorrowResult(library, "Technology", 2);
            printBorrowResult(library, "Science", 6);
            printBorrowResult(library, "Comics", 1);
        }
    }

    private static void printBorrowResult(Library library, String category, int requestedCount) {
        int borrowResult = library.borrowBooks(category, requestedCount);
        System.out.println("Borrowing " + requestedCount + " books from category '" + category + "'");

        if (borrowResult >= 0) {
            int remainingCount = library.getRemainingCount(category);
            System.out.println("Remaining count in category '" + category + "': " + remainingCount);
        } else {
            System.out.println("Error code: " + borrowResult);
        }

        System.out.println("----------------------------------");
    }
}
