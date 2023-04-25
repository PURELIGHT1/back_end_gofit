package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Member;

import jakarta.transaction.Transactional;

public interface MemberRepo extends JpaRepository<Member, String> {

    @Query("SELECT generateIdMember FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdMemberByGenereateTabel(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdMember = ?1 WHERE gr.id = ?2")
    public Integer updateGenereteIdByGenerateTabel(Integer counter, Integer id);
}
