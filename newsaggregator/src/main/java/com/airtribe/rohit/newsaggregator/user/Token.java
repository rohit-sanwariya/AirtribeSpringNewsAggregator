package com.airtribe.rohit.newsaggregator.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    private String token;
    private Date expiresAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name = "userId")
    @JsonIgnore
    private User user;





}
