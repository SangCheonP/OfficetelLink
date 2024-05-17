package com.ssafy.offistellink.transaction.model.dto;

import lombok.Data;

@Data
public class TransactionTrendDto {
    private String contractDate;
    private int avgDeal; //월별 평균 거래가
}
