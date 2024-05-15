package com.ssafy.offistellink.mail.model.service;

public interface MailService {
    public boolean checkAuthNumber(String mail, String authNum);
    public int makeRandomNumber();
    public String joinMail(String mail);
    public void sendMail(String from, String to, String title, String content, String authNum);
}
