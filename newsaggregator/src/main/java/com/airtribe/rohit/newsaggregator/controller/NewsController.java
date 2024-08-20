package com.airtribe.rohit.newsaggregator.controller;


import com.airtribe.rohit.newsaggregator.models.newsapi.NewsResponse;
import com.airtribe.rohit.newsaggregator.services.NewsApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController

public class NewsController {

    private final NewsApiService newsApiService;

    public NewsController(NewsApiService newsapi){
        newsApiService  = newsapi;
    }

    @GetMapping("/news")
    public Mono<NewsResponse> getNews(@RequestParam String q,
                        @RequestParam String fromdate,
                        @RequestParam String todate,
                        @RequestParam String sortby
                                ){
        return newsApiService.getNews(q,fromdate,todate,sortby);
    }


}
