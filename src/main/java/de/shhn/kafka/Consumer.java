package de.shhn.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.kafka.common.protocol.types.Field;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer {
    private String bootstrapServer;
    private Duration consumeTimeout=Duration.ofMillis(1000);
    private final int waitingGiveUp = 10;

    public Consumer(String bootstrapServer){
        this.bootstrapServer = bootstrapServer;
    }

    public KafkaConsumer<String, String> getConsumer(String topicName,String consumerGroupID){
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        prop.put(ConsumerConfig.GROUP_ID_CONFIG,consumerGroupID);

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(prop);
        consumer.subscribe(Collections.singletonList(topicName));

        return consumer;
    }

    public void consume(KafkaConsumer<String,String> consumer){
        ConsumerRecords<String,String> records ;

        int noNewInputCounts = 0;

        while (true){
            records = consumer.poll(consumeTimeout);
            if(records.count() == 0){
                if(++noNewInputCounts >= waitingGiveUp)
                    break;
                else
                    continue;
            }

            records.forEach(record ->{
                    System.out.printf("Record information: (%s %s %d %d)\n",
                    record.key(),record.value(),record.partition(),record.offset());
            });
        }
    }

}
