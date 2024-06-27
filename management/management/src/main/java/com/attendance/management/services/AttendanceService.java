package com.attendance.management.services;

import java.util.List;

import com.attendance.management.entities.Attendance;

public interface AttendanceService {
    Attendance markAttendance(Long staffId, Long shiftId, String imagePath);
    List<Attendance> viewAttendanceByStaff(Long staffId);
}