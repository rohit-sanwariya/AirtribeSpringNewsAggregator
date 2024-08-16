package com.airtribe.rohit.newsaggregator.controller;

import com.airtribe.rohit.newsaggregator.role.Role;
import com.airtribe.rohit.newsaggregator.services.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/auth/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/get/all")
    public List<Role> getRoles(){

        return roleService.getRoles();
    }
    @GetMapping("{name}")
    public Role createRole(@PathVariable("name") String name){

        return roleService.createRole(name);
    }



}
