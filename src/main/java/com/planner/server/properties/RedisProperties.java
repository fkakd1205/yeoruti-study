package com.planner.server.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisProperties {
    private static String host;
    private static int port;

    @Value("${spring.redis.host}")
    private void setHost(String host) {
        this.host = host;
    }

    public static String getHost() {
        return host;
    }

    @Value("${spring.redis.port}")
    private void setPort(int port) {
        this.port = port;
    }

    public static int getPort() {
        return port;
    }
}
