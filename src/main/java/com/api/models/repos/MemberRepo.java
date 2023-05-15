package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Member;
import com.api.models.entities.User;

import java.util.List;

import jakarta.transaction.Transactional;

public interface MemberRepo extends JpaRepository<Member, String> {

    @Query("SELECT generateIdMember FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdMemberByGenereateTabel(Integer id);

    @Query("SELECT u from _user u WHERE u.member = ?1")
    public List<User> findUserMember(Member member);

    @Query("select m from Member m where m.nama LIKE ?1%")
    public List<Member> findByNama(String nama);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdMember = ?1 WHERE gr.id = ?2")
    public Integer updateGenereteIdByGenerateTabel(Integer counter, Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE _user u SET u.passwordLogin = ?1 WHERE u.member = ?2")
    public Integer updatePassword(String password, Member member);

    @Query("select m from Member m where m.status = 'A' and m.id = ?1")
    public Member findMemberByIdAktif(String id);

}
