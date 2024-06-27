package com.attendance.management.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.attendance.management.entities.Roster;
import com.attendance.management.entities.StaffDetail;
import com.attendance.management.services.RosterService;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/roster")
public class RosterController {

    @Autowired
    private RosterService rosterService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Roster> addRoster(@RequestBody Roster roster) {
        return ResponseEntity.ok(rosterService.addRoster(roster));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Roster> updateRoster(@PathVariable Long id, @RequestBody Roster roster) {
        return ResponseEntity.ok(rosterService.updateRoster(id, roster));
    }

    @GetMapping("/view")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<List<Roster>> viewRoster() {
        return ResponseEntity.ok(rosterService.viewRoster());
    }

    @PutMapping("/edit/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseEntity<Map<String, String>> editStaffDetails(@PathVariable Long id, @RequestBody StaffDetail staffDetail) {
        rosterService.editStaffDetails(id, staffDetail);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Staff details edited successfully");
        return ResponseEntity.ok(response);
    }
}
