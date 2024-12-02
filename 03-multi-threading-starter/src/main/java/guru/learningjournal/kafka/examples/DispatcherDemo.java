package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DispatcherDemo {

    public static void main(String[] args) throws IOException {

        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(AppConfigs.kafkaConfigFileLocation);
        properties.load(inputStream);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.applicationID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        KafkaProducer<Integer, String> kafkaProducer = new KafkaProducer<>(properties);

        Thread[]  dispacthers = new Thread[AppConfigs.eventFiles.length];
        System.out.println("Starting dispatcher threds");
        for (int i=0 ; i < AppConfigs.eventFiles.length; i++)
        {
            dispacthers[i]= new Thread(new Dispacther(kafkaProducer,AppConfigs.topicName,AppConfigs.eventFiles[i]));

        }
        kafkaProducer.close();
   ExecutorService executorService = Executors.newFixedThreadPool(2);
   executorService.submit(new Dispacther(kafkaProducer, Arrays.stream(AppConfigs.eventFiles).iterator().next(),AppConfigs.topicName));


    }
}
