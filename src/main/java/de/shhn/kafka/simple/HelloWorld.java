package de.shhn.kafka.simple;

import de.shhn.kafka.Consumer;
import de.shhn.kafka.Producer;

public class HelloWorld {
    private final String topicName="JavaHelloWorld";
    private final String bootstrapServer="localhost:9092";

    private final String consumerGroupID="simpleConsumer";

    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.main();
    }

    private void main(){
        Producer kafkaProducer = new Producer(bootstrapServer,topicName);
        kafkaProducer.produce(kafkaProducer.getProducer());

        Consumer kafkaConsumer = new Consumer(bootstrapServer);
        kafkaConsumer.consume(kafkaConsumer.getConsumer(topicName,consumerGroupID));
    }
}
