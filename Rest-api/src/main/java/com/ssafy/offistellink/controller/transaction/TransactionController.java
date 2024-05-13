package com.ssafy.offistellink.controller.transaction;

import com.ssafy.offistellink.model.transaction.dto.TransactionDto;
import com.ssafy.offistellink.model.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String getAllTransactions(Model model) {
        List<TransactionDto> transactions = transactionService.selectAll();
        model.addAttribute("transactions", transactions);
        return "transactionList"; // 트랜잭션 목록을 보여줄 뷰의 이름
    }
}
