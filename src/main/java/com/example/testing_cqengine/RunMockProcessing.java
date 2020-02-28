package com.example.testing_cqengine;

import com.example.testing_cqengine.data.ExchangeOrder;
import com.example.testing_cqengine.data.Status;
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
    public CommandLineRunner run(SaveOrdersService saveOrdersService) {
        return (args) -> {

            List<ExchangeOrder> loadedOrders = emulateLoadOrders();
            log.info("loadedOrders size {}", loadedOrders.size());

            loadedOrders.forEach(saveOrdersService::save);

            log.info("data saved");


//            IndexedCollection<Order> cachedOrders = new ConcurrentIndexedCollection<>(WrappingPersistence.aroundCollection(loadedOrders));
//            cachedOrders.addIndex(UniqueIndex.onAttribute(Order.INTERNAL_ID));


        };
    }

    private List<ExchangeOrder> emulateLoadOrders() {

        List<ExchangeOrder> orders = new ArrayList<>();

        for (int i = 0; i < 10_000; i++) {

            ExchangeOrder order = createNewOrder();
            orders.add(order);

            order.setId(UUID.randomUUID().toString());
            order.setUpdatedAt(System.currentTimeMillis());
            order.setStatus(Status.PART_FILLED);
            orders.add(order);

            order.setId(UUID.randomUUID().toString());
            order.setUpdatedAt(System.currentTimeMillis());
            order.setStatus(Status.FULL_FILLED);
            orders.add(order);
        }

        return orders;
    }

    private ExchangeOrder createNewOrder() {
        ExchangeOrder order = new ExchangeOrder();
        order.setId(UUID.randomUUID().toString());
        order.setInternalId(UUID.randomUUID().toString());
        order.setExternalId(UUID.randomUUID().toString());
        order.setCreateAt(System.currentTimeMillis());
        order.setAmount(generateRandomInRange(100, 10_000));
        order.setStatus(Status.ACCEPTED);
        return order;
    }

    private double generateRandomInRange(int min, int max) {
        return new Random().nextDouble() * (max - min) + min;
    }
}
