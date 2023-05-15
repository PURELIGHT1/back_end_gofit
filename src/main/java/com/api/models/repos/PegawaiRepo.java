package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.api.models.entities.Pegawai;
import com.api.models.entities.User;
import com.api.models.entities.token.Token;

import jakarta.transaction.Transactional;

public interface PegawaiRepo extends JpaRepository<Pegawai, String> {

    @Query("SELECT p FROM pegawai p WHERE p.email = ?1")
    public List<Pegawai> findByEmail(String email);

    @Query("SELECT generateIdPegawai FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdPegawaiByGenereateTabel(Integer id);

    @Query("SELECT u from _user u WHERE u.pegawai = ?1")
    public List<User> findUserPegawai(Pegawai pegawai);

    @Query("SELECT t FROM Token t WHERE t.user = ?1")
    public List<Token> findTokenUser(User user);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdPegawai = ?1 WHERE gr.id = ?2")
    public Integer updateGenereteIdByGenerateTabel(Integer counter, Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE _user u SET u.passwordLogin = ?1 WHERE u.pegawai = ?2")
    public Integer updatePassword(String password, Pegawai member);
}
