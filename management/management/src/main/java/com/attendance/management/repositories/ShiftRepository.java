package com.attendance.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.management.entities.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
}
