package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.entity.ReservationRequest;
import com.nyoung.b201.domain.reservation.entity.enums.ReservationRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRequestRepository extends JpaRepository<ReservationRequest, Long> {
    List<ReservationRequest> findByStatusOrderByCreatedAtAsc(ReservationRequestStatus status);
}