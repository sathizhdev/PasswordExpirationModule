package com.example.loginservice.Repository;

import com.example.loginservice.Modal.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface usersRepository extends CrudRepository<User,String> {

    User findByUserName(String userName);

}