package com.weibo.dip.htk.kafka;

import com.weibo.dip.htk.Config;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;

/**
 * Created by fpschina on 16/2/17.
 */
public class WriteKafka {
    private Logger logger = LoggerFactory.getLogger(WriteKafka.class);
    private KafkaProducer<String, String> kafkaProducer;
    private volatile BigInteger flow = new BigInteger("0");

    private volatile int count = 0;

    public WriteKafka(Config config) {
        kafkaProducer = new KafkaProducer<String, String>(config.getKafkaProperties());
    }

    /**
     * 输入需要写入的 topic 和 message,写入数据成功则计数器加1
     *
     * @param topic
     * @param message
     */
    public void sendDataToKafka(String topic, final String message) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, message);
        kafkaProducer.send(producerRecord, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e != null) {
                    logger.error(e.getMessage());
                } else {
                    flowAdd(new BigInteger(new Integer(message.getBytes().length).toString()));
                    count++;
                }
            }
        });

//        kafkaProducer.send(producerRecord);
    }

    public void close() {

        if (kafkaProducer != null) {
            kafkaProducer.close();
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void flowAdd(BigInteger bigInteger) {
        flow = this.flow.add(bigInteger);
    }

    public BigInteger getFlow() {
        return flow;
    }
}
