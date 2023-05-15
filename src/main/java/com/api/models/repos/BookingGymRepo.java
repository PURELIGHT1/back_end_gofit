package com.api.models.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.models.entities.BookingGym;

public interface BookingGymRepo extends JpaRepository<BookingGym, String> {

    @Query("SELECT bg FROM BookingGym bg WHERE bg.status = 'A'")
    public List<BookingGym> findAllBookingGym();
}
