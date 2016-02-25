package com.weibo.dip.htk.hdfs;

import com.weibo.dip.htk.Config;
import com.weibo.dip.htk.FileList;
import com.weibo.dip.htk.conn.HDFSService;
import com.weibo.dip.htk.conn.KafkaService;
import com.weibo.dip.htk.func.GetTime;
import com.weibo.dip.htk.kafka.WriteKafka;
import com.weibo.dip.htk.model.DataModel;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fpschina on 16/2/22.
 */
public class ReadFile {
    private Logger logger = LoggerFactory.getLogger(ReadFile.class);
    private BufferedReader bufferedReader;
    private FileSystem fileSystem;
    private FSDataInputStream fsDataInputStream;
    private WriteKafka writeKafka;
    private Config config;
    private static final String REGEX = "^_accesskey=([^=]*)&_ip=([^=]*)&_port=([^=]*)&_an=([^=]*)&_data=([^\\s]*) ([^\\s]*) " +
            "([^\\s]*) ([^\\s]*) \\[([^\\s]*) ([^\\s]*) ([^\\s]*) ([^\\s]*) ([^\\s]*) ([^\\s]*) ([^\\s]*) " +
            "([^\\s]*) ([^\\s]*) (.*)$";
    private static Pattern PATTERN = Pattern.compile(REGEX);
    private Matcher matcher;

    /**
     * 初始化 configuration 和 filesystem
     */
    public ReadFile(Config config) {
        this.writeKafka = KafkaService.getInstance(config);
        this.fileSystem = HDFSService.getInstance(config);
        this.config = config;
    }

    /**
     * 从 hdfs 中读取输入的文件, 将文件中的数据写入到 kafka
     *
     * @param fileList
     */
    public void readNWrite(FileList fileList) {
        String topic = config.getTopic();
        String path = null;

        while (!("none".equals(path = fileList.getFilePath()))) {
            try {
                fsDataInputStream = fileSystem.open(new Path(path));
                bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    matcher = PATTERN.matcher(line);
                    if (matcher.matches()) {
                        DataModel dataModel = new DataModel();
                        dataModel.setAccesskey(matcher.group(1));
                        dataModel.setSip(matcher.group(2));
                        dataModel.setPort(matcher.group(3));
                        dataModel.setAn(matcher.group(4));
                        dataModel.setDomain(matcher.group(5));
                        dataModel.setCip(matcher.group(6));
                        dataModel.setTime(matcher.group(7));
                        dataModel.setHitInfo(matcher.group(8));
                        dataModel.setCreateTime(GetTime.getTimeToSec());
                        dataModel.setTimeZone(matcher.group(10));
                        dataModel.setHttpMethod(matcher.group(11));
                        dataModel.setUrl(matcher.group(12));
                        dataModel.setHttpType(matcher.group(13));
                        dataModel.setHttpCode(matcher.group(14));
                        dataModel.setSize(matcher.group(15));
                        dataModel.setRefer(matcher.group(16));
                        dataModel.setCookie(matcher.group(17));
                        dataModel.setUserAgent(matcher.group(18));

                        //写入数据到 kafka
                        writeKafka.sendDataToKafka(topic, dataModel.toString());
                    }
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            } finally {
                close();
            }
        }

    }

    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fsDataInputStream != null) {
                fsDataInputStream.close();
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
