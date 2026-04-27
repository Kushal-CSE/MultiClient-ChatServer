package com.makaut_cse.sumit_kushal.multiclient_chatserver.repository;

import com.makaut_cse.sumit_kushal.multiclient_chatserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);


}