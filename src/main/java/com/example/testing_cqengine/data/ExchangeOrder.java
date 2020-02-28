package com.example.testing_cqengine.data;

import com.googlecode.cqengine.attribute.Attribute;
import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.query.option.QueryOptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Slf4j
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeOrder {

    @Id
    private String id;


    private String internalId;
    private String externalId;

    private Status status;
    private double amount;

    private long createAt;
    private long updatedAt;


    public static final Attribute<ExchangeOrder, String> INTERNAL_ID = new SimpleAttribute<>("internalId") {
        public String getValue(ExchangeOrder transaction, QueryOptions queryOptions) {
            return transaction.internalId;
        }
    };
}
