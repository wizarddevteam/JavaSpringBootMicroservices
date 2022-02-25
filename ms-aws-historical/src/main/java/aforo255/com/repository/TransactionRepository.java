package aforo255.com.repository;

import aforo255.com.entity.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    @Query("{'accountId':?0}")
    public Iterable<Transaction> findByAccountId(Integer accountId);
}
