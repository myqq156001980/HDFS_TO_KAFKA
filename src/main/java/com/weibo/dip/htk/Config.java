package com.weibo.dip.htk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by fpschina on 16/2/19.
 */
public class Config {
    private String topic;
    private String coreSitePath;
    private String hdfsSitePath;
    private String mapredSitePath;
    private String yarnSitePath;
    private int threadNum;
    private Properties kafkaProperties = new Properties();
    private Properties prop = new Properties();

    public Config(String path) throws Exception {
        prop.load(new FileInputStream(path));
        kafkaProperties.load(new FileInputStream(prop.getProperty("kafkaProperties").trim()));
        topic = prop.getProperty("topic", "test").trim();
        coreSitePath = prop.getProperty("coreSitePath", "").trim();
        hdfsSitePath = prop.getProperty("hdfsSitePath", "").trim();
        mapredSitePath = prop.getProperty("mapredSitePath", "").trim();
        yarnSitePath = prop.getProperty("yarnSitePath", "").trim();
        threadNum = Integer.parseInt(prop.getProperty("threadNum", "10").trim());

    }


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCoreSitePath() {
        return coreSitePath;
    }

    public void setCoreSitePath(String coreSitePath) {
        this.coreSitePath = coreSitePath;
    }

    public String getHdfsSitePath() {
        return hdfsSitePath;
    }

    public void setHdfsSitePath(String hdfsSitePath) {
        this.hdfsSitePath = hdfsSitePath;
    }

    public String getMapredSitePath() {
        return mapredSitePath;
    }

    public void setMapredSitePath(String mapredSitePath) {
        this.mapredSitePath = mapredSitePath;
    }

    public String getYarnSitePath() {
        return yarnSitePath;
    }

    public void setYarnSitePath(String yarnSitePath) {
        this.yarnSitePath = yarnSitePath;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public Properties getKafkaProperties() {
        return kafkaProperties;
    }

    public void setKafkaProperties(Properties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }
}
