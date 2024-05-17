package com.ssafy.offistellink.transaction.model.service;


import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionSearchDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionTrendDto;

import java.sql.SQLException;
import java.util.List;

public interface TransactionService {
    List<TransactionDto> getAllTransactions() throws SQLException;
    List<TransactionSearchDto> getTransactionByConditions(TransactionSearchDto dto) throws SQLException;
    List<TransactionTrendDto> getMonthMarketTrend() throws SQLException;
}
