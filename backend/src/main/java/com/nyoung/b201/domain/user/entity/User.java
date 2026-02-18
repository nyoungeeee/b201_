package com.nyoung.b201.domain.user.entity;

import com.nyoung.b201.TeamMember;
import com.nyoung.b201.common.BaseEntity;
import com.nyoung.b201.domain.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users",
        indexes = {
                @Index(name = "idx_users_kakao_id", columnList = "kakao_id"),
                @Index(name = "idx_users_global_role", columnList = "global_role")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User extends BaseEntity {   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
private Long id;

    @Column(name = "kakao_id", nullable = false)
    private String kakaoId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "global_role", nullable = false, length = 20)
    private GlobalRole globalRole;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "user")
    private List<TeamMember> teamMembers = new ArrayList<>();

    /* =========================
       도메인 로직
       ========================= */

    public boolean isActive() {
        return deletedAt == null;
    }

    public boolean canReserve() {
        return globalRole != GlobalRole.GUEST;
    }

    public void changeRole(GlobalRole newRole) {
        this.globalRole = newRole;
    }

    public void softDelete() {
        this.deletedAt = LocalDateTime.now();
    }
    public User(UserRequest req) {
        this.email = req.getEmail();
        this.nickname = req.getName();
//        this.team = req.getTeam();
    }

    public void updateUser(String email, String name, String team) {
        if (StringUtils.hasText(email)) {
            this.email = email;
        }
        if (StringUtils.hasText(name)) {
            this.nickname = name;
        }
//        if (StringUtils.hasText(team)) {
//            this.teamMembers = team;
//        }
    }

//    public void changePassword(String encodedPassword) {
//        this.password = encodedPassword;
//    }

//    public void updateDeletedStatus(boolean status) {
//        this.deleted = status;
//    }
}
