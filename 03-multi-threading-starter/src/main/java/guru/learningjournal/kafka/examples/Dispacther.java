package guru.learningjournal.kafka.examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class Dispacther implements Runnable{

    private String filelocation;
    private String topicName;
    private KafkaProducer<Integer,String > producer;

    Dispacther(KafkaProducer<Integer,String> producer,String filelocation,String topicName){
    this.producer=producer;
    this.filelocation=filelocation;
    this.topicName=topicName;
    }


    @Override
    public void run() {
        try {
            System.out.println("Exceition Started");
            File file = new File(filelocation);

            Scanner scanner = new Scanner(file);
           while ( scanner.hasNextLine()) {
               String lines = scanner.nextLine();
               producer.send(new ProducerRecord<>(topicName, null, lines));
               System.out.println("Sending the data to topic is complete");
           }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
