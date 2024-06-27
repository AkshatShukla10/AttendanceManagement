package com.attendance.management.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.attendance.management.entities.StaffDetail;
import com.attendance.management.entities.User;
import com.attendance.management.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        System.out.println("adding a user");
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PostMapping("/staff/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<StaffDetail> addStaffDetail(@RequestBody StaffDetail staffDetail) {
        return ResponseEntity.ok(userService.addStaffDetail(staffDetail));
    }
}
