package com.wyb.thread.pool.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 下载器线程
 *
 * @author Kunzite
 */
@Slf4j
public class DownloadThread implements Runnable {

    private final Request request;

    public DownloadThread(Request request) {
        this.request = request;
    }

    @Override
    public void run() {
        log.debug("[{}] 开始请求", request.getUrl());
        io.github.biezhi.request.Request httpReq = null;
        if ("get".equalsIgnoreCase(request.method())) {
            httpReq = io.github.biezhi.request.Request.get(request.getUrl());
        }
        if ("post".equalsIgnoreCase(request.method())) {
            httpReq = io.github.biezhi.request.Request.post(request.getUrl());
        }

        InputStream result = httpReq.contentType(request.contentType()).headers(request.getHeaders())
                .connectTimeout(request.getConfig().timeout())
                .readTimeout(request.getConfig().timeout())
                .stream();

        log.debug("[{}] 下载完毕", request.getUrl());
        StringBuilder html = new StringBuilder(100);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(result, request.charset()));
            String         temp;
            while ((temp = br.readLine()) != null) {
                html.append(temp).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        log.debug("页面数据：[{}] ", html.toString());
    }
}
