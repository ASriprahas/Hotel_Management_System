package com.LakeView.LakeView.service.impl;

import com.LakeView.LakeView.Utils.JwtUtils;
import com.LakeView.LakeView.Utils.Utils;
import com.LakeView.LakeView.dto.LoginRequest;
import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.dto.UserDto;
import com.LakeView.LakeView.entity.User;
import com.LakeView.LakeView.exception.OurException;
import com.LakeView.LakeView.repo.UserRepository;
import com.LakeView.LakeView.service.interfac.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response register(User user) {

        Response response = new Response();

        try {

            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }

            if (userRepository.existsByEmail(user.getEmail())) {
                throw new OurException(
                        user.getEmail() + " already exists");
            }

            user.setPassword(
                    passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(user);

            UserDto userDTO =
                    Utils.mapUserEntityToUserDTO(savedUser);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {

            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Occurred During User Registration: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {

        Response response = new Response();

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            User user =
                    userRepository.findByEmail(
                                    loginRequest.getEmail())
                            .orElseThrow(() ->
                                    new OurException(
                                            "User Not Found"));

            String token =
                    jwtUtils.generateToken(user.getEmail());

            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("successful");

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Occurred During User Login: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getAllUsers() {

        Response response = new Response();

        try {

            List<User> userList =
                    userRepository.findAll();

            List<UserDto> userDTOList =
                    Utils.mapUserListEntityToUserListDTO(
                            userList);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Getting All Users: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getUserBookingHistory(
            String userId) {

        Response response = new Response();

        try {

            User user =
                    userRepository.findById(
                                    Long.valueOf(userId))
                            .orElseThrow(() ->
                                    new OurException(
                                            "User Not Found"));

            UserDto userDTO =
                    Utils.mapUserEntityToUserDTO(user);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Getting User Booking History: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteUser(String userId) {

        Response response = new Response();

        try {

            Long id = Long.valueOf(userId);

            userRepository.findById(id)
                    .orElseThrow(() ->
                            new OurException(
                                    "User Not Found"));

            userRepository.deleteById(id);

            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Deleting User: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getUserById(String userId) {

        Response response = new Response();

        try {

            User user =
                    userRepository.findById(
                                    Long.valueOf(userId))
                            .orElseThrow(() ->
                                    new OurException(
                                            "User Not Found"));

            UserDto userDTO =
                    Utils.mapUserEntityToUserDTO(user);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Getting User: "
                            + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getMyInfo(String email) {

        Response response = new Response();

        try {

            User user =
                    userRepository.findByEmail(email)
                            .orElseThrow(() ->
                                    new OurException(
                                            "User Not Found"));

            UserDto userDTO =
                    Utils.mapUserEntityToUserDTO(user);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        } catch (OurException e) {

            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage(
                    "Error Getting User Info: "
                            + e.getMessage());
        }

        return response;
    }
}