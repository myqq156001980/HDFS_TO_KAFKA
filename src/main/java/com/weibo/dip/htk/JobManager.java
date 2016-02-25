package com.weibo.dip.htk;

import com.weibo.dip.htk.client.MessageClient;
import com.weibo.dip.htk.conn.HDFSService;
import com.weibo.dip.htk.conn.KafkaService;
import com.weibo.dip.htk.func.FlowStatis;
import com.weibo.dip.htk.func.GetTime;
import com.weibo.dip.htk.hdfs.GetAllFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by fpschina on 16/2/18.
 */
public class JobManager {
    private Logger logger = LoggerFactory.getLogger(JobManager.class);
    private String path;
    private ThreadPoolExecutor threadPoolExecutor;
    private ArrayList<MessageClient> clientList = new ArrayList<MessageClient>();
    private Config config;
    private int threadNum = 10;

    public JobManager(String path, Config config) {
        this.path = path;
        this.config = config;
        this.threadNum = config.getThreadNum();

    }

    public boolean init() throws URISyntaxException, IOException {
        GetAllFiles getAllFiles = new GetAllFiles(config);
        FileList fileList = new FileList();
        getAllFiles.initFileList(path);
        fileList.setFileList(getAllFiles.getFileList());

        threadPoolExecutor = new ThreadPoolExecutor(threadNum, threadNum, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        if (fileList.getSize() == 0) {
            logger.debug("The fileList is none, there is nothing to read");
            return false;
        } else {
            for (int i = 0; i < threadNum; i++) {
                MessageClient mc = new MessageClient(fileList, config);
                clientList.add(mc);
            }
        }
        return true;
    }

    public void start() {
        final long[] beTime = new long[2];
        beTime[0] = GetTime.getTimeToms();
        Timer timer = new Timer();
        for (int i = 0; i < clientList.size(); i++) {
            threadPoolExecutor.execute(clientList.get(i));
        }

        threadPoolExecutor.shutdown();


        final FlowStatis flowStatis = new FlowStatis(beTime[0]);
        final int[] numBefore = new int[1];
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                beTime[1] = GetTime.getTimeToms();
                BigInteger currentFlow = KafkaService.getFlow();
                try {
                    int currentNum = KafkaService.getHandleMessNum();
                    logger.debug("during {} ms totaly read and write {} messages to kafka", beTime[1] - beTime[0]
                            , currentNum - numBefore[0]);
                    logger.debug("now the read and write speed is {} item/second", (currentNum - numBefore[0]) / ((beTime[1] - beTime[0]) / 1000));
                    logger.debug("the average flow is {} kb/s", flowStatis.getCurrentFlow(currentFlow, beTime[1]));
                    numBefore[0] = currentNum;
                    beTime[0] = beTime[1];

                } catch (NullPointerException e) {
                    logger.error("has not init WriteKafka", e);
                }
            }
        }, 5000, 5000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (threadPoolExecutor.isTerminated()) {
                    HDFSService.close();
                    KafkaService.close();
                    System.out.println("complete close......");
                    System.exit(1);
                }
            }
        }, 2000, 10000);

    }

}
