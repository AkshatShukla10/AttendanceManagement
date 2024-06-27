package com.attendance.management.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.management.entities.Attendance;
import com.attendance.management.entities.StaffDetail;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByStaffDetail(StaffDetail staffDetail);
}