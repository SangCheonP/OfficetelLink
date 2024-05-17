package com.ssafy.offistellink.transaction.model.mapper;

import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionSearchDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionTrendDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    List<TransactionDto> getAllTransactions();
    List<TransactionSearchDto> getTransactionByConditions(TransactionSearchDto dto);
    List<TransactionTrendDto> getMonthMarketTrend();
}
