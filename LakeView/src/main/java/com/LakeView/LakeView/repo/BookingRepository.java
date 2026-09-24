package com.LakeView.LakeView.repo;

import com.LakeView.LakeView.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByBookingConfirmationCode(
            String bookingConfirmationCode
    );

    List<Booking> findByRoomId(Long roomId);

    List<Booking> findByUserId(Long userId);
}