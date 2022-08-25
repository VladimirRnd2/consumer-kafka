package com.example.democonsumerkafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("goodbye")
@Builder
public class Goodbye {

    @Id
    private Long id;
    private String name;
    private String age;

}
