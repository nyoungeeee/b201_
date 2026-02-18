package com.nyoung.b201.domain.reservation.entity;

import com.nyoung.b201.common.BaseEntity;
import com.nyoung.b201.domain.reservation.entity.enums.RequesterType;
import com.nyoung.b201.domain.reservation.entity.enums.ReservationRepeatType;
import com.nyoung.b201.domain.reservation.entity.enums.ReservationRequestStatus;
import com.nyoung.b201.domain.team.entity.Team;
import com.nyoung.b201.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(
        name = "reservation_requests",
        indexes = {
                @Index(name = "idx_rr_status", columnList = "status"),
                @Index(name = "idx_rr_created_at", columnList = "created_at")
        }
)
@Getter
@NoArgsConstructor
public class ReservationRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_request_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "requester_type", nullable = false, length = 10)
    private RequesterType requesterType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_user_id")
    private User requesterUser; // 개인 예약일 때

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_team_id")
    private Team requesterTeam; // 팀 예약일 때

    @Enumerated(EnumType.STRING)
    @Column(name = "repeat_type", nullable = false, length = 10)
    private ReservationRepeatType repeatType;

    // ONCE면 date만, WEEKLY면 start/end + repeatDays 사용
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    /**
     * WEEKLY일 때: "MON,TUE,THU" 형태로 저장 (간단 버전)
     * (원하면 Converter/별도 테이블로 확장 가능)
     */
    @Column(name = "repeat_days", length = 50)
    private String repeatDays;

    @Column(name = "note", length = 500)
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ReservationRequestStatus status;

    // ---- 상태 변경 메서드 (서비스에서 호출) ----
    public void markApproved() { this.status = ReservationRequestStatus.APPROVED; }
    public void markRejected() { this.status = ReservationRequestStatus.REJECTED; }
    public void markCanceled() { this.status = ReservationRequestStatus.CANCELED; }
}