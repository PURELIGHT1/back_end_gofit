package com.api.implement;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.exception.member.*;
import com.api.models.UserRole;
import com.api.models.entities.Member;
import com.api.models.entities.User;
import com.api.models.repos.MemberRepo;
import com.api.models.repos.UserRepo;
import com.api.services.MemberService;

@Service
public class MemberImpl implements MemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Member createMember(Member member) {
        Member memberDB = new Member();

        if (Objects.nonNull(member.getNama()) &&
                !"".equalsIgnoreCase(member.getNama())) {
            memberDB.setNama(member.getNama());
        } else {
            throw new MemberExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(member.getEmail()) &&
                !"".equalsIgnoreCase(member.getEmail())) {
            memberDB.setEmail(member.getEmail());
        } else {
            throw new MemberExceptionBadRequest("Email tidak boleh kosong");
        }

        if (Objects.nonNull(member.getAlamat()) &&
                !"".equalsIgnoreCase(member.getAlamat())) {
            memberDB.setAlamat(member.getAlamat());
        } else {
            throw new MemberExceptionBadRequest("Alamat tidak boleh kosong");
        }

        memberDB.setTglLahir(member.getTglLahir());
        memberDB.setNoHp(member.getNoHp());

        if (Objects.nonNull(member.getNoHp()) &&
                !"".equalsIgnoreCase(member.getNoHp())) {
            memberDB.setNoHp(member.getNoHp());
        } else {
            throw new MemberExceptionBadRequest("Nomor Handphone tidak boleh kosong");
        }

        // Id member
        DateFormat dateFormat = new SimpleDateFormat("YY.MM");
        Date date = new Date();
        String currentDateTime = dateFormat.format(date);
        Integer counter = findGenerateIdMember(1);
        if (counter == 0) {
            counter += 1;
            Integer changeCounter = updateGenereteIdMember(counter, 1);
            memberDB.setId(currentDateTime + ".0" + changeCounter);
        } else {
            if (counter < 9) {
                memberDB.setId(currentDateTime + ".0" + counter);
                updateGenereteIdMember(counter, 1);
            } else {
                counter += 1;
                memberDB.setId(currentDateTime + "." + counter);
                updateGenereteIdMember(counter, 1);
            }
        }
        memberDB.setFoto("profile.png");
        // BigDecimal deposit = new BigDecimal(10.0, 1);
        memberDB.setSisaDeposit(BigDecimal.ZERO);
        memberDB.setStatus(true);
        memberDB.setCreator(1);
        memberRepo.save(memberDB);

        // insert to user
        User userDB = new User();
        userDB.setUserLogin(memberDB.getEmail());
        String encodedPassword = bCryptPasswordEncoder.encode(memberDB.getNoHp());
        userDB.setPasswordLogin(encodedPassword);

        userDB.setUserRole(UserRole.MEMBER);
        userDB.setMember(memberDB);
        userRepo.save(userDB);

        return memberDB;
    }

    @Override
    public Integer updateGenereteIdMember(Integer counter, Integer id) {
        return memberRepo.updateGenereteIdByGenerateTabel(counter, id);
    }

    @Override
    public Integer findGenerateIdMember(Integer id) {
        return memberRepo.findgenerateIdMemberByGenereateTabel(id);
    }

    @Override
    public void deleteMember(String id) {
        memberRepo.deleteById(id);
    }

    @Override
    public List<Member> findAll() {
        return (List<Member>) memberRepo.findAll();
    }

    @Override
    public Member findByIdMember(String id) {
        if (memberRepo.findById(id).isEmpty()) {
            throw new MemberExceptionNotFound("Data tidak ditemukan");
        }
        return memberRepo.findById(id).get();
    }

    @Override
    public Member updateMember(String id, Member member) {
        if (memberRepo.findById(id).isEmpty()) {
            throw new MemberExceptionNotFound("Data tidak ditemukan");
        }

        Member memberDB = memberRepo.findById(id).get();

        if (Objects.nonNull(member.getNama()) &&
                !"".equalsIgnoreCase(member.getNama())) {
            memberDB.setNama(member.getNama());
        } else {
            throw new MemberExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(member.getEmail()) &&
                !"".equalsIgnoreCase(member.getEmail())) {
            memberDB.setEmail(member.getEmail());
        } else {
            throw new MemberExceptionBadRequest("Email tidak boleh kosong");
        }

        if (Objects.nonNull(member.getAlamat()) &&
                !"".equalsIgnoreCase(member.getAlamat())) {
            memberDB.setAlamat(member.getAlamat());
        } else {
            throw new MemberExceptionBadRequest("Alamat tidak boleh kosong");
        }

        memberDB.setTglLahir(member.getTglLahir());
        memberDB.setNoHp(member.getNoHp());

        if (Objects.nonNull(member.getNoHp()) &&
                !"".equalsIgnoreCase(member.getNoHp())) {
            memberDB.setNoHp(member.getNoHp());
        } else {
            throw new MemberExceptionBadRequest("Nomor Handphone tidak boleh kosong");
        }
        memberDB.setModifier(member.getModifier());

        Date date = new Date();
        memberDB.setModified_time(date);
        return memberRepo.save(memberDB);
    }

    @Override
    public Member updateFotoMember(String id, String foto) {
        if (memberRepo.findById(id).isEmpty()) {
            throw new MemberExceptionNotFound("Data tidak ditemukan");
        }
        Member memberDB = memberRepo.findById(id).get();
        if (Objects.nonNull(foto) &&
                !"".equalsIgnoreCase(foto)) {
            memberDB.setFoto(foto);
        }

        return memberRepo.save(memberDB);
    }

    @Override
    public Member updateMemberStatus(String id) {
        if (memberRepo.findById(id).isEmpty()) {
            throw new MemberExceptionNotFound("Data tidak ditemukan");
        }
        Member memberDB = memberRepo.findById(id).get();
        memberDB.setStatus(false);

        return memberRepo.save(memberDB);
    }

}
