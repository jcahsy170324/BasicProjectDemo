package com.lg.bsp.config;

import lombok.Data;

/**
 * @author ：Enzo Cotter
 * @description：各系统路径
 * @date ：Created in 2021/9/29 0029 16:27
 * @modified By：
 * @version: version
 */
@Data
public class SysUrl {

    /**
     * 学校ID
     */
    public static String schoolId = null;
    /**
     * 学校名称
     */
    public static String schoolName = null;
    /**
     * 本系统锁控ID
     */
    public static Integer lockId = null;
    /**
     * 本系统SysID
     */
    public static String sysId = null;
    /**
     * 基础平台地址
     */
    public static String basicUrl = null;
    /**
     * 消息中间件
     */
    public static String E80Url = null;
    /**
     * 大数据
     */
    public static String Sys850Url = null;
    /**
     * 管控中心
     */
    public static String E00Url = null;
}
