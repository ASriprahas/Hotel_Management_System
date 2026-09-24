package com.LakeView.LakeView.controller;

import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.service.interfac.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/rooms")
@CrossOrigin("*")
public class RoomController {

    @Autowired
    private IRoomService roomService;

    @PostMapping("/add")
    public ResponseEntity<Response> addRoom(

            @RequestParam("photo") MultipartFile photo,

            @RequestParam("roomType") String roomType,

            @RequestParam("roomPrice") BigDecimal roomPrice,

            @RequestParam("description") String description) {

        Response response =
                roomService.addNewRoom(
                        photo,
                        roomType,
                        roomPrice,
                        description);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAllRooms() {

        Response response = roomService.getAllRooms();

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Response> getRoomById(
            @PathVariable Long roomId) {

        Response response =
                roomService.getRoomById(roomId);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<Response> updateRoom(

            @PathVariable Long roomId,

            @RequestParam(required = false)
            String description,

            @RequestParam(required = false)
            String roomType,

            @RequestParam(required = false)
            BigDecimal roomPrice,

            @RequestParam(required = false)
            MultipartFile photo) {

        Response response =
                roomService.updateRoom(
                        roomId,
                        description,
                        roomType,
                        roomPrice,
                        photo);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @DeleteMapping("/delete/{roomId}")
    public ResponseEntity<Response> deleteRoom(
            @PathVariable Long roomId) {

        Response response =
                roomService.deleteRoom(roomId);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @GetMapping("/available")
    public ResponseEntity<Response> getAvailableRooms(

            @RequestParam LocalDate checkInDate,

            @RequestParam LocalDate checkOutDate,

            @RequestParam String roomType) {

        Response response =
                roomService.getAvailableRoomsByDataAndType(
                        checkInDate,
                        checkOutDate,
                        roomType);

        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }
}