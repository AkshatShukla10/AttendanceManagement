package com.attendance.management.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.attendance.management.entities.StaffDetail;
import com.attendance.management.entities.User;
import com.attendance.management.repositories.StaffDetailRepository;
import com.attendance.management.repositories.UserRepository;
import com.attendance.management.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffDetailRepository staffDetailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public StaffDetail addStaffDetail(StaffDetail staffDetail) {
        return staffDetailRepository.save(staffDetail);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
