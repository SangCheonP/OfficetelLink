package com.ssafy.offistellink.transaction.model.service;

import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import com.ssafy.offistellink.transaction.model.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public List<TransactionDto> selectAll() {
        return transactionMapper.selectAll();
    }
}
