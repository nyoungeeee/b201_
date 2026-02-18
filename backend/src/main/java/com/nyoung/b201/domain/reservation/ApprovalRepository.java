package com.nyoung.b201.domain.reservation;

import com.nyoung.b201.domain.reservation.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
}
