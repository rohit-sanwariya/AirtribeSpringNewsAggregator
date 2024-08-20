package com.airtribe.rohit.newsaggregator.services;

import com.airtribe.rohit.newsaggregator.models.newsapi.NewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class NewsApiService {

    @Value("${newsapi.apikey}")
    private String apikey;

    @Value("${newsapi.baseurl")
    private String baseurl;

    private final WebClient webClient;

    public NewsApiService(WebClient.Builder webclient) {
        this.webClient = webclient.baseUrl(baseurl).build();
    }


    public Mono<NewsResponse> getNews(String query, String from, String to, String sortBy){
        return  webClient.get().uri(builder->
                        builder
                                .path("/everything")
                                .queryParam("q",query)
                                .queryParam("from", from)
                                .queryParam("to", to)
                                .queryParam("sortBy", sortBy)
                                .queryParam("apiKey", apikey)
                                .build())
                .retrieve()
                .bodyToMono(NewsResponse.class);
    }
}
