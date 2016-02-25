package com.weibo.dip.htk;

import java.util.ArrayList;

/**
 * Created by fpschina on 16/2/22.
 */
public class FileList {
    private ArrayList<String> fileList = new ArrayList<String>();
    private volatile int index = 0;

    public int getSize() {
        return fileList.size();
    }

    public synchronized String getFilePath() {
        String filePath = null;
        if (index < fileList.size()) {
            filePath = fileList.get(index);
            index++;
        } else {
            filePath = "none";
        }
        return filePath;
    }

    public ArrayList<String> getFileList() {
        return fileList;
    }

    public void setFileList(ArrayList<String> fileList) {
        this.fileList = fileList;
    }

}
