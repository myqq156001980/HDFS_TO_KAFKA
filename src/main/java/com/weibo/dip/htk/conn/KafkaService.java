package com.weibo.dip.htk.conn;

import com.weibo.dip.htk.Config;
import com.weibo.dip.htk.kafka.WriteKafka;

import java.math.BigInteger;

/**
 * Created by fpschina on 16/2/19.
 */
public class KafkaService {
    private static WriteKafka WRITEKAFKA;

    private KafkaService() {
    }

    public synchronized static WriteKafka getInstance(Config config) {
        if (WRITEKAFKA != null) {
            return WRITEKAFKA;
        } else {
            WRITEKAFKA = new WriteKafka(config);
        }

        return WRITEKAFKA;
    }

    public static void close() {
        if (WRITEKAFKA != null) {
            WRITEKAFKA.close();
        }
    }

    public static int getHandleMessNum() {
        return WRITEKAFKA.getCount();
    }

    public static BigInteger getFlow() {
        return WRITEKAFKA.getFlow();
    }

}
