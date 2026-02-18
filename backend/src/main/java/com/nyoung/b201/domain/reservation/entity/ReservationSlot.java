package com.nyoung.b201.domain.reservation.entity;

import com.nyoung.b201.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "reservation_slots",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_slots_date_time",
                        columnNames = {"reservation_date", "time_slot_id"}
                )
        },
        indexes = {
                @Index(name = "idx_slots_date", columnList = "reservation_date")
        }
)
@Getter
@NoArgsConstructor
public class ReservationSlot extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_slot_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_request_id", nullable = false)
    private ReservationRequest request;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot;

    public static ReservationSlot of(ReservationRequest request, LocalDate date, TimeSlot slot) {
        ReservationSlot rs = new ReservationSlot();
        rs.request = request;
        rs.reservationDate = date;
        rs.timeSlot = slot;
        return rs;
    }
}