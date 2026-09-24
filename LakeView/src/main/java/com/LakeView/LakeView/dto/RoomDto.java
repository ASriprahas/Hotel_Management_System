package com.LakeView.LakeView.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDto {

    private Long id;

    private String roomType;

    private String roomPrice;

    private String roomPhotoUrl;

    private String roomDescription;

    private List<BookingDto> bookings = new ArrayList<>();
}