package com.ssafy.offistellink.user.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Schema(title = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {

    @Schema(description = "이메일")
    private String email;
    @Schema(description = "이름")
    private String name;
    @Schema(description = "비밀번호")
    private String password;
    @Schema(description = "전화번호")
    private String phone;
    @Schema(description = "포인트")
    protected Integer point;
    @Schema(description = "관리자 권한")
    private boolean admin;
    @Schema(description = "가입일")
    private Timestamp createdAt;
    @Schema(description = "refreshToken")
    private String refreshToken;
    @Schema(description = "프로필 이미지 URL")
    private String profileImageUrl;
    @Schema(description = "테두리")
    private int borderId;
    @Schema(description = "경험치")
    private int expId;
}

