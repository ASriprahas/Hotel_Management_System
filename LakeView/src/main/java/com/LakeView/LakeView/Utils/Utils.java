package com.LakeView.LakeView.Utils;

import com.LakeView.LakeView.dto.*;
import com.LakeView.LakeView.entity.*;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final String ALPHANUMERIC_STRING =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final SecureRandom secureRandom =
            new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(
                    ALPHANUMERIC_STRING.charAt(
                            secureRandom.nextInt(
                                    ALPHANUMERIC_STRING.length()
                            )
                    )
            );
        }

        return sb.toString();
    }

    public static UserDto mapUserEntityToUserDTO(User user) {

        UserDto dto = new UserDto();

        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setRole(user.getRole());

        return dto;
    }

    public static UserDto mapUserEntityToUserDTOPlusUserBookingsAndRoom(
            User user) {

        UserDto dto = mapUserEntityToUserDTO(user);

        dto.setBookings(user.getBookings());

        return dto;
    }

    public static RoomDto mapRoomEntityToRoomDTO(Room room) {

        RoomDto dto = new RoomDto();

        dto.setId((long) room.getId());
        dto.setRoomType(room.getRoomType());
        dto.setRoomPrice(room.getRoomPrice().toString());
        dto.setRoomPhotoUrl(room.getRoomPhotoUrl());
        dto.setRoomDescription(room.getRoomDescription());

        return dto;
    }

    public static BookingDto mapBookingEntityToBookingDTO(
            Booking booking) {

        BookingDto dto = new BookingDto();

        dto.setId(booking.getId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setNumberOfAdults(booking.getNumberOfAdults());
        dto.setNumberOfChildren(booking.getNumberOfChildren());
        dto.setTotalNumberOfGuests(
                booking.getTotalNumberOfGuests()
        );
        dto.setBookingConfirmationCode(
                booking.getBookingConfirmationCode()
        );

        return dto;
    }

    public static List<UserDto> mapUserListEntityToUserListDTO(
            List<User> users) {

        return users.stream()
                .map(Utils::mapUserEntityToUserDTO)
                .collect(Collectors.toList());
    }

    public static List<RoomDto> mapRoomListEntityToRoomListDTO(
            List<Room> rooms) {

        return rooms.stream()
                .map(Utils::mapRoomEntityToRoomDTO)
                .collect(Collectors.toList());
    }

    public static List<BookingDto> mapBookingListEntityToBookingListDTO(
            List<Booking> bookings) {

        return bookings.stream()
                .map(Utils::mapBookingEntityToBookingDTO)
                .collect(Collectors.toList());
    }
}