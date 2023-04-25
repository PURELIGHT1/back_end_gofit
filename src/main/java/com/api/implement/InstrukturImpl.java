package com.api.implement;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.exception.instruktur.InstrukturExceptionBadRequest;
import com.api.exception.instruktur.InstrukturExceptionNotFound;
import com.api.models.UserRole;
import com.api.models.entities.Instruktur;
import com.api.models.entities.User;
import com.api.models.repos.InstrukturRepo;
import com.api.models.repos.UserRepo;
import com.api.services.InstrukturService;

@Service
public class InstrukturImpl implements InstrukturService {

    @Override
    public Integer findGenerateIdInstruktur(Integer id) {
        return instrukturRepo.findgenerateIdInstrukturByGenereateTabel(id);
    }

    @Override
    public Integer updateGenereteIdInstruktur(Integer counter, Integer id) {
        return instrukturRepo.updateGenereteIdByGenerateTabel(counter, id);
    }

    @Override
    public List<Instruktur> findByEmail(String email) {
        if (instrukturRepo.findByEmail(email).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }
        return instrukturRepo.findByEmail(email);
    }

    @Override
    public List<Instruktur> findByInisial(String inisial) {
        if (instrukturRepo.findByInisial(inisial).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }
        return instrukturRepo.findByInisial(inisial);
    }

    @Autowired
    private InstrukturRepo instrukturRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Instruktur createInstruktur(Instruktur instruktur) {
        Instruktur instrukturDB = new Instruktur();

        if (Objects.nonNull(instruktur.getNama()) &&
                !"".equalsIgnoreCase(instruktur.getNama())) {
            instrukturDB.setNama(instruktur.getNama());
        } else {
            throw new InstrukturExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(instruktur.getInisial()) &&
                !"".equalsIgnoreCase(instruktur.getInisial())) {
            instrukturDB.setInisial(instruktur.getInisial());
        } else {
            throw new InstrukturExceptionBadRequest("Inisial tidak boleh kosong");
        }

        if (Objects.nonNull(instruktur.getEmail()) &&
                !"".equalsIgnoreCase(instruktur.getEmail())) {
            instrukturDB.setEmail(instruktur.getEmail());
        } else {
            throw new InstrukturExceptionBadRequest("Email tidak boleh kosong");
        }

        if (Objects.nonNull(instruktur.getAlamat()) &&
                !"".equalsIgnoreCase(instruktur.getAlamat())) {
            instrukturDB.setAlamat(instruktur.getAlamat());
        } else {
            throw new InstrukturExceptionBadRequest("Alamat tidak boleh kosong");
        }

        instrukturDB.setTglLahir(instruktur.getTglLahir());
        instrukturDB.setNoHp(instruktur.getNoHp());
        if (Objects.nonNull(instruktur.getNoHp()) &&
                !"".equalsIgnoreCase(instruktur.getNoHp())) {
            instrukturDB.setNoHp(instruktur.getNoHp());
        } else {
            throw new InstrukturExceptionBadRequest("Nomor Handphone tidak boleh kosong");
        }

        // Id Instruktur
        Integer counter = findGenerateIdInstruktur(1);
        if (counter == 0) {
            counter += 1;
            Integer changeCounter = updateGenereteIdInstruktur(counter, 1);
            instrukturDB.setId("P0" + changeCounter + "_" + instrukturDB.getInisial());
        } else {
            if (counter < 9) {
                counter += 1;
                instrukturDB.setId("P0" + counter + "_" + instrukturDB.getInisial());
                updateGenereteIdInstruktur(counter, 1);
            } else {
                counter += 1;
                instrukturDB.setId("P" + counter + "_" + instrukturDB.getInisial());
                updateGenereteIdInstruktur(counter, 1);
            }
        }

        instrukturDB.setFoto("profile.png");
        instrukturDB.setJlhHadir(0);
        instrukturDB.setJlhLibur(0);
        instrukturDB.setJlhTerlambat(0);
        instrukturDB.setStatus(true);
        instrukturDB.setCreator(1);
        instrukturRepo.save(instrukturDB);

        // insert to user
        User userDB = new User();
        userDB.setUserLogin(instrukturDB.getInisial());
        String encodedPassword = bCryptPasswordEncoder.encode(instrukturDB.getNoHp());
        userDB.setPasswordLogin(encodedPassword);

        userDB.setUserRole(UserRole.INSTRUKTUR);
        userDB.setInstruktur(instrukturDB);
        userRepo.save(userDB);

        return instrukturDB;
    }

    @Override
    public void deleteInstruktur(String id) {
        instrukturRepo.deleteById(id);
    }

    @Override
    public List<Instruktur> findAll() {
        return (List<Instruktur>) instrukturRepo.findAll();
    }

    @Override
    public Instruktur findByIdInstruktur(String id) {
        if (instrukturRepo.findById(id).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }
        return instrukturRepo.findById(id).get();
    }

    @Override
    public Instruktur updateInstruktur(String id, Instruktur instruktur) {
        if (instrukturRepo.findById(id).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }

        Instruktur instrukturDB = instrukturRepo.findById(id).get();

        if (Objects.nonNull(instruktur.getNama()) &&
                !"".equalsIgnoreCase(instruktur.getNama())) {
            instrukturDB.setNama(instruktur.getNama());
        } else {
            throw new InstrukturExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(instruktur.getEmail()) &&
                !"".equalsIgnoreCase(instruktur.getEmail())) {
            instrukturDB.setEmail(instruktur.getEmail());
        } else {
            throw new InstrukturExceptionBadRequest("Email tidak boleh kosong");
        }

        if (Objects.nonNull(instruktur.getAlamat()) &&
                !"".equalsIgnoreCase(instruktur.getAlamat())) {
            instrukturDB.setAlamat(instruktur.getAlamat());
        } else {
            throw new InstrukturExceptionBadRequest("Alamat tidak boleh kosong");
        }

        instrukturDB.setTglLahir(instruktur.getTglLahir());
        instrukturDB.setNoHp(instruktur.getNoHp());

        if (Objects.nonNull(instruktur.getNoHp()) &&
                !"".equalsIgnoreCase(instruktur.getNoHp())) {
            instrukturDB.setNoHp(instruktur.getNoHp());
        } else {
            throw new InstrukturExceptionBadRequest("Nomor Handphone tidak boleh kosong");
        }
        instrukturDB.setModifier(instruktur.getModifier());

        Date date = new Date();
        instrukturDB.setModified_time(date);
        return instrukturRepo.save(instrukturDB);
    }

    @Override
    public Instruktur updateFotoInstruktur(String id, String foto) {
        if (instrukturRepo.findById(id).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }
        Instruktur instrukturDB = instrukturRepo.findById(id).get();
        if (Objects.nonNull(foto) &&
                !"".equalsIgnoreCase(foto)) {
            instrukturDB.setFoto(foto);
        }

        return instrukturRepo.save(instrukturDB);
    }

    @Override
    public Instruktur updateInstrukturStatus(String id) {
        if (instrukturRepo.findById(id).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }
        Instruktur instrukturDB = instrukturRepo.findById(id).get();
        instrukturDB.setStatus(false);

        return instrukturRepo.save(instrukturDB);
    }

}
