package com.ssafy.offistellink.transaction.model.service;

import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionSearchDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionTrendDto;
import com.ssafy.offistellink.transaction.model.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> getAllTransactions() throws SQLException {
        return transactionMapper.getAllTransactions();
    }

    @Override
    public List<TransactionSearchDto> getTransactionByConditions(TransactionSearchDto searchDto) throws SQLException {
        return transactionMapper.getTransactionByConditions(searchDto);
    }
    @Override
    public List<TransactionTrendDto> getMonthMarketTrend() throws SQLException {
        return transactionMapper.getMonthMarketTrend();
    }
}
