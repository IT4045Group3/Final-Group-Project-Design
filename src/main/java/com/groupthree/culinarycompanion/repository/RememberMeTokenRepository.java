package com.groupthree.culinarycompanion.repository;

import com.groupthree.culinarycompanion.entity.RememberMeToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, Integer> {
    RememberMeToken findByToken(String token);
}
