package com.api.implement.services;

import java.util.List;

import com.api.dto.UbahPasswordRequest;
import com.api.models.entities.Member;

public interface MemberService {

    List<Member> findAll();

    Member findByIdMember(String id);

    Member updateMember(String id, Member Member);

    Member updateMemberStatus(String id);

    Member createMember(Member Member);

    void deleteMember(String id);

    Member updateFotoMember(String id, String foto);

    Member updatePasswordMember(String id);

    Member ubahPasswordMember(String id, UbahPasswordRequest request);
}
