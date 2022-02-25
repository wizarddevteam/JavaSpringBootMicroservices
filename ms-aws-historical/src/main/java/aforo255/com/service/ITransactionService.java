package aforo255.com.service;

import aforo255.com.entity.Transaction;

public interface ITransactionService {
    public Transaction save (Transaction transaction);
    public Iterable<Transaction> findByAccountId (Integer accountId);
    public Iterable <Transaction> findAll();
}
