package com.LakeView.LakeView.controller;


import com.LakeView.LakeView.dto.LoginRequest;
import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.entity.User;
import com.LakeView.LakeView.service.interfac.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@RequestBody User user) {
        Response response = userService.register(user);
        return ResponseEntity.status(Math.toIntExact(response.getStatusCode())).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest) {
        Response response = userService.login(loginRequest);
        return ResponseEntity.status(Math.toIntExact(response.getStatusCode())).body(response);
    }
}
