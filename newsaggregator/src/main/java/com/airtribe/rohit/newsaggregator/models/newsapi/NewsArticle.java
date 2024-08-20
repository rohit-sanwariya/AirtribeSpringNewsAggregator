package com.airtribe.rohit.newsaggregator.models.newsapi;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsArticle {
    private NewSource source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;
}
