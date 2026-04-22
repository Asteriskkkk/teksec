package com.example.movierecommendation.model;

public class Movie {
    private String id;
    private String title;
    private String genre;
    private double rating;

    public Movie(String id, String title, String genre, double rating) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return title + " (" + genre + ") - Rating: " + rating;
    }
}
