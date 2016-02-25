package com.weibo.dip.htk.client;

import com.weibo.dip.htk.Config;
import com.weibo.dip.htk.FileList;
import com.weibo.dip.htk.hdfs.*;

/**
 * Created by fpschina on 16/2/18.
 */
public class MessageClient implements Runnable {
    private FileList fileList;
    private Config config;

    public MessageClient(FileList fileList, Config config) {
        this.fileList = fileList;
        this.config = config;
    }

    public void run() {
        ReadFile readFiles = new ReadFile(config);
        readFiles.readNWrite(fileList);

    }
}
