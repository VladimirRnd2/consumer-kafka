package com.example.democonsumerkafka.consume;

import com.example.democonsumerkafka.repository.GoodbyeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.concurrent.atomic.AtomicInteger;


@Component
public class Consumer {

    private final GoodbyeRepository goodbyeRepository;
    private static final Logger log = LoggerFactory.getLogger(Consumer.class.getName());
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final KafkaSender<String, String> sender;

    @Autowired
    public Consumer(GoodbyeRepository goodbyeRepository, KafkaSender<String, String> sender) {
        this.goodbyeRepository = goodbyeRepository;
        this.sender = sender;
    }

    @KafkaListener(topics = "bookRq", groupId = "foo", containerFactory = "kafkaListenerContainerFactory")
    public void listen(ConsumerRecord<String, Object> record) throws InterruptedException {
        AtomicInteger count = new AtomicInteger();
        String key = record.key();
        sender.send(goodbyeRepository.findAll().buffer().map(i -> {
                    System.out.println(i);
                    try {
                        String s = objectMapper.writeValueAsString(i);
                        return SenderRecord.create(new ProducerRecord<>("bookRs", key , s ), count.getAndIncrement());
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return null;
                }))
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }
}
