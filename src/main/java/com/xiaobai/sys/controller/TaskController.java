package com.xiaobai.sys.controller;

import com.xiaobai.sys.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

@RestController
@RequestMapping("sys/task")
public class TaskController extends BaseController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;//配置类生成一个bean
    private ScheduledFuture<?> future;

    @RequestMapping("/startCron")
    public String startCron() {
        //Cron 时间格式, 每隔多久执行一次
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("0/5 * * * * *"));
        System.out.println("任务管理器 开始 一个定时任务");
        return "startCron";
    }

    @RequestMapping("/stopCron")
    public String stopCron() {

        if (future != null) {
            future.cancel(true);
        }
        System.out.println("任务管理器 停止 一个定时任务");
        return "stopCron";
    }

    @RequestMapping("/changeCron")
    public String changeCron() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new MyRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("任务管理器 修改 一个定时任务");
        return "changeCron";
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            //可以执行定时推送, 读取数据库的数据, 执行定时任务
            System.out.println("MyRunable 执行 run 方法" + new Date());
        }
    }
}
