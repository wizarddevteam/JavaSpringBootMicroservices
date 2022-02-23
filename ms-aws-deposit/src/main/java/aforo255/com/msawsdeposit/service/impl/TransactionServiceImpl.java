package aforo255.com.msawsdeposit.service.impl;

import aforo255.com.msawsdeposit.model.domain.Transaction;
import aforo255.com.msawsdeposit.model.entity.TransactionEntity;
import aforo255.com.msawsdeposit.producer.DepositEventProducer;
import aforo255.com.msawsdeposit.repository.TransactionRepository;
import aforo255.com.msawsdeposit.service.ITransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private TransactionRepository _transactionRepository;

    @Autowired
    DepositEventProducer eventProducer;

    @Override
    public Transaction save(Transaction transaction) {
        var transactionEntity = _transactionRepository.save(mapTransactionEntity(transaction));

        //sending to Kafka
        try{
            eventProducer.sendDepositEvent(transactionEntity);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return mapTransaction (transactionEntity);
    }

    private TransactionEntity mapTransactionEntity(Transaction transaction) {
        return TransactionEntity.builder().accountId(transaction.getAccountId()).type(transaction.getType())
                .amount(transaction.getAmount()).build();
    }

    private Transaction mapTransaction(TransactionEntity transactionEntity) {
        return Transaction.builder().id(transactionEntity.getId()).accountId(transactionEntity.getAccountId())
                .type(transactionEntity.getType()).amount(transactionEntity.getAmount()).build();
    }
}
