package com.airtribe.rohit.newsaggregator.models.newsapi;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewsResponse {
    private String status;
    private int  totlaResults;
    private List<NewsArticle> articles;
}
