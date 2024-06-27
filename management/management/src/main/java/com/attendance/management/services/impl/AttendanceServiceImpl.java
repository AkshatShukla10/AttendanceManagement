package com.attendance.management.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.management.entities.Attendance;
import com.attendance.management.entities.Shift;
import com.attendance.management.entities.StaffDetail;
import com.attendance.management.repositories.AttendanceRepository;
import com.attendance.management.repositories.ShiftRepository;
import com.attendance.management.repositories.StaffDetailRepository;
import com.attendance.management.services.AttendanceService;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StaffDetailRepository staffDetailRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public Attendance markAttendance(Long staffId, Long shiftId, String imagePath) {
        StaffDetail staffDetail = staffDetailRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found"));
        Shift shift = shiftRepository.findById(shiftId).orElseThrow(() -> new RuntimeException("Shift not found"));
        Attendance attendance = new Attendance();
        attendance.setStaffDetail(staffDetail);
        attendance.setShift(shift);
        attendance.setImagePath(imagePath);
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> viewAttendanceByStaff(Long staffId) {
        StaffDetail staffDetail = staffDetailRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found"));
        return attendanceRepository.findByStaffDetail(staffDetail);
    }
}
