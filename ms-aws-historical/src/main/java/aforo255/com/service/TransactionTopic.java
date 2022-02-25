package aforo255.com.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import aforo255.com.entity.Transaction;
@Service
public class TransactionTopic {

    @Autowired
    private ITransactionService service;
    @Autowired
    private ObjectMapper objectMapper;
    private Logger log = LoggerFactory.getLogger(TransactionTopic.class);
    public void proccessTopicEvent (ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {

        Transaction event = objectMapper.readValue(consumerRecord.value(), Transaction.class);
        log.info("transactionId:{}",event.getId());
        service.save(event);

    }

}
