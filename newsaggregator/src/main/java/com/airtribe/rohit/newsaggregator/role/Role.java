package com.airtribe.rohit.newsaggregator.role;


import com.airtribe.rohit.newsaggregator.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "role_seq", sequenceName = "role_seq")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "role_seq")
    @SequenceGenerator(name = "role_seq", sequenceName = "role_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private  String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

}
