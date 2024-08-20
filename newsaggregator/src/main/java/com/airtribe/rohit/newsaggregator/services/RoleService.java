package com.airtribe.rohit.newsaggregator.services;

import com.airtribe.rohit.newsaggregator.role.RoleRepository;
import com.airtribe.rohit.newsaggregator.role.Role;
import com.airtribe.rohit.newsaggregator.user.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@NoArgsConstructor
@AllArgsConstructor
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role createRole(String name){
        Role role = Role.builder().name(name).build();
        return repository.save(role);
    }

    public String assignRole(User user,String roleName){
        Role role = repository.findByName(roleName);

        user.setRole(role);
        return "success";
    }

    public Role getRoleByName(String user) {
        return repository.findByName(user);
    }

    public List<Role> getRoles() {
        return repository.findAll();
    }
}
