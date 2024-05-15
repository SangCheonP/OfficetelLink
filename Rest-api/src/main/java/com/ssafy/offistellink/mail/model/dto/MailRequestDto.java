package com.ssafy.offistellink.mail.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MailRequestDto {
    @Email // 이메일 형식이 맞는지 검증
    @NotEmpty(message = "이메일을 입력해 주세요")
    private String mail;
}
