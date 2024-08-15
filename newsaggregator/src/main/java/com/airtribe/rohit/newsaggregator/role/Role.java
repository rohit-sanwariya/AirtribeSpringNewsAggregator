package com.airtribe.rohit.newsaggregator.role;


import com.airtribe.rohit.newsaggregator.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;
import java.util.Set;

@Entity
public class Role {
    @Id
    private Long id;

    @Column(unique = true)
    private  String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

}
