package com.attendance.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "roster")
@Getter
@Setter
@NoArgsConstructor
public class Roster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private StaffDetail staffDetail;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

}

