package com.example.testing_cqengine;

import com.example.testing_cqengine.data.ExchangeOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Service
@Slf4j
@EnableScheduling
public class SaveOrdersService implements ISaveOrdersService {

    private final OrderRepository orderRepository;
    private final BlockingQueue<ExchangeOrder> queue = new LinkedBlockingDeque<>();

    public SaveOrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(ExchangeOrder order) {
        try {
            queue.put(order);
        } catch (InterruptedException e) {
           log.error(e.getMessage());
        }
    }


    @Scheduled(fixedDelay = 1)
    private void processingOrders() {

        if (queue.size() > 0) {
//            log.info("save orders. size {}", queue.size());

            try {
                orderRepository.save(queue.take());
            } catch (InterruptedException e) {
               log.error("", e);
            }
        }
    }
}
