package com.weibo.dip.htk.model;

/**
 * Created by fpschina on 16/2/18.
 */
public class DataModel {
    private String accesskey;
    private String sip;
    private String port;
    private String an;
    private String domain;
    private String cip;
    private String time;
    private String hitInfo;
    private String createTime;
    private String timeZone;
    private String httpMethod;
    private String url;
    private String httpType;
    private String httpCode;
    private String size;
    private String refer;
    private String cookie;
    private String userAgent;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSip() {
        return sip;
    }

    public void setSip(String sip) {
        this.sip = sip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHitInfo() {
        return hitInfo;
    }

    public void setHitInfo(String hitInfo) {
        this.hitInfo = hitInfo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpType() {
        return httpType;
    }

    public void setHttpType(String httpType) {
        this.httpType = httpType;
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRefer() {
        return refer;
    }

    public void setRefer(String refer) {
        this.refer = refer;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        String seperator = "&&";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(accesskey);
        stringBuilder.append(seperator);
        stringBuilder.append(sip);
        stringBuilder.append(seperator);
        stringBuilder.append(port);
        stringBuilder.append(seperator);
        stringBuilder.append(an);
        stringBuilder.append(seperator);
        stringBuilder.append(domain);
        stringBuilder.append(seperator);
        stringBuilder.append(cip);
        stringBuilder.append(seperator);
        stringBuilder.append(time);
        stringBuilder.append(seperator);
        stringBuilder.append(hitInfo);
        stringBuilder.append(seperator);
        stringBuilder.append(createTime);
        stringBuilder.append(seperator);
        stringBuilder.append(timeZone);
        stringBuilder.append(seperator);
        stringBuilder.append(httpMethod);
        stringBuilder.append(seperator);
        stringBuilder.append(url);
        stringBuilder.append(seperator);
        stringBuilder.append(httpType);
        stringBuilder.append(seperator);
        stringBuilder.append(httpCode);
        stringBuilder.append(seperator);
        stringBuilder.append(size);
        stringBuilder.append(seperator);
        stringBuilder.append(refer);
        stringBuilder.append(seperator);
        stringBuilder.append(cookie);
        stringBuilder.append(seperator);
        stringBuilder.append(userAgent);

        return stringBuilder.toString();
    }
}
