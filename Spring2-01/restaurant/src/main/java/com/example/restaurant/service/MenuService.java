package com.example.restaurant.service;

import com.example.restaurant.model.MenuItem;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    private final List<MenuItem> items = new ArrayList<>();

    @PostConstruct
    public void init() {
        items.add(new MenuItem(1, "Margherita Pizza", 8.99));
        items.add(new MenuItem(2, "Caesar Salad", 6.49));
        items.add(new MenuItem(3, "Spaghetti Bolognese", 10.99));
    }

    public List<MenuItem> getAllItems() {
        return items;
    }
}
