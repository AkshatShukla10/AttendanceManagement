package com.attendance.management.services;

import com.attendance.management.entities.StaffDetail;
import com.attendance.management.entities.User;

public interface UserService {
    User addUser(User user);
    StaffDetail addStaffDetail(StaffDetail staffDetail);
    User findByUsername(String username);
}
