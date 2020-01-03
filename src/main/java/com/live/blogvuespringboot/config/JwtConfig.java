package com.live.blogvuespringboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
JWT实体类
 */
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtConfig {
    /*
    time:过期时间
    secertJ：WT密码
    prefix：token前缀
    header：存放Token的Header Key
     */
    private long time;
    private String secert;
    private String prefix;
    private String header;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getSecert() {
        return secert;
    }

    public void setSecert(String secert) {
        this.secert = secert;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
