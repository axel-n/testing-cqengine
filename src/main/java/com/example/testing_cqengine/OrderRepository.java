package com.example.testing_cqengine;

import com.example.testing_cqengine.data.ExchangeOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<ExchangeOrder, Long> {}
