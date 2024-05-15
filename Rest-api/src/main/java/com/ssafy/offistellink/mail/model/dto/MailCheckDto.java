package com.ssafy.offistellink.mail.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MailCheckDto {
    @Email
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String mail;

    @NotEmpty(message = "인증 번호를 입력해주세요.")
    private String authNum;
}
