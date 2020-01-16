package com.test.permohonanCuti.permohonanCuti.repository;

import com.test.permohonanCuti.permohonanCuti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByUserId(Long userId);
}
