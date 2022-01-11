package com.lg.bsp.common;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @ClassName MyDateUtil
 * @Description 时间工具类
 * @Author jincheng
 * @Date 2022/1/10 14:11
 * @Version 1.0
 **/
public class MyDateUtil {
    /**
     * @Author jincheng
     * @Description //
     * 0/获取某日的开始时间
     * 1/获取某周的开始时间
     * 2/获取某月的开始时间
     * 3/获取某年的开始时间
     **/
    public static Date getStartTime(Integer type){
        DateTime date = DateUtil.date();
        switch (type){
            case 0:
                date = DateUtil.beginOfDay(date);
                break;
            case 1:
                date = DateUtil.beginOfWeek(date,true);
                break;
            case 2:
                date = DateUtil.beginOfMonth(date);
                break;
            case 3:
                date = DateUtil.beginOfYear(date);
                break;
            default:
                break;
        }
        return date;
    }

    /**
     * @Author jincheng
     * @Description //
     * 0/获取某日的结束时间
     * 1/获取某周的结束时间
     * 2/获取某月的结束时间
     * 3/获取某年的结束时间
     **/
    public static Date getEndTime(Integer type){
        DateTime date = DateUtil.date();
        switch (type){
            case 0:
                date = DateUtil.endOfDay(date);
                break;
            case 1:
                date = DateUtil.endOfWeek(date,true);
                break;
            case 2:
                date = DateUtil.endOfMonth(date);
                break;
            case 3:
                date = DateUtil.endOfYear(date);
                break;
            default:
                break;
        }
        return date;
    }

    public static Integer countWeek(){
        return DateUtil.weekOfMonth(DateUtil.endOfMonth(new Date()));
    }

    public static int countDay() {
        return DateUtil.dayOfMonth(DateUtil.endOfMonth(new Date()));
    }

}
