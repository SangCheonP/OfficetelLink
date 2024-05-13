package com.ssafy.offistellink.model.transaction.service;


import com.ssafy.offistellink.model.transaction.dto.TransactionDto;

import java.util.List;

public interface TransactionService {
    public List<TransactionDto> selectAll();
}
