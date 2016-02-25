package com.weibo.dip.htk.conn;

import com.weibo.dip.htk.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by fpschina on 16/2/18.
 */
public class HDFSService {
    private static Logger LOGGER = LoggerFactory.getLogger(HDFSService.class);
    private static Configuration CONFIGURATION = new Configuration(false);
    public static FileSystem fileSystem = null;

    private HDFSService() {
    }

    public synchronized static FileSystem getInstance(Config config) {
        if (fileSystem != null) {
            return fileSystem;
        } else {
            CONFIGURATION.addResource(new Path(config.getCoreSitePath()));
            CONFIGURATION.addResource(new Path(config.getHdfsSitePath()));
            CONFIGURATION.addResource(new Path(config.getMapredSitePath()));
            CONFIGURATION.addResource(new Path(config.getYarnSitePath()));
            try {
                fileSystem = FileSystem.get(CONFIGURATION);
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return fileSystem;
    }


    public static void close() {
        if (fileSystem != null) {
            try {
                fileSystem.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

}
