package com.attendance.management.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private StaffDetail staffDetail;

    @ManyToOne
    @JoinColumn(name = "shift_id", nullable = false)
    private Shift shift;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }

}

