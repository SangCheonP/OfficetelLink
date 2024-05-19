package com.ssafy.offistellink.transaction.model.dto;

import lombok.Data;

@Data
public class TransactionSearchDto {
    private String gu; //sigungu
    private String dong; //sigungu
    private String roadName;
    private String dangiName;
    private String bungi;
    private int deal;
    private double latitude;
    private double longitude;
}
