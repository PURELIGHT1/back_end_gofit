package com.api.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.api.exception.member.MemberExceptionBadRequest;
import com.api.models.entities.Member;
import com.api.models.entities.TransaksiAktivasi;
import com.api.models.repos.MemberRepo;
import com.api.models.repos.TransaksiAktivasiRepo;

@Service
public class TransaksiAktivasiImpl {

    @Autowired
    private TransaksiAktivasiRepo repo;

    @Autowired
    private MemberImpl memberImpl;

    public List<TransaksiAktivasi> findAllAktivasi() {
        return (List<TransaksiAktivasi>) repo.findAll();
    }

    public TransaksiAktivasi findAllByMember(String id) {
        Member memberDB = memberImpl.findByIdMember(id);
        return repo.findTAMember(memberDB);
    }

    public List<TransaksiAktivasi> findByMember(String id) {
        Member memberDB = memberImpl.findByIdMember(id);
        return repo.findAllTAMember(memberDB);
    }
    // public List<TransaksiAktivasi> findAllNonAktivasi() {
    // return (List<TransaksiAktivasi>) repo.findAllNonAktivasi();
    // }

    public TransaksiAktivasi findTransaksiAktivasiById(String id) {
        if (repo.findById(id).isEmpty()) {
            throw new MemberExceptionBadRequest("Data tidak ditemukan");
        }
        return repo.findById(id).get();
    }

    public TransaksiAktivasi updateTransaksiAktivasi(String id) {
        TransaksiAktivasi transaksiAktivasiDB = findTransaksiAktivasiById(id);
        Member memberDB = memberImpl.findByIdMember(transaksiAktivasiDB.getMember().getId());
        memberImpl.updateMemberStatus(transaksiAktivasiDB.getMember().getId());
        transaksiAktivasiDB.setStatus("P");
        repo.save(transaksiAktivasiDB);
        return transaksiAktivasiDB;
    }

}
