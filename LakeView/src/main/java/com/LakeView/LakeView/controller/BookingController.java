package com.LakeView.LakeView.controller;

import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.entity.Booking;
import com.LakeView.LakeView.service.interfac.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@CrossOrigin("*")
public class BookingController {

    @Autowired
    private IBookingService bookingService;

    @PostMapping("/book-room/{roomId}/{userId}")
    public ResponseEntity<Response> saveBooking(

            @PathVariable Long roomId,

            @PathVariable Long userId,

            @RequestBody Booking bookingRequest) {

        Response response =
                bookingService.saveBooking(
                        roomId,
                        userId,
                        bookingRequest);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllBookings() {

        Response response =
                bookingService.getAllBookings();

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @GetMapping("/confirmation/{confirmationCode}")
    public ResponseEntity<Response> getBookingByCode(
            @PathVariable String confirmationCode) {

        Response response =
                bookingService.findBookingByConfirmationCode(
                        confirmationCode);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @DeleteMapping("/cancel/{bookingId}")
    public ResponseEntity<Response> cancelBooking(
            @PathVariable Long bookingId) {

        Response response =
                bookingService.cancelBooking(bookingId);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }
}