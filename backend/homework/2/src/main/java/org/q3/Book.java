package org.q3;

import java.util.logging.Logger;

public class Book {
    static Logger logger = Logger.getLogger(String.valueOf(Book.class));

    private String title;
    private String author;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getRatingsCount() {
        return this.ratingsCount;
    }
    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }
    public double getAverageRating() {
        return this.averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }
    public int getPublicationYear() {
        return this.publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void printValues(){
        logger.info("Title : "+getTitle());
        logger.info("Author : "+getAuthor());
        logger.info("Publication Year : "+getPublicationYear());
        logger.info("Average Rating : "+getAverageRating());
        logger.info("Rating Count : "+getRatingsCount());
        logger.info("Image URL : "+getImageUrl());
    }
}
