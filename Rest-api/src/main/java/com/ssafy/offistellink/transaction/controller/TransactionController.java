package com.ssafy.offistellink.transaction.controller;

import com.ssafy.offistellink.transaction.model.dto.TransactionDto;
import com.ssafy.offistellink.transaction.model.service.TransactionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Transaction", description = "거래 내역 관리")
@RequestMapping("/transactions")
@CrossOrigin("*")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("")
    public String getAllTransactions(Model model) {
        List<TransactionDto> transactions = transactionService.selectAll();
        model.addAttribute("transactions", transactions);
        return "transactionList"; // 트랜잭션 목록을 보여줄 뷰의 이름
    }
}
