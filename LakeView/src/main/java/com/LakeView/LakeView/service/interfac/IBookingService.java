package com.LakeView.LakeView.service.interfac;

import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.entity.Booking;

public interface IBookingService {

    Response saveBooking(Long roomId, Long userId, Booking bookingRequest);

    Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(Long bookingId);

}
