package com.starriddle.starter.springboot.schedule.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledExecutorService
 *
 * jdk自带的一个类，基于线程池设计的定时任务类
 *
 * 每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响。
 *
 * 一旦某个任务抛出异常(导致线程中止，可通过捕获异常来避免)或卡死，其他任务不受影响，但会影响该任务的后续计划，
 * 虽然一个任务前后2次执行可能在不同线程，但只会在前一次执行结束后才会执行后一次，
 * 因此 线程池中线程数=任务数 即可，多了也没用
 *
 * @author CYL
 * @date 2019-04-01
 */
public class ScheduledExecutorUtil {

    public static void main(String[] args) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        Runnable task = () -> {
            printCurrentTime(format, "task start");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printCurrentTime(format, "task end");
        };
        Runnable task1 = () -> {
            printCurrentTime(format, "task1 start");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int random = new Random().nextInt(10);
            if (random%2 == 0) {
                printCurrentTime(format, "task1 error " + random);
//                throw new RuntimeException("task1 error");
            } else {
                printCurrentTime(format, "task1 end " + random);
            }
        };

        printCurrentTime(format, "timer start");

        // 1. 延迟指定时长后执行一次任务
        // PS：执行完后task线程不会自动退出
//        service.schedule(task, 3, TimeUnit.SECONDS);

        // 2. 延迟 initialDelay 时长执行第一次任务
        // 以第一次任务开始时间为基准，每隔 period 时长执行一次
        // PS：如任务执行时间超过period时长，则后一个任务会在前一个任务执行结束后再立即执行
        service.scheduleAtFixedRate(task, 1, 5, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(task1, 1, 5, TimeUnit.SECONDS);

        // 3. 延迟 initialDelay 时长执行第一次任务
        // 在上一次任务执行完毕后，延迟delay 时长执行下一次任务
        // PS：如任务执行时间超过 delay 时长，则下一个任务会在上一个任务执行结束后再立即执行
//        service.scheduleWithFixedDelay(task, 3, 5, TimeUnit.SECONDS);

        printCurrentTime(format, "timer end");

    }

    public static void printCurrentTime(DateFormat format, String tag) {
        System.out.println(Thread.currentThread().getName() + "\t: " + format.format(new Date()) + "\t: " + tag);
    }

}
