package com.weibo.dip.htk.func;

import java.math.BigInteger;

/**
 * Created by fpschina on 16/2/22.
 */
public class FlowStatis {
    private long beginTime;

    public FlowStatis(long beginTime) {
        this.beginTime = beginTime;
    }

    public String getCurrentFlow(BigInteger b, long endTime) {
        return b.divide(new BigInteger("1024")).divide(new BigInteger(new Integer((int) (endTime - beginTime) / 1000).toString())).toString();
    }
}
