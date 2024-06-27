package com.attendance.management.services;

import java.util.List;

import com.attendance.management.entities.Shift;

public interface ShiftService {
    Shift addShift(Shift shift);
    List<Shift> getAllShifts();
}