package com.attendance.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.management.entities.StaffDetail;

@Repository
public interface StaffDetailRepository extends JpaRepository<StaffDetail, Long> {
}
