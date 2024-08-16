package com.airtribe.rohit.newsaggregator.repository;


import com.airtribe.rohit.newsaggregator.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName(String  roleName);
}
