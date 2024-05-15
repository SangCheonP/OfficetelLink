package com.ssafy.offistellink.mail.model.service;

import com.ssafy.offistellink.mail.model.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    // 인증번호 체크
    public boolean checkAuthNumber(String mail, String authNum) {
        if(redisUtil.getData(authNum)==null){
            return false;
        }
        else if(redisUtil.getData(authNum).equals(mail)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    // 임의의 6자리 양수 반환
    public int makeRandomNumber() {
        Random r = new Random();

        int authNum = 0;
        String tempAuthNum = "";

        for(int i = 0; i < 6; i++){
            tempAuthNum += Integer.toString(r.nextInt(10));
        }

        authNum = Integer.parseInt(tempAuthNum);
        return authNum;
    }

    @Override
    // mail을 어디서 보내는지, 어디로 보내는지, 인증 번호를 html 형식으로 어떻게 보내는지 작성
    public String joinMail(String mail) {
        int authNum = makeRandomNumber();

        String from = "pswlove38@gmail.com";
        String to = mail;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "<h1>인증 번호 발송</h1>" + // mail html
                "<br><br>" +
                "인증 번호는 " + authNum + "입니다.";

        sendMail(from, to, title, content, Integer.toString(authNum));

        return Integer.toString(authNum);
    }

    @Override
    // mail 전송
    public void sendMail(String from, String to, String title, String content, String authNum) {
        MimeMessage message = mailSender.createMimeMessage(); // JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");//이메일 메시지와 관련된 설정을 수행합니다.
            // true를 전달하여 multipart 형식의 메시지를 지원하고, "utf-8"을 전달하여 문자 인코딩을 설정

            helper.setFrom(from);//이메일의 발신자 주소 설정
            helper.setTo(to);//이메일의 수신자 주소 설정
            helper.setSubject(title);//이메일의 제목을 설정
            helper.setText(content,true);//이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.

            mailSender.send(message);

        }catch (MessagingException e){
            e.printStackTrace();
        }

        // 5분 동안 인증번호가 살게 함
        redisUtil.setDataExpire(authNum, to, 60*5L);
    }
}
