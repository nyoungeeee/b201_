package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByStartTimeGreaterThanEqualAndEndTimeLessThanEqual(LocalTime start, LocalTime end);
}