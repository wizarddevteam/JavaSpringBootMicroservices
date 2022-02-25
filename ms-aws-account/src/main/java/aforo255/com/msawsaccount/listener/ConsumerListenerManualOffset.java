package aforo255.com.msawsaccount.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;


import aforo255.com.msawsaccount.service.TransactionTopic;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ConsumerListenerManualOffset  implements AcknowledgingMessageListener<Integer,String> {

    @Autowired
    private TransactionTopic _transactionTopic;


    @Override
    @KafkaListener(topics = {"transaction-events"})
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {
        log.info("*************** MICROSERVICE ACCOUNT *******************");
        log.info("ConsumerRecord : {}", data.value());

        try {
            _transactionTopic.proccessTopicEvent(data);
            acknowledgment.acknowledge();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

