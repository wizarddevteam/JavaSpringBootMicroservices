package aforo255.com.controller;

import java.util.List;

import aforo255.com.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aforo255.com.entity.Transaction;
import aforo255.com.service.ITransactionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/historical")
public class HistoricalController {

    @Autowired
    ITransactionService _transaction ;
    Logger logger = LoggerFactory.getLogger(HistoricalController.class);
    @GetMapping("listar")
    public List<Transaction> listar (){

        return ( List<Transaction>)_transaction.findAll();
    }

    @GetMapping ("/GetById/{Id}")
    public ResponseEntity<?> getByAccountId (@PathVariable Integer Id){

        logger.info("CONTROLLER: Get By AccountId: {}", Id);

        Iterable< Transaction> transaction= _transaction.findByAccountId(Id);
        return ResponseEntity.ok(transaction);
    }
}
