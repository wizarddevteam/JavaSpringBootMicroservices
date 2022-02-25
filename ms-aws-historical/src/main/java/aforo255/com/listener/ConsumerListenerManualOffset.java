package aforo255.com.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import aforo255.com.service.TransactionTopic;
@Component
public class ConsumerListenerManualOffset  implements AcknowledgingMessageListener<Integer,String>{

    private Logger log = LoggerFactory.getLogger(ConsumerListenerManualOffset.class);

    @Autowired
    private TransactionTopic _tansactionTopic;


    @Override
    @KafkaListener(topics = {"transaction-events"})
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment)  {
        // TODO Auto-generated method stub
        log.info("*************** MICROSERVICE HISTORICAL 2*******************");
        log.info("ConsumerRecord : {}", consumerRecord.value());
        try {
            _tansactionTopic.proccessTopicEvent(consumerRecord);
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
