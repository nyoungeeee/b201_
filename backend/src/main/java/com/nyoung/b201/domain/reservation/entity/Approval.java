package com.nyoung.b201.domain.reservation.entity;

import com.nyoung.b201.common.BaseEntity;
import com.nyoung.b201.domain.reservation.entity.enums.ApprovalDecision;
import com.nyoung.b201.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "reservation_approvals",
        indexes = {@Index(name = "idx_ra_decided_at", columnList = "decided_at")}
)
@Getter
@NoArgsConstructor
public class Approval extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "approval_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_request_id", nullable = false, unique = true)
    private ReservationRequest request;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private ApprovalDecision decision; // APPROVED / REJECTED

    @Column(length = 500)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_user_id", nullable = false)
    private User admin;

    @Column(name = "decided_at", nullable = false)
    private LocalDateTime decidedAt;

    public static Approval approve(ReservationRequest request, User admin, String reason) {
        Approval a = new Approval();
        a.request = request;
        a.decision = ApprovalDecision.APPROVED;
        a.reason = reason;
        a.admin = admin;
        a.decidedAt = LocalDateTime.now();
        return a;
    }

    public static Approval reject(ReservationRequest request, User admin, String reason) {
        Approval a = new Approval();
        a.request = request;
        a.decision = ApprovalDecision.REJECTED;
        a.reason = reason;
        a.admin = admin;
        a.decidedAt = LocalDateTime.now();
        return a;
    }
}