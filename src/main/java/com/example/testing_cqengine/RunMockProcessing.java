package com.example.testing_cqengine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Component
public class RunMockProcessing {

    @Bean
    public CommandLineRunner run(TransactionRepository transactionRepository) {
        return (args) -> {

            // emulate load stored data
            List<Transaction> successTransactions = createSuccessTransactions();
            transactionRepository.saveAll(successTransactions);

        };
    }

    private List<Transaction> createSuccessTransactions() {

        List<Transaction> successTransactions = new ArrayList<>();

        for (int i = 0; i < 1_000; i++) {

            Transaction transaction = createNewTransaction();
            successTransactions.add(transaction);

            transaction.setUpdatedAt(System.currentTimeMillis());
            transaction.setStatus(Status.PROCESSING);
            successTransactions.add(transaction);

            transaction.setUpdatedAt(System.currentTimeMillis());
            transaction.setStatus(Status.SUCCESS);
            successTransactions.add(transaction);
        }

        return successTransactions;
    }

    private Transaction createNewTransaction() {
        Transaction transaction = new Transaction();
        transaction.setAmount(generateRandomInRange(100, 10_000));
        transaction.setStatus(Status.NEW);
        transaction.setInternalId(UUID.randomUUID().toString());
        transaction.setExternalId(UUID.randomUUID().toString());
        transaction.setCreateAt(System.currentTimeMillis());

        return transaction;
    }

    private double generateRandomInRange(int min, int max) {
        return new Random().nextDouble() * (max - min) + min;
    }
}
