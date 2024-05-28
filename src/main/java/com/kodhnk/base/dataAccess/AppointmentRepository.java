package com.kodhnk.base.dataAccess;

import com.kodhnk.base.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}