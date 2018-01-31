package com.wyb.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author: wangyingbo
 * @version: 2018-01-31 17:16
 */
@Component
@PropertySource(value = "classpath:/application.yml")
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {


}
