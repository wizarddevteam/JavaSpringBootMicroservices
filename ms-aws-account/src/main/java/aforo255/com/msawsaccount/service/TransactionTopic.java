package aforo255.com.msawsaccount.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aforo255.com.msawsaccount.model.domain.Transaction;
import aforo255.com.msawsaccount.model.entity.AccountEntity;
@Service
public class TransactionTopic {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IAccountService accountService;
    private Logger log = LoggerFactory.getLogger(TransactionTopic.class);

    public void proccessTopicEvent (ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {


        double newAmount = 0 ;
        AccountEntity account = new	 AccountEntity();
        Transaction event = objectMapper.readValue(consumerRecord.value(), Transaction.class);
        account = accountService.findById(event.getAccountId());
        switch(event.getType()) {
            case "deposit":
                newAmount =  account.getTotalAmount() + event.getAmount();
                break ;

            case "withdrawal":
                newAmount =  account.getTotalAmount()  - event.getAmount();
                break ;
        }
        account.setTotalAmount(newAmount);
        log.info("Actulizando N° cuenta ***"+event.getAccountId());
        accountService.save(account);

    }


}
