package com.weibo.dip.htk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by fpschina on 16/2/18.
 */
public class Driver {
    private static Logger LOGGER = LoggerFactory.getLogger(Driver.class);

    public static void main(String[] args) {

        if(args.length != 2){
            System.err.print("please input config file path and  file or directory path");
            System.exit(1);

        }else {
            Config config = null;
            try {
                config = new Config(args[0]);

                JobManager jm = new JobManager(args[1], config);

                if(jm.init()){
                    jm.start();
                }else {
                    LOGGER.debug("the file path does not contains any file");
                }
            } catch (URISyntaxException e){
                LOGGER.error("get all files failed " + e.getMessage());
                LOGGER.error("exception", e);
            } catch (IOException e){
                LOGGER.error(e.getMessage());
                LOGGER.error("exception", e);
            } catch (Exception e) {
                LOGGER.error("init configuration failed......because " + e.getMessage());
                LOGGER.error("exception", e);

            }
        }


//        String path = "/user/hdfs/rawlog/www_sinaedgeahsolci14ydn_trafficserver";
//        String configPath = "/Users/fpschina/Desktop/config/htk-config";
//        Config config = null;
//        try {
//            config = new Config(configPath);
//            JobManager jm = new JobManager(path, config);
//
//            if (jm.init()) {
//                jm.start();
//            } else {
//                LOGGER.debug("the file path does not contains any file");
//            }
//        } catch (URISyntaxException e) {
//            LOGGER.error("get all files failed " + e.getMessage());
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
//        } catch (Exception e) {
//            LOGGER.error("init configuration failed......because " + e.toString());
//            e.printStackTrace();
//        }


    }
}
