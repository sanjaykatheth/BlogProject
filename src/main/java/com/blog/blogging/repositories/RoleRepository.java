package com.blog.blogging.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.blogging.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
