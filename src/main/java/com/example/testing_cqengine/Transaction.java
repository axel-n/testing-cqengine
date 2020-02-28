package com.example.testing_cqengine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

@Data
@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String internalId;
    private String externalId;

    private Status status;
    private double amount;

    private long createAt;
    private long updatedAt;

    @Transient // skip in db
    private boolean isChanged = false;
}
