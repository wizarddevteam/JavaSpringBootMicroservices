package aforo255.com.msawsaccount.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import aforo255.com.msawsaccount.service.TransactionTopic;
//@Component
public class ConsumerListener {


    @Autowired
    private TransactionTopic _transactionTopic;

    private Logger log = LoggerFactory.getLogger(ConsumerListener.class);
    @KafkaListener(topics = {"transaction-events"})
    public void OnMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
        log.info("*************** MICROSERVICE ACCOUNT *******************");
        log.info("ConsumerRecord : {}", consumerRecord.value());

        _transactionTopic.proccessTopicEvent(consumerRecord);
    }

}
