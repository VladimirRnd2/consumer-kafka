package com.example.democonsumerkafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(value = "goodbye")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Greeting {

    @Id
    private Long id;
    private String name;
    private String age;
}
