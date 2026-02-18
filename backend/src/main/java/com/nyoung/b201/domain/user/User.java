package com.nyoung.b201.domain.user;

import com.nyoung.b201.common.BaseEntity;
import com.nyoung.b201.domain.user.dto.UserRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String name;

    @Column
    private String team;

    @Column(nullable = false)
    private boolean admin;

    @Column(nullable = false)
    private boolean deleted;

    public User(UserRequest req) {
        this.password = req.getPassword();
        this.email = req.getEmail();
        this.provider = req.getProvider();
        this.name = req.getName();
        this.team = req.getTeam();
    }

    public void updateUser(String email, String name, String team) {
        if (StringUtils.hasText(email)) {
            this.email = email;
        }
        if (StringUtils.hasText(name)) {
            this.name = name;
        }
        if (StringUtils.hasText(team)) {
            this.team = team;
        }
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updateDeletedStatus(boolean status) {
        this.deleted = status;
    }
}
