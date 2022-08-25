package com.example.democonsumerkafka.repository;

import com.example.democonsumerkafka.model.Goodbye;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface GoodbyeRepository extends ReactiveCrudRepository<Goodbye ,Long> {

    Flux<Goodbye> findByName(String name);
}
