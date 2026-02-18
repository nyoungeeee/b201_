package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.entity.ReservationSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationSlotRepository extends JpaRepository<ReservationSlot, Long> {

    @Query("""
        select rs
        from ReservationSlot rs
        where rs.reservationDate = :date
          and rs.timeSlot.id in :timeSlotIds
    """)
    List<ReservationSlot> findConflicts(@Param("date") LocalDate date,
                                        @Param("timeSlotIds") List<Long> timeSlotIds);

    @Query("""
        select rs
        from ReservationSlot rs
        where rs.reservationDate between :from and :to
    """)
    List<ReservationSlot> findByDateRange(@Param("from") LocalDate from,
                                          @Param("to") LocalDate to);
}