package com.LakeView.LakeView.service.interfac;

import com.LakeView.LakeView.dto.LoginRequest;
import com.LakeView.LakeView.dto.Response;
import com.LakeView.LakeView.entity.User;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
