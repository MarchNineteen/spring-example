package com.wyb.thread.pool.threadPoolExecutor;

import com.wyb.thread.pool.threadPoolExecutor.config.Config;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Request 请求类
 */
@Getter
public class Request<T> {

    private String url;
    private String method = "GET";
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();
    private String contentType = "text/html; charset=UTF-8";
    private String charset = "UTF-8";
    private Config config;

    public Request(String url, Config config) {
        this.url = url;
        this.config = config;
        this.header("User-Agent", config.userAgent());
    }

    public Request header(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public Request cookie(String key, String value) {
        this.cookies.put(key, value);
        return this;
    }

    public String header(String key) {
        return this.headers.get(key);
    }

    public String cookie(String key) {
        return this.cookies.get(key);
    }

    public String contentType() {
        return contentType;
    }

    public Request contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public String charset() {
        return charset;
    }

    public Request charset(String charset) {
        this.charset = charset;
        return this;
    }

    public Request method(String method) {
        this.method = method;
        return this;
    }

    public String method() {
        return this.method;
    }
}
