package com.cwilliam.task.manager.repositories;

import com.cwilliam.task.manager.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
