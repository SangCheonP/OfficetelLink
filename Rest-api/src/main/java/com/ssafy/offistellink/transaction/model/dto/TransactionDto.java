package com.ssafy.offistellink.transaction.model.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private int id; //
    private String sigungu;
    private String bungi;
    private String dangiName;
    private double area;
    private String contractDate;
    private int deal;
    private int floor;
    private String roadName;
    private double latitude;
    private double longitude;
}
