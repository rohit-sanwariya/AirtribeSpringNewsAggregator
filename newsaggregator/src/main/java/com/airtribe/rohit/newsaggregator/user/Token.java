package com.airtribe.rohit.newsaggregator.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;


import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor

@NoArgsConstructor

public class Token {
    @Id
    private long id;



    private String token;
    private Date expiresAt;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(nullable = false,name = "userId")
    private User user;





}
