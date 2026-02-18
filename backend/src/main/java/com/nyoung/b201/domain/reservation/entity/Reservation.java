package com.nyoung.b201.domain.reservation.entity;

import com.nyoung.b201.common.BaseEntity;
import com.nyoung.b201.domain.reservation.entity.enums.ReservationRequestStatus;
import com.nyoung.b201.domain.team.entity.Team;
import com.nyoung.b201.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(
        name = "reservation",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"reservation_date", "start_time", "end_time"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    /**
     * 개인 예약일 경우 필수
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 팀 예약일 경우 필수
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationRequestStatus status;

    /**
     * 반복 예약 묶음용 ID
     */
    @Column(name = "repeat_group_id")
    private UUID repeatGroupId;

    /**
     * 관리자 승인 정보
     */
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    /* ================== 생성 로직 ================== */

    public static Reservation createPersonal(
            User user,
            LocalDate date,
            LocalTime start,
            LocalTime end,
            UUID repeatGroupId
    ) {
        validateTime(start, end);

        Reservation r = new Reservation();
        r.user = user;
        r.reservationDate = date;
        r.startTime = start;
        r.endTime = end;
        r.status = ReservationRequestStatus.PENDING;
        r.repeatGroupId = repeatGroupId;
        return r;
    }

    public static Reservation createTeam(
            Team team,
            LocalDate date,
            LocalTime start,
            LocalTime end,
            UUID repeatGroupId
    ) {
        validateTime(start, end);

        Reservation r = new Reservation();
        r.team = team;
        r.reservationDate = date;
        r.startTime = start;
        r.endTime = end;
        r.status = ReservationRequestStatus.PENDING;
        r.repeatGroupId = repeatGroupId;
        return r;
    }

    /* ================== 비즈니스 메서드 ================== */

    public void approve(User admin) {
        this.status = ReservationRequestStatus.APPROVED;
        this.approvedBy = admin;
        this.approvedAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = ReservationRequestStatus.REJECTED;
    }

    public void cancel() {
        this.status = ReservationRequestStatus.CANCELED;
    }

    /* ================== 검증 ================== */

    private static void validateTime(LocalTime start, LocalTime end) {
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("예약 시작 시간은 종료 시간보다 빨라야 합니다.");
        }
    }
}
