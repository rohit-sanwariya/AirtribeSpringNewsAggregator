package com.airtribe.rohit.newsaggregator.controller;

import com.airtribe.rohit.newsaggregator.repository.RoleRepository;
import com.airtribe.rohit.newsaggregator.role.Role;
import com.airtribe.rohit.newsaggregator.services.RoleService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("{name}")
    public Role createRole(@PathVariable("name") String name){

        return roleService.createRole(name);
    }


}
