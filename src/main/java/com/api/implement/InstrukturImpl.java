package com.api.implement;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dto.UbahPasswordRequest;
import com.api.exception.instruktur.InstrukturExceptionBadRequest;
import com.api.exception.instruktur.InstrukturExceptionNotFound;
import com.api.implement.builder.GenerateImpl;
import com.api.implement.services.InstrukturService;
import com.api.models.UserRole;
import com.api.models.entities.Instruktur;
import com.api.models.entities.User;
import com.api.models.repos.InstrukturRepo;
import com.api.models.repos.TokenRepo;
import com.api.models.repos.UserRepo;

@Service
public class InstrukturImpl implements InstrukturService {

    @Autowired
    private InstrukturRepo instrukturRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private GenerateImpl generateImpl;

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

        // Id Instruktur
        Integer counter = generateImpl.findGenerateIdInstruktur(1);
        if (counter == 0) {
            counter += 1;
            generateImpl.updateGenereteIdInstruktur(counter);
            instrukturDB.setId("P0" + counter + "-" + instruktur.getInisial());
        } else {
            if (counter < 9) {
                counter += 1;
                instrukturDB.setId("P0" + counter + "-" + instruktur.getInisial());
                generateImpl.updateGenereteIdInstruktur(counter);
            } else {
                counter += 1;
                instrukturDB.setId("P" + counter + "-" + instruktur.getInisial());
                generateImpl.updateGenereteIdInstruktur(counter);
            }
        }

        instrukturDB.setFoto("profile.png");
        instrukturDB.setJlhHadir(0);
        instrukturDB.setJlhLibur(0);
        instrukturDB.setJlhTerlambat(0);
        instrukturDB.setStatus("A");
        instrukturDB.setCreator(instruktur.getCreator());
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
        Instruktur instrukturDB = instrukturRepo.findById(id).get();
        User userDB = instrukturRepo.findUserInstruktur(instrukturDB).get(0);
        // TokenRepo token = tokenRepo.findById(tokeDB.getId());
        tokenRepo.deleteAll();
        userRepo.deleteById(userDB.getId());
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
        instrukturDB.setModifier(instruktur.getModifier());

        Date date = new Date();
        instrukturDB.setModified_time(date);

        // update to user
        User userDB = userRepo.findUserByInstruktur(instrukturDB);
        userDB.setUserLogin(instrukturDB.getInisial());
        if (instrukturDB.getNoHp().equals(instruktur.getNoHp())) {
            userDB.setPasswordLogin(userDB.getPasswordLogin());
        } else {
            instrukturDB.setNoHp(instruktur.getNoHp());
            String encodedPassword = bCryptPasswordEncoder.encode(instruktur.getNoHp());
            userDB.setPasswordLogin(encodedPassword);
        }
        instrukturRepo.save(instrukturDB);
        userRepo.save(userDB);
        return instrukturDB;
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
    public Instruktur ubahPasswordInstruktur(String id, UbahPasswordRequest request) {
        Instruktur instrukturDB = findByIdInstruktur(id);

        String password = request.getPasswordBaru();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Integer ubah = instrukturRepo.updatePassword(encodedPassword, instrukturDB);
        if (ubah <= 0) {
            throw new InstrukturExceptionBadRequest("Password gagal diubah");
        }
        return instrukturDB;
    }

    @Override
    public Instruktur updateInstrukturStatus(String id) {
        if (instrukturRepo.findById(id).isEmpty()) {
            throw new InstrukturExceptionNotFound("Data tidak ditemukan");
        }
        Instruktur instrukturDB = instrukturRepo.findById(id).get();
        instrukturDB.setStatus("I");

        return instrukturRepo.save(instrukturDB);
    }

}
