package com.attendance.management.services;

import java.util.List;

import com.attendance.management.entities.Roster;
import com.attendance.management.entities.StaffDetail;

public interface RosterService {
    Roster addRoster(Roster roster);
    Roster updateRoster(Long rosterId, Roster roster);
    List<Roster> viewRoster();
    void editStaffDetails(Long staffId, StaffDetail staffDetail);
}