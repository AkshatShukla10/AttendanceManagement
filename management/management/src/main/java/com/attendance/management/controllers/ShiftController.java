package com.attendance.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.attendance.management.entities.Shift;
import com.attendance.management.services.ShiftService;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Shift> addShift(@RequestBody Shift shift) {
        return ResponseEntity.ok(shiftService.addShift(shift));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('MANAGER', 'STAFF')")
    public ResponseEntity<List<Shift>> getAllShifts() {
        return ResponseEntity.ok(shiftService.getAllShifts());
    }
}
