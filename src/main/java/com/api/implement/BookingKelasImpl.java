package com.api.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.implement.services.MemberService;
import com.api.models.entities.BookingKelas;
import com.api.models.entities.Member;
import com.api.models.repos.BookingKelasRepo;

@Service
public class BookingKelasImpl {

    @Autowired
    private BookingKelasRepo repo;

    @Autowired
    private MemberService service;

    public List<BookingKelas> findAll() {
        return (List<BookingKelas>) repo.findAll();
    }

    public List<BookingKelas> findAllByMember(String id) {
        Member memberDB = service.findByIdMember(id);
        return (List<BookingKelas>) repo.findAllByMember(memberDB);
    }

    public List<BookingKelas> findAllByJadwal(String id) {
        return (List<BookingKelas>) repo.findAllBookingJadwal(id);
    }

}
