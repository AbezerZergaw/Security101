package com.example.demo.Repository;

import com.example.demo.Classes.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
