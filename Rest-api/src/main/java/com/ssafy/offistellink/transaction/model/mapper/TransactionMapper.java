package com.ssafy.offistellink.transaction.model.mapper;

import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    public List<TransactionDto> selectAll();
}
