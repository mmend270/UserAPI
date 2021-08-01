package com.example.demo.userInfo;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserInfo a " + "SET a.enabled = TRUE WHERE a.email = ?1")
    int enableUserInfo(String email);
}
