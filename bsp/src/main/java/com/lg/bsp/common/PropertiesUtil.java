package com.lg.bsp.common;

import cn.hutool.core.io.resource.ClassPathResource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * properties配置文件读取工具类
 *
 */
public class PropertiesUtil {

    /**
     * 读取配置文件
     *
     * @param filePath
     * @return
     */
    public static Properties readPropertiesFile(String filePath) {

        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            InputStream inputStream = classPathResource.getStream();
            Properties props = new Properties();
            props.load(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
