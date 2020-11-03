package de.shhn.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.protocol.types.Field;

import java.sql.Timestamp;
import java.util.Properties;
import org.apache.kafka.common.serialization.StringSerializer;

public class Producer {
    private String bootstrapServer;
    private String topicName;
    private final String ackConfig="all";
    private final String keySerializer=StringSerializer.class.getName();
    private final String valueSerializer=StringSerializer.class.getName();

    public Producer(String bootstrapServer,String topicName){
        this.bootstrapServer = bootstrapServer;
        this.topicName = topicName;
    }

    public KafkaProducer<String, String> getProducer(){
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        prop.put(ProducerConfig.ACKS_CONFIG,ackConfig);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,keySerializer);
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,valueSerializer);

        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(prop);
        return producer;
    }
    public void produce(KafkaProducer<String,String> producer){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        producer.send(new ProducerRecord<String,String>(topicName,"This is new record at " + timestamp.toString()));
    }
}
