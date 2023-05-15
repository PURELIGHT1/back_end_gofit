package com.api.implement;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.models.entities.BookingGym;
import com.api.models.entities.Member;
import com.api.models.repos.BookingGymRepo;

@Service
public class BookingGymImpl {

    @Autowired
    private BookingGymRepo repo;

    @Autowired
    private MemberImpl memberImpl;

    public List<BookingGym> findAll() {
        return (List<BookingGym>) repo.findAllBookingGym();
    }

    public BookingGym bookingGym(String id) {
        Member memberDB = memberImpl.findByIdMember(id);
        BookingGym bookingGym = new BookingGym();
        bookingGym.setMember(memberDB);
        bookingGym.setStatus("S");
        Date now = new Date();
        bookingGym.setTglBooking(now);
        String generateString = RandomStringUtils.randomAlphanumeric(15);
        bookingGym.setId("Booked-Gym-" + memberDB.getId() + "_" + generateString);
        return repo.save(bookingGym);
    }
}
