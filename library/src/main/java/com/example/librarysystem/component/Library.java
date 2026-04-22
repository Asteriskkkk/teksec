package com.example.librarysystem.component;

import com.example.librarysystem.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Library {

    private final List<Book> books;

    @Value("#{${library.category.counts}}")
    private Map<String, Integer> categoryCounts;

    public Library(List<Book> books) {
        this.books = books;
    }

    public int borrowBooks(String category, int requestedCount) {
        Integer availableCount = categoryCounts.get(category);
        if (availableCount == null) {
            return -2;
        }

        if (requestedCount > availableCount) {
            return -1;
        }

        int remainingCount = availableCount - requestedCount;
        categoryCounts.put(category, remainingCount);
        return remainingCount;
    }

    public int getRemainingCount(String category) {
        return categoryCounts.getOrDefault(category, -2);
    }

    public List<Book> getBooks() {
        return books;
    }
}
