package com.airtribe.rohit.newsaggregator.services;

import com.airtribe.rohit.newsaggregator.config.WebClientConfig;
import com.airtribe.rohit.newsaggregator.models.newsapi.NewsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;





@Service
public class NewsApiService {

    @Value("${newsapi.apikey}")
    private String apikey;

    @Value("${newsapi.baseurl}")
    private String baseurl;
    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);
    private final WebClient webClient;

    public NewsApiService(WebClient.Builder webclient) {
        this.webClient = webclient.baseUrl("https://newsapi.org").build();
    }


    public Mono<String> getNews(String query, String from, String to, String sortBy){
       return webClient.get().uri(builder ->
                builder
                        .path("/v2/everything")
                        .queryParam("q", query)
                        .queryParam("from", from)
                        .queryParam("to", to)
                        .queryParam("sortBy", sortBy)
                        .queryParam("apiKey", apikey)
                        .build()).retrieve()
         .bodyToMono(String.class);
    }
}
