package com.LakeView.LakeView.service;

import com.LakeView.LakeView.exception.OurException;
import com.LakeView.LakeView.repo.UserRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService, UserDetailsPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new OurException("User/Email not found"));

    }

    @Override
    public UserDetails updatePassword(UserDetails user, @Nullable String newPassword) {
        return null;
    }
}
