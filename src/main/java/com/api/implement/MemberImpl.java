package com.api.implement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.dto.UbahPasswordRequest;
import com.api.exception.member.*;
import com.api.implement.builder.GenerateImpl;
import com.api.implement.services.MemberService;
import com.api.models.UserRole;
import com.api.models.entities.Member;
import com.api.models.entities.Pegawai;
import com.api.models.entities.TransaksiAktivasi;
import com.api.models.entities.User;
import com.api.models.repos.MemberRepo;
import com.api.models.repos.TokenRepo;
import com.api.models.repos.TransaksiAktivasiRepo;
import com.api.models.repos.UserRepo;

@Service
public class MemberImpl implements MemberService {

    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private GenerateImpl generateImpl;

    @Autowired
    private PegawaiImpl pegawaiImpl;

    @Autowired
    private TransaksiAktivasiRepo transaksiAktivasiRepo;

    @Autowired
    private TransaksiAktivasiImpl transaksiAktivasiImpl;

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

        Integer counter = generateImpl.findGenerateIdMember(1);
        if (counter == 0) {
            counter += 1;
            Integer changeCounter = generateImpl.updateGenereteIdMember(counter);
            memberDB.setId(currentDateTime + ".0" + changeCounter);
        } else {
            if (counter < 9) {
                counter += 1;
                memberDB.setId(currentDateTime + ".0" + counter);
                generateImpl.updateGenereteIdMember(counter);
            } else {
                counter += 1;
                memberDB.setId(currentDateTime + "." + counter);
                generateImpl.updateGenereteIdMember(counter);
            }
        }
        memberDB.setFoto("profile.png");
        memberDB.setSisaDeposit(0);
        memberDB.setStatus("I");
        memberDB.setCreator(member.getCreator());

        // insert to user
        User userDB = new User();
        userDB.setUserLogin(memberDB.getEmail());
        DateFormat dateFormat2 = new SimpleDateFormat("Y-MM-dd");
        String tglLahir = dateFormat2.format(memberDB.getTglLahir());
        String encodedPassword = bCryptPasswordEncoder.encode(tglLahir);
        userDB.setPasswordLogin(encodedPassword);
        userDB.setUserRole(UserRole.MEMBER);
        userDB.setMember(memberDB);

        // insert to Transaksi Aktivasi
        TransaksiAktivasi transaksiAktivasiDB = new TransaksiAktivasi();

        Integer counter2 = generateImpl.findGenerateStruk(1);
        if (counter2 != 0) {
            counter2 += 1;
            generateImpl.updateGenereteStruk(counter2);
            transaksiAktivasiDB.setId(currentDateTime + "." + counter2);
        }
        Pegawai pegawaiDB = pegawaiImpl.findByIdPegawai(member.getCreator());
        transaksiAktivasiDB.setPegawai(pegawaiDB);
        transaksiAktivasiDB.setMember(memberDB);

        memberRepo.save(memberDB);
        userRepo.save(userDB);
        transaksiAktivasiDB.setJlhBayar(3000000);

        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        transaksiAktivasiDB.setTglAktiviasi(now);

        cal.setTime(now);
        cal.add(Calendar.DATE, 365);
        Date tglAktivasi = cal.getTime();
        transaksiAktivasiDB.setTglBerlaku(tglAktivasi);

        transaksiAktivasiDB.setStatus("W");
        transaksiAktivasiRepo.save(transaksiAktivasiDB);
        return memberDB;
    }

    // @Override
    // public Member updatePasswordMember(String id) {
    // Member memberDB = memberRepo.findById(id).get();
    // User userDB = userRepo.findUserByMember(memberDB).get(0);
    // userDB.setPasswordLogin(memberDB.getTglLahir());
    // return memberDB;
    // }

    @Override
    public Member ubahPasswordMember(String id, UbahPasswordRequest request) {
        Member memberDB = findByIdMember(id);

        String password = request.getPasswordBaru();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        Integer ubah = memberRepo.updatePassword(encodedPassword, memberDB);
        if (ubah <= 0) {
            throw new MemberExceptionBadRequest("Password gagal diubah");
        }
        return memberDB;
    }

    @Override
    public Member updatePasswordMember(String id) {
        Member memberDB = memberRepo.findById(id).get();
        User userDB = userRepo.findUserByMember(memberDB);
        DateFormat dateFormat = new SimpleDateFormat("Y-MM-dd");
        String tglLahir = dateFormat.format(memberDB.getTglLahir());
        // String encodedPassword = bCryptPasswordEncoder.encode(tglLahir);
        userDB.setPasswordLogin("12");
        return memberDB;
    }

    @Override
    public void deleteMember(String id) {
        Member memberDB = memberRepo.findById(id).get();
        User userDB = memberRepo.findUserMember(memberDB).get(0);
        // TokenRepo token = tokenRepo.findById(tokeDB.getId());
        tokenRepo.deleteAll();

        TransaksiAktivasi transaksiAktivasiDB = transaksiAktivasiRepo.findTAMember(memberDB);
        transaksiAktivasiRepo.deleteById(transaksiAktivasiDB.getId());
        userRepo.deleteById(userDB.getId());
        memberRepo.deleteById(id);
    }

    @Override
    public List<Member> findAll() {
        return (List<Member>) memberRepo.findAll();
    }

    @Override
    public List<Member> findAllAktif() {
        return (List<Member>) memberRepo.findMemberAktif();
    }

    @Override
    public Member findByIdMember(String id) {
        if (memberRepo.findById(id).isEmpty()) {
            throw new MemberExceptionNotFound("Data tidak ditemukan");
        }
        return memberRepo.findById(id).get();
    }

    public Member findByIdMemberAktif(String id) {
        return memberRepo.findMemberByIdAktif(id);
    }

    @Override
    public Member updateDepositMember(String id, Integer deposit) {
        Member DB = findByIdMember(id);
        DB.setSisaDeposit(DB.getSisaDeposit() + deposit);
        return memberRepo.save(DB);
    }

    @Override
    public Member updateDepositMemberKurang(String id, Integer deposit) {
        Member DB = findByIdMember(id);
        DB.setSisaDeposit(DB.getSisaDeposit() - deposit);
        return memberRepo.save(DB);
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
        if (Objects.nonNull(member.getAlamat()) &&
                !"".equalsIgnoreCase(member.getAlamat())) {
            memberDB.setAlamat(member.getAlamat());
        } else {
            throw new MemberExceptionBadRequest("Alamat tidak boleh kosong");
        }

        memberDB.setTglLahir(member.getTglLahir());
        memberDB.setNoHp(member.getNoHp());
        memberDB.setModifier(member.getModifier());
        memberDB.setStatus(member.getStatus());

        Date date = new Date();
        memberDB.setModified_time(date);
        memberRepo.save(memberDB);

        return memberDB;
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
        memberDB.setStatus("A");

        return memberRepo.save(memberDB);
    }

}
