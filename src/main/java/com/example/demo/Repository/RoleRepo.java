package com.example.demo.Repository;

import com.example.demo.Classes.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role, Long>{

    Role findByRole(String role);
}
