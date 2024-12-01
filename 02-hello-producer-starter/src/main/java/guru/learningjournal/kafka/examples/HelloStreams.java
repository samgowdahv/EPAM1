package guru.learningjournal.kafka.examples;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;

public class HelloStreams {
    public static Logger logger = LogManager.getLogger(HelloStreams.class);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,AppConfigs.applicationID);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,AppConfigs.bootstrapServers);
        /* Serdes is a factory and it combines seraizer and de serailzer
        *  To read and writing it will create the internal producer and consumer so it needs seralizer and de serailzer*
        here serdes will help us to do the same
         */
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass());
        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<Integer, String> stream = streamsBuilder.stream(AppConfigs.topicName);
        stream.foreach((k,v) -> System.out.println("Key is "+k +" "+ "Value is "+v));
        //We can use peek method also read and print
        //stream.peek((k,v) -> System.out.println("Key is "+k +" "+ "Value is "+v));
        Topology topology = streamsBuilder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology,properties);
        kafkaStreams.start();
        
        Runtime.getRuntime().addShutdownHook( new Thread( () -> {
            kafkaStreams.close();
        }));
    }

}
