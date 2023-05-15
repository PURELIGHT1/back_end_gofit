package com.api.implement;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dto.UbahPasswordRequest;
import com.api.exception.pegawai.*;
import com.api.implement.builder.GenerateImpl;
import com.api.implement.services.PegawaiService;
import com.api.models.UserRole;
import com.api.models.entities.Pegawai;
import com.api.models.entities.User;
import com.api.models.repos.PegawaiRepo;
import com.api.models.repos.TokenRepo;
import com.api.models.repos.UserRepo;

@Service
public class PegawaiImpl implements PegawaiService {

    @Autowired
    private PegawaiRepo pegawaiRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private GenerateImpl generateImpl;

    @Override
    public Pegawai createPegawai(Pegawai pegawai) {
        Pegawai pegawaiDB = new Pegawai();

        if (Objects.nonNull(pegawai.getNama()) &&
                !"".equalsIgnoreCase(pegawai.getNama())) {
            pegawaiDB.setNama(pegawai.getNama());
        } else {
            throw new PegawaiExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(pegawai.getEmail()) &&
                !"".equalsIgnoreCase(pegawai.getEmail())) {
            pegawaiDB.setEmail(pegawai.getEmail());
        } else {
            throw new PegawaiExceptionBadRequest("Email tidak boleh kosong");
        }

        if (Objects.nonNull(pegawai.getAlamat()) &&
                !"".equalsIgnoreCase(pegawai.getAlamat())) {
            pegawaiDB.setAlamat(pegawai.getAlamat());
        } else {
            throw new PegawaiExceptionBadRequest("Alamat tidak boleh kosong");
        }

        pegawaiDB.setTglLahir(pegawai.getTglLahir());
        pegawaiDB.setNoHp(pegawai.getNoHp());

        if (Objects.nonNull(pegawai.getNoHp()) &&
                !"".equalsIgnoreCase(pegawai.getNoHp())) {
            pegawaiDB.setNoHp(pegawai.getNoHp());
        } else {
            throw new PegawaiExceptionBadRequest("Nomor Handphone tidak boleh kosong");
        }

        // Id pegawai
        Integer counter = generateImpl.findGenerateIdPegawai(1);
        if (counter == 0) {
            counter += 1;
            Integer changeCounter = generateImpl.updateGenereteIdPegawai(counter);
            pegawaiDB.setId("P0" + changeCounter);
        } else {
            if (counter < 9) {
                counter += 1;
                pegawaiDB.setId("P0" + counter);
                generateImpl.updateGenereteIdPegawai(counter);
            } else {
                counter += 1;
                pegawaiDB.setId("P" + counter);
                generateImpl.updateGenereteIdPegawai(counter);
            }
        }

        pegawaiDB.setFoto("profile.png");
        pegawaiDB.setStatus("A");
        pegawaiDB.setCreator(pegawai.getCreator());
        return pegawaiRepo.save(pegawaiDB);
    }

    @Override
    public Pegawai ubahPasswordPegawai(String id, UbahPasswordRequest request) {
        Pegawai pegawaiDB = findByIdPegawai(id);

        String password = request.getPasswordBaru();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Integer ubah = pegawaiRepo.updatePassword(encodedPassword, pegawaiDB);
        if (ubah <= 0) {
            throw new PegawaiExceptionBadRequest("Password gagal diubah");
        }
        return pegawaiDB;
    }

    @Override
    public Pegawai addUserPegawai(String id, String role) {
        if (pegawaiRepo.findById(id).isEmpty()) {
            throw new PegawaiExceptionNotFound("Data tidak ditemukan");
        }
        Pegawai pegawaiDB = pegawaiRepo.findById(id).get();

        // insert MO to user
        if ("KASIR".equalsIgnoreCase(role)) {
            User userDB = new User();
            userDB.setUserLogin(pegawaiDB.getEmail());
            String encodedPassword = bCryptPasswordEncoder.encode(pegawaiDB.getNoHp());
            userDB.setPasswordLogin(encodedPassword);

            userDB.setUserRole(UserRole.KASIR);
            userDB.setPegawai(pegawaiDB);
            userRepo.save(userDB);
        }

        if ("MO".equalsIgnoreCase(role)) {
            User userDB = new User();
            userDB.setUserLogin(pegawaiDB.getEmail());
            String encodedPassword = bCryptPasswordEncoder.encode(pegawaiDB.getNoHp());
            userDB.setPasswordLogin(encodedPassword);

            userDB.setUserRole(UserRole.MO);
            userDB.setPegawai(pegawaiDB);
            userRepo.save(userDB);
        }
        pegawaiDB.setModifier(1);
        Date date = new Date();
        pegawaiDB.setModified_time(date);

        return pegawaiRepo.save(pegawaiDB);
    }

