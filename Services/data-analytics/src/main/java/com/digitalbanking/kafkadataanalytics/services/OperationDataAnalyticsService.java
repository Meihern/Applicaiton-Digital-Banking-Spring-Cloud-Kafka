package com.digitalbanking.kafkadataanalytics.services;

import com.digitalbanking.kafkadataanalytics.models.Operation;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.function.Function;

@Service
public class OperationDataAnalyticsService {
    public Function<KStream<Integer, Operation>, KStream<String, Long>> realTimeOperationsCount(){
        return (inputStream)-> inputStream
                .map((k, v) -> new KeyValue<>(v.getCodeCompte(), 1))
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMillis(5000)))
                .count(Materialized.as("Operation_Count"))
                .toStream()
                .map((k, v)-> new KeyValue<>(k.key(), v));
    }
}
