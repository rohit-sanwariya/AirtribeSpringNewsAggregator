package com.airtribe.rohit.newsaggregator.controller;


import com.airtribe.rohit.newsaggregator.services.NewsApiService;
import com.airtribe.rohit.newsaggregator.services.PreferencesService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class NewsController {

    private final NewsApiService newsApiService;
    private final PreferencesService preferencesService;

    public NewsController(NewsApiService newsapi, PreferencesService preferencesService){
        newsApiService  = newsapi;
        this.preferencesService = preferencesService;
    }

    @GetMapping("/news")
    public String getNews(Authentication authentication,
                        @RequestParam String fromdate,
                          @RequestParam String todate,
                          @RequestParam String sortby
                                ){
        String q = String.join("+",preferencesService.getPreferencesByUsername(authentication.getName()).stream().map(x -> x.getName()).toList());

        return newsApiService.getNews(q,fromdate,todate,sortby).block();
    }


}
