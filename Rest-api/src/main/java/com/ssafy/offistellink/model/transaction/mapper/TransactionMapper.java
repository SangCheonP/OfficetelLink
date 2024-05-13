package com.ssafy.offistellink.model.transaction.mapper;

import com.ssafy.offistellink.model.transaction.dto.TransactionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionMapper {

    public List<TransactionDto> selectAll();
}
