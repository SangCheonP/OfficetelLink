package com.ssafy.offistellink.transaction.controller;

import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionSearchDto;
import com.ssafy.offistellink.transaction.model.dto.TransactionTrendDto;
import com.ssafy.offistellink.transaction.model.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.sql.SQLException;
import java.util.List;

@Tag(name = "Transaction", description = "거래 내역 관리")
@RequestMapping("/transactions")
@CrossOrigin("*")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //모든 거래내역 조회
    @Operation(summary = "모든 거래내역 조회", description = "모든 대전 오피스텔 정보 조회")
    @GetMapping("")
    public ResponseEntity<List<TransactionDto>> listAll() throws SQLException {
        List<TransactionDto> list = transactionService.getAllTransactions();
        if (list.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(list);
        }
    }

    @Operation(summary = "조건에 맞는 오피스텔 조회", description = "구,동,도로명")
    @PostMapping("/search")
    public ResponseEntity<List<TransactionSearchDto>> listConditions(@RequestBody TransactionSearchDto searchDto) throws SQLException {
        List<TransactionSearchDto> searchDtoList = transactionService.getTransactionByConditions(searchDto);
        if (searchDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(searchDtoList);
        }
    }

    @Operation(summary = "월별 월세 추이 조회", description = "날짜, 거래가")
    @GetMapping("/price")
    public ResponseEntity<List<TransactionTrendDto>> monthlyTrend() throws SQLException {
        List<TransactionTrendDto> trendDtoList = transactionService.getMonthMarketTrend();
        if (trendDtoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(trendDtoList);
        }
    }
}
