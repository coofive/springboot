package com.util.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wenwu.liu.o
 */
@Slf4j
public class DateUtil {
    private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认格式字符串转换为日期java.util.Date
     * yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 字符串
     * @return Date
     */
    public static Date stringToDate(String dateString) {
        return stringToDate(dateString, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 字符串转换为日期java.util.Date
     *
     * @param dateString 字符串
     * @param format     日期格式
     * @return Date
     */
    public static Date stringToDate(String dateString, String format) {
        if (dateString == null || dateString.length() == 0) {
            log.info("时间字符串为空");
            return null;
        }
        try {
            // 定义字符串格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            // 获取时间
            LocalDateTime localDateTime = LocalDateTime.parse(format, formatter);
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
            return Date.from(zonedDateTime.toInstant());
        } catch (Exception e) {
            log.info("解析成指定时间格式失败:dateString:{},format:{}", dateString, format);
            e.printStackTrace();
            return null;
        }
    }
}
