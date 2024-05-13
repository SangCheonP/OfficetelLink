package com.ssafy.offistellink.transaction.model.service;


import com.ssafy.offistellink.transaction.model.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    public List<TransactionDto> getAllTransactions();
}
