package com.ssafy.offistellink.mail.controller;

import com.ssafy.offistellink.mail.model.dto.MailCheckDto;
import com.ssafy.offistellink.mail.model.dto.MailRequestDto;
import com.ssafy.offistellink.mail.model.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.regex.Pattern;

@Tag(name = "Mail", description = "메일 관련 API")
@RestController
@CrossOrigin("*")
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // 사용자에게 메일 보내는 API
    @PostMapping("/sendAuthCode")
    public ResponseEntity<String> sendAuthCode(@RequestBody @Valid MailRequestDto mailDto) {
        System.out.println("이메일 인증 요청이 들어옴");
        System.out.println("이메일 인증 이메일 : " + mailDto.getMail());

        if (!EMAIL_PATTERN.matcher(mailDto.getMail()).matches()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format");
        }

        String code = mailService.joinMail(mailDto.getMail());
        return ResponseEntity.ok(code);
    }

    @PostMapping("/checkAuthCode")
    public ResponseEntity<Boolean> checkAuthCode(@RequestBody @Valid MailCheckDto mailCheckDto) {
        Boolean checked = mailService.checkAuthNumber(mailCheckDto.getMail(), mailCheckDto.getAuthNum());
        if (checked) {
            return ResponseEntity.ok(checked);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "인증 실패!"); // 보다 구체적인 예외 처리
        }
    }
}
