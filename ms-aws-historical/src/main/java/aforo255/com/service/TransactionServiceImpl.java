package aforo255.com.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import aforo255.com.entity.Transaction;
import aforo255.com.repository.TransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CacheConfig(cacheNames = "transaction")
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository _repository;
    Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    @Override
    public Transaction save(Transaction transaction) {
        // TODO Auto-generated method stub
        return _repository.save(transaction);
    }

    @Override
    @Cacheable(cacheNames = "transaction", key = "#accountId")
    public Iterable<Transaction> findByAccountId(Integer accountId) {
        // TODO Auto-generated method stub
        logger.info("Service: Get Find By AccountId: {}",accountId);
        return _repository.findByAccountId(accountId);
    }

    @Override
    public Iterable<Transaction> findAll() {
        // TODO Auto-generated method stub
        return _repository.findAll();
    }

}
