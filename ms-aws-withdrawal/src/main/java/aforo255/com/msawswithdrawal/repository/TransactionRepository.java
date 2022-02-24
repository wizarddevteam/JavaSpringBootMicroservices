package aforo255.com.msawswithdrawal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import aforo255.com.msawswithdrawal.model.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Integer> {

}
