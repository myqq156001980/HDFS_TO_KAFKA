package com.weibo.dip.htk.hdfs;

import com.weibo.dip.htk.Config;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.weibo.dip.htk.conn.*;

/**
 * Created by fpschina on 16/2/18.
 */
public class GetAllFiles {
    private FileSystem fileSystem;
    private ArrayList<String> fileList = new ArrayList<String>();

    public GetAllFiles(Config config) throws URISyntaxException {
        fileSystem = HDFSService.getInstance(config);
    }

    /**
     * 如果输入的路径是文件夹获取该文件夹下的所有文件路径
     *
     * @param path
     */
    public void initFileList(String path) throws IOException {
        FileStatus[] fileStatuses;
        fileStatuses = fileSystem.listStatus(new Path(path.trim()));
        for (int i = 0; i < fileStatuses.length; i++) {
            FileStatus fileStatus = fileStatuses[i];
            if (fileStatus.isDirectory()) {
                initFileList(fileStatus.getPath().toString());
            } else if (fileStatus.isFile()) {
                fileList.add(fileStatus.getPath().toString());
            }
        }

    }

    public ArrayList<String> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<String> fileList) {
        this.fileList = fileList;
    }


}
