package com.attendance.management.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.management.entities.Shift;
import com.attendance.management.repositories.ShiftRepository;
import com.attendance.management.services.ShiftService;

import java.util.List;

@Service
public class ShiftServiceImpl implements ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Override
    public Shift addShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }
}

