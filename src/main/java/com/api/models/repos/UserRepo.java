package com.api.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.Instruktur;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.User;

import jakarta.transaction.Transactional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserLogin(String userLogin);

    @Query("SELECT u from _user u where u.member = ?1")
    User findUserByMember(Member member);

    @Query("SELECT u from _user u where u.instruktur = ?1")
    User findUserByInstruktur(Instruktur instruktur);

    @Query("SELECT u from _user u where u.pegawai = ?1")
    User findUserByPegawai(Pegawai pegawai);

    @Modifying
    @Transactional
    @Query("DELETE _user u WHERE u.instruktur = ?1")
    public Integer delteUser(Instruktur instruktur);
    // @Query("select m from Member m where m.statusAktivasi = 'Belum Teraktivasi'")
    // public List<Member> findByAktivasi();

}