    @Override
    public void deletePegawai(String id) {
        Pegawai pegawaiDB = pegawaiRepo.findById(id).get();
        User userDB = pegawaiRepo.findUserPegawai(pegawaiDB).get(0);
        // TokenRepo token = tokenRepo.findById(tokeDB.getId());
        tokenRepo.deleteAll();
        userRepo.deleteById(userDB.getId());
        pegawaiRepo.deleteById(id);
    }

    @Override
    public List<Pegawai> findAll() {
        return (List<Pegawai>) pegawaiRepo.findAll();
    }

    @Override
    public Pegawai findByIdPegawai(String id) {
        if (pegawaiRepo.findById(id).isEmpty()) {
            throw new PegawaiExceptionNotFound("Data tidak ditemukan");
        }
        return pegawaiRepo.findById(id).get();
    }

    @Override
    public Pegawai updatePegawai(String id, Pegawai pegawai) {
        if (pegawaiRepo.findById(id).isEmpty()) {
            throw new PegawaiExceptionNotFound("Data tidak ditemukan");
        }

        Pegawai pegawaiDB = pegawaiRepo.findById(id).get();

        if (Objects.nonNull(pegawai.getNama()) &&
                !"".equalsIgnoreCase(pegawai.getNama())) {
            pegawaiDB.setNama(pegawai.getNama());
        } else {
            throw new PegawaiExceptionBadRequest("Nama tidak boleh kosong");
        }

        if (Objects.nonNull(pegawai.getEmail()) &&
                !"".equalsIgnoreCase(pegawai.getEmail())) {
            pegawaiDB.setEmail(pegawai.getEmail());
        } else {
            throw new PegawaiExceptionBadRequest("Email tidak boleh kosong");
        }

        if (Objects.nonNull(pegawai.getAlamat()) &&
                !"".equalsIgnoreCase(pegawai.getAlamat())) {
            pegawaiDB.setAlamat(pegawai.getAlamat());
        } else {
            throw new PegawaiExceptionBadRequest("Alamat tidak boleh kosong");
        }

        pegawaiDB.setTglLahir(pegawai.getTglLahir());
        pegawaiDB.setModifier(pegawai.getModifier());

        Date date = new Date();
        pegawaiDB.setModified_time(date);

        // update to user
        User userDB = userRepo.findUserByPegawai(pegawaiDB);
        if (userDB.getUserRole() == UserRole.MO) {
            if (pegawaiDB.getNoHp().equals(pegawai.getNoHp())) {
                userDB.setPasswordLogin(userDB.getPasswordLogin());
            } else {
                pegawaiDB.setNoHp(pegawai.getNoHp());
                String encodedPassword = bCryptPasswordEncoder.encode(pegawai.getNoHp());
                userDB.setPasswordLogin(encodedPassword);
            }
        }
        pegawaiDB.setNoHp(pegawai.getNoHp());
        pegawaiRepo.save(pegawaiDB);
        userRepo.save(userDB);

        return pegawaiDB;
    }

    @Override
    public Pegawai updateFotoPegawai(String id, String foto) {
        if (pegawaiRepo.findById(id).isEmpty()) {
            throw new PegawaiExceptionNotFound("Data tidak ditemukan");
        }
        Pegawai pegawaiDB = pegawaiRepo.findById(id).get();
        if (Objects.nonNull(foto) &&
                !"".equalsIgnoreCase(foto)) {
            pegawaiDB.setFoto(foto);
        }

        return pegawaiRepo.save(pegawaiDB);
    }

    @Override
    public Pegawai updatePegawaiStatus(String id) {
        if (pegawaiRepo.findById(id).isEmpty()) {
            throw new PegawaiExceptionNotFound("Data tidak ditemukan");
        }
        Pegawai pegawaiDB = pegawaiRepo.findById(id).get();
        pegawaiDB.setStatus("F");

        return pegawaiRepo.save(pegawaiDB);
    }

    @Override
    public List<Pegawai> findByEmail(String email) {
        if (pegawaiRepo.findByEmail(email).isEmpty()) {
            throw new PegawaiExceptionNotFound("Data tidak ditemukan");
        }
        return pegawaiRepo.findByEmail(email);
    }

}
