package com.nyoung.b201.domain.user.dto;

import com.nyoung.b201.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String team;
    private boolean admin;
    private boolean deleted;


    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
//        this.name = user.getName();
//        this.team = user.getTeam();
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
//                .name(user.getName())
//                .team(user.getTeam())
//                .admin(user.isAdmin())
//                .deleted(user.isDeleted())
                .build();
    }

    public static List<UserResponse> from(List<User> users) {
        return users.stream().map(UserResponse::from).toList();
    }
}
