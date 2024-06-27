package com.attendance.management.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.attendance.management.entities.Attendance;
import com.attendance.management.services.AttendanceService;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/mark")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<Attendance> markAttendance(@RequestParam Long staffId, @RequestParam Long shiftId, @RequestParam String imagePath) {
        return ResponseEntity.ok(attendanceService.markAttendance(staffId, shiftId, imagePath));
    }

    @GetMapping("/view/{staffId}")
    @PreAuthorize("hasRole('STAFF')")
    public ResponseEntity<List<Attendance>> viewAttendanceByStaff(@PathVariable Long staffId) {
        return ResponseEntity.ok(attendanceService.viewAttendanceByStaff(staffId));
    }
}
