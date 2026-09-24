package com.LakeView.LakeView.controller;

import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/all")
    public ResponseEntity<Response> getAllUsers() {
        Response response = userService.getAllUsers();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Response> getUserById(
            @PathVariable String userId) {

        Response response = userService.getUserById(userId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/booking-history/{userId}")
    public ResponseEntity<Response> getUserBookingHistory(
            @PathVariable String userId) {

        Response response =
                userService.getUserBookingHistory(userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/my-info/{email}")
    public ResponseEntity<Response> getMyInfo(
            @PathVariable String email) {

        Response response = userService.getMyInfo(email);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Response> deleteUser(
            @PathVariable String userId) {

        Response response = userService.deleteUser(userId);

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}