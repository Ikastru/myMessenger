package com.example.myMessenger.repo;

import com.example.myMessenger.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<User, String> {
}
