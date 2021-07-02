package com.github.mehdishahdoost.product.repository;

import com.github.mehdishahdoost.product.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String u);
}
