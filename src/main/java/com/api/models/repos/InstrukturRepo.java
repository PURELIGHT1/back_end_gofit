package com.api.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import com.api.models.entities.Instruktur;
import com.api.models.entities.User;

import jakarta.transaction.Transactional;

public interface InstrukturRepo extends JpaRepository<Instruktur, String> {

    // @Query("SELECT i from _user u JOIN instruktur i ON u.instruktur = i.id GROUP
    // BY i.id, u.id")
    // @Query("SELECT * FROM instruktur i NATURAL JOIN user u")
    // public Collection<> findALLByUser();
    @Query("SELECT u from _user u WHERE u.instruktur = ?1")
    public User findUserInstruktur(Instruktur instruktur);

    @Query("SELECT i from instruktur i order by i.status asc")
    public List<Instruktur> findInstrukturUrut();

    @Query("SELECT i from instruktur i where i.status = 'A' order by i.nama asc")
    public List<Instruktur> findInstrukturAktif();

    @Query("SELECT i FROM instruktur i WHERE i.email = ?1")
    public List<Instruktur> findByEmail(String email);

    @Query("SELECT i FROM instruktur i where i.status = 'A' order by i.jlhTerlambat asc")
    public List<Instruktur> findInstrukturASC();

    @Query("SELECT i FROM instruktur i where i.status='A'")
    public List<Instruktur> findInstruktur();

    @Query("SELECT i FROM instruktur i WHERE i.inisial = ?1")
    public List<Instruktur> findByInisial(String inisial);

    @Query("select i from instruktur i where i.nama LIKE %?1% or i.alamat LIKE %?1% or i.noHp LIKE %?1%")
    public List<Instruktur> findByNama(String nama);

    @Query("SELECT generateIdInstruktur FROM generate gr WHERE gr.id = ?1")
    public Integer findgenerateIdInstrukturByGenereateTabel(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE generate gr SET gr.generateIdInstruktur = ?1 WHERE gr.id = ?2")
    public Integer updateGenereteIdByGenerateTabel(Integer counter, Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE _user u SET u.passwordLogin = ?1 WHERE u.instruktur = ?2")
    public Integer updatePassword(String password, Instruktur instruktur);
}
