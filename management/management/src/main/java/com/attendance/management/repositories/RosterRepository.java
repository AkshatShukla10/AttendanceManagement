package com.attendance.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.management.entities.Roster;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Long> {
}
