package com.hatcher;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author hatcher
 * @date 2023/1/5 14:13
 * @description
 */
public class GetProperties {
    public static Configuration getConfig() throws Exception {
        try {
            return new PropertiesConfiguration("application-v1-pack.yml");
        } catch (ConfigurationException e) {
            throw new ConfigurationException("获取配置文件失败，");
        }
    }
}
