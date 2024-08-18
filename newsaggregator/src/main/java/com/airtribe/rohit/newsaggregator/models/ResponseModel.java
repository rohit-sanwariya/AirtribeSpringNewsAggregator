package com.airtribe.rohit.newsaggregator.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@Builder
public class ResponseModel {
    private HttpStatus httpStatus;
    private Object data;
    private String message;
}
