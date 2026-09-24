package com.LakeView.LakeView.service.impl;

import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.dto.RoomDto;
import com.LakeView.LakeView.entity.Room;
import com.LakeView.LakeView.exception.OurException;
import com.LakeView.LakeView.repo.BookingRepository;
import com.LakeView.LakeView.repo.RoomRepository;
import com.LakeView.LakeView.service.FileStorageService;
import com.LakeView.LakeView.service.interfac.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Response addNewRoom(MultipartFile photo,
                               String roomType,
                               BigDecimal roomPrice,
                               String description) {

        Response response = new Response();

        try {

            String imageUrl = fileStorageService.saveImage(photo);

            Room room = new Room();
            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);

            Room savedRoom = roomRepository.save(room);

            response.setStatusCode(200);
            response.setMessage("Room Added Successfully");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomTypes();
    }

    @Override
    public Response getAllRooms() {

        Response response = new Response();

        try {

            List<Room> roomList =
                    roomRepository.findAll(
                            Sort.by(Sort.Direction.DESC, "id"));

            response.setStatusCode(200);
            response.setMessage("Success");
            response.setRoomList(null);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteRoom(Long roomId) {

        Response response = new Response();

        try {

            roomRepository.findById(roomId)
                    .orElseThrow(() ->
                            new OurException("Room Not Found"));

            roomRepository.deleteById(roomId);

            response.setStatusCode(200);
            response.setMessage("Deleted Successfully");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public Response updateRoom(Long roomId,
                               String description,
                               String roomType,
                               BigDecimal roomPrice,
                               MultipartFile photo) {

        Response response = new Response();

        try {

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() ->
                            new OurException("Room Not Found"));

            if (description != null)
                room.setRoomDescription(description);

            if (roomType != null)
                room.setRoomType(roomType);

            if (roomPrice != null)
                room.setRoomPrice(roomPrice);

            if (photo != null && !photo.isEmpty()) {

                String imageUrl =
                        fileStorageService.saveImage(photo);

                room.setRoomPhotoUrl(imageUrl);
            }

            roomRepository.save(room);

            response.setStatusCode(200);
            response.setMessage("Updated Successfully");

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public Response getRoomById(Long roomId) {
        return null;
    }

    @Override
    public Response getAvailableRoomsByDataAndType(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            String roomType) {
        return null;
    }

    @Override
    public Response getAllAvailableRooms() {
        return null;
    }
}