package com.lg.bsp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统及基础平台信息
 * Created by Administrator on 2019-07-23.
 */
@Component
@Data
@ConfigurationProperties(prefix = "lancoo")
public class LancooConfig {

    /**
     * 基础平台地址
     */
    private String baseAddr;

    /**
     * 本系统锁控Id
     */
    private int lockerId;

    /**
     * 本系统编号
     */
    private String sysId;
}
