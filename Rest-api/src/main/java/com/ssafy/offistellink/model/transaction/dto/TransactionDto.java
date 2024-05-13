package com.ssafy.offistellink.model.transaction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public TransactionDto() {
    }

    public TransactionDto(int id, String sigungu, String bungi, String dangiName, double area, String contractDate, int deal, int floor, String roadName) {
        this.id = id;
        this.sigungu = sigungu;
        this.bungi = bungi;
        this.dangiName = dangiName;
        this.area = area;
        this.contractDate = contractDate;
        this.deal = deal;
        this.floor = floor;
        this.roadName = roadName;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id=" + id +
                ", sigungu='" + sigungu + '\'' +
                ", bungi='" + bungi + '\'' +
                ", dangiName='" + dangiName + '\'' +
                ", area=" + area +
                ", contractDate='" + contractDate + '\'' +
                ", deal=" + deal +
                ", floor=" + floor +
                ", roadName='" + roadName + '\'' +
                '}';
    }
}
