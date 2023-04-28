package com.api.models.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.token.Token;

public interface TokenRepo extends JpaRepository<Token, Integer> {
    @Query("SELECT t FROM Token t INNER JOIN _user u ON t.user.id = u.id where u.id = :userId and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokensByUser(Long userId);

    Optional<Token> findByToken(String token);
}
