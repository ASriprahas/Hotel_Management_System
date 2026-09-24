package com.LakeView.LakeView.service.impl;

import com.LakeView.LakeView.Utils.Utils;
import com.LakeView.LakeView.dto.BookingDto;
import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.entity.Booking;
import com.LakeView.LakeView.entity.Room;
import com.LakeView.LakeView.entity.User;
import com.LakeView.LakeView.exception.OurException;
import com.LakeView.LakeView.repo.BookingRepository;
import com.LakeView.LakeView.repo.RoomRepository;
import com.LakeView.LakeView.repo.UserRepository;
import com.LakeView.LakeView.service.interfac.IBookingService;
import com.LakeView.LakeView.service.interfac.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private IRoomService roomService;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {

        Response response = new Response();

        try {

            if (bookingRequest.getCheckOutDate()
                    .isBefore(bookingRequest.getCheckInDate())) {

                throw new IllegalArgumentException(
                        "Check in date must come before check out date");
            }

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() ->
                            new OurException("Room Not Found"));

            User user = userRepository.findById(userId)
                    .orElseThrow(() ->
                            new OurException("User Not Found"));

            List<Booking> existingBookings = room.getBookings();

            if (!roomIsAvailable(bookingRequest, existingBookings)) {
                throw new OurException(
                        "Room not Available for selected date range");
            }

            bookingRequest.setRoom(room);
            bookingRequest.setUser(user);

            String bookingConfirmationCode =
                    Utils.generateRandomConfirmationCode(10);

            bookingRequest.setBookingConfirmationCode(
                    bookingConfirmationCode);

            bookingRepository.save(bookingRequest);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBookingConfirmationCode(
                    bookingConfirmationCode);

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Saving a booking: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response findBookingByConfirmationCode(
            String confirmationCode) {

        Response response = new Response();

        try {

            Booking booking =
                    bookingRepository
                            .findByBookingConfirmationCode(
                                    confirmationCode)
                            .orElseThrow(() ->
                                    new OurException(
                                            "Booking Not Found"));

            BookingDto bookingDTO =
                    Utils.mapBookingEntityToBookingDTO(booking);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBooking(bookingDTO);

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Finding a booking: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getAllBookings() {

        Response response = new Response();

        try {

            List<Booking> bookingList =
                    bookingRepository.findAll(
                            Sort.by(
                                    Sort.Direction.DESC,
                                    "id"));

            List<BookingDto> bookingDTOList =
                    Utils.mapBookingListEntityToBookingListDTO(
                            bookingList);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setBookingList(bookingDTOList);

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Getting all bookings: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response cancelBooking(Long bookingId) {

        Response response = new Response();

        try {

            bookingRepository.findById(bookingId)
                    .orElseThrow(() ->
                            new OurException(
                                    "Booking Does Not Exist"));

            bookingRepository.deleteById(bookingId);

            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Cancelling a booking: "
                            + e.getMessage());
        }

        return response;
    }

    private boolean roomIsAvailable(
            Booking bookingRequest,
            List<Booking> existingBookings) {

        return existingBookings.stream()
                .noneMatch(existingBooking ->

                        bookingRequest.getCheckInDate()
                                .equals(
                                        existingBooking
                                                .getCheckInDate())

                                ||

                                bookingRequest.getCheckOutDate()
                                        .isBefore(
                                                existingBooking
                                                        .getCheckOutDate())

                                ||

                                (bookingRequest
                                        .getCheckInDate()
                                        .isAfter(
                                                existingBooking
                                                        .getCheckInDate())

                                        &&

                                        bookingRequest
                                                .getCheckInDate()
                                                .isBefore(
                                                        existingBooking
                                                                .getCheckOutDate()))
                );
    }
}