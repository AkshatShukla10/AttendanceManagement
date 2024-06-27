package com.attendance.management.services.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.management.entities.Roster;
import com.attendance.management.entities.StaffDetail;
import com.attendance.management.repositories.RosterRepository;
import com.attendance.management.repositories.StaffDetailRepository;
import com.attendance.management.services.RosterService;

import java.util.List;

@Service
public class RosterServiceImpl implements RosterService {

    @Autowired
    private RosterRepository rosterRepository;

    @Autowired
    private StaffDetailRepository staffDetailRepository;

    @Override
    public Roster addRoster(Roster roster) {
        return rosterRepository.save(roster);
    }

    @Override
    public Roster updateRoster(Long rosterId, Roster roster) {
        Roster existingRoster = rosterRepository.findById(rosterId).orElseThrow(() -> new RuntimeException("Roster not found"));
        existingRoster.setShift(roster.getShift());
        existingRoster.setWorkDate(roster.getWorkDate());
        return rosterRepository.save(existingRoster);
    }

    @Override
    public List<Roster> viewRoster() {
        return rosterRepository.findAll();
    }

    @Override
    public void editStaffDetails(Long staffId, StaffDetail staffDetail) {
        StaffDetail existingStaffDetail = staffDetailRepository.findById(staffId).orElseThrow(() -> new RuntimeException("Staff not found"));
        existingStaffDetail.setName(staffDetail.getName());
        existingStaffDetail.setEmail(staffDetail.getEmail());
        existingStaffDetail.setPhoneNumber(staffDetail.getPhoneNumber());
        staffDetailRepository.save(existingStaffDetail);
    }
}
