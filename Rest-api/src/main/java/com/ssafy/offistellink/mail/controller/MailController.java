package com.ssafy.offistellink.mail.controller;

import com.ssafy.offistellink.mail.model.dto.MailRequestDto;
import com.ssafy.offistellink.mail.model.service.MailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Mail", description = "메일 관련 API")
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    @Autowired
    private MailService mailService;

    // 사용자에게 메일 보내는 API
    @PostMapping("/sendAuthCode")
    public String sendAuthCode(@RequestBody @Valid MailRequestDto mailDto){
        System.out.println("이메일 인증 요청이 들어옴");
        System.out.println("이메일 인증 이메일 : " + mailDto.getMail());

        return mailService.joinMail(mailDto.getMail());
    }

    @PostMapping("/checkAuthCode")
    public String checkAuthCode(@RequestBody @Valid MailRequestDto mailDto){
        Boolean cheked = true;
        if(cheked){
            return "ok";
        }else{
            throw new NullPointerException("인증 실패!");
        }

    }
}
