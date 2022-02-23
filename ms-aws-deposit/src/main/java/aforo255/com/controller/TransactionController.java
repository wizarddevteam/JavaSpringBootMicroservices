package aforo255.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import aforo255.com.msawsdeposit.model.api.TransactionRequest;
import aforo255.com.msawsdeposit.model.api.TransactionResponse;
import aforo255.com.msawsdeposit.model.domain.Transaction;
import aforo255.com.msawsdeposit.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService _transactionService;
    private static final String DEPOSIT = "deposit";


    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> postDepositEvent(@RequestBody TransactionRequest transactionRequest) {

        log.info("Start postDepositEvent ");

        var transaction = _transactionService.save(mapTransaction(transactionRequest, DEPOSIT));

        log.info("Finish postDepositEvent ");

        return ResponseEntity.status(HttpStatus.CREATED).body(mapTransactionResponse(transaction));
    }

    private Transaction mapTransaction(TransactionRequest transactionRequest, String type) {
        return Transaction.builder().accountId(transactionRequest.getAccountId()).type(type)
                .amount(transactionRequest.getAmount()).build();
    }

    private TransactionResponse mapTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder().id(transaction.getId()).accountId(transaction.getAccountId())
                .type(transaction.getType()).amount(transaction.getAmount()).build();
    }

}
