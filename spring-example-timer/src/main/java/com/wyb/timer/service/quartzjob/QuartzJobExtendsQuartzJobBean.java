package com.wyb.timer.service.quartzjob;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Description:
 *
 * @author: Kunzite
 * @version: 2018-02-07 17:41
 */
public class QuartzJobExtendsQuartzJobBean extends QuartzJobBean {
    private int timeout;

    private static int i = 0;
    //调度工厂实例化后，经过timeout时间开始执行调度
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("定时任务执行中…");
    }
}
