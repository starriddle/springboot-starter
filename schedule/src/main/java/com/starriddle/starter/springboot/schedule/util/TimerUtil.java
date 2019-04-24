package com.starriddle.starter.springboot.schedule.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer
 *
 * java自带的java.util.Timer类，允许你调度一个java.util.TimerTask任务。
 *
 * 使用这种方式可以让你的程序按照某一个频度执行，但不能在指定时间运行。一般用的较少。
 *
 * 单线程调用计划任务，一旦中间某个任务抛出异常(导致线程中止，可通过捕获异常来避免)或卡死，所有后续任务将无法继续执行，不建议使用
 *
 * @author CYL
 * @date 2019-04-01
 */
public class TimerUtil {

    public static void main(String[] args) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                printCurrentTime(format, "task start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printCurrentTime(format, "task end");
            }
        };
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                printCurrentTime(format, "task1 start");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printCurrentTime(format, "task1 end");
//                throw new RuntimeException("break");
            }
        };

        printCurrentTime(format, "timer start");

        // 1-1. 延迟指定时长(ms)后执行一次任务
        // PS：执行完后task线程不会自动退出
//        timer.schedule(task, 5000);

        // 1-2. 在指定时间执行一次任务，如指定时间已经过去则立即执行
        // PS：执行完后task线程不会自动退出
//        timer.schedule(task, format.parse("2019-04-01 14:20:20:500"));

        // 2-1. 延迟 delay 时长(ms)执行第一次任务
        // 以第一次任务开始时间为基准，每隔 period 时长(ms)执行一次
        // PS：如任务执行时间超过period时长，则后一个任务会在前一个任务执行结束后再立即执行
        timer.schedule(task, 2000, 6000);
        timer.schedule(task1, 3000, 4000);

        // 2-2. 在指定时间执行第一次任务，如指定时间已经过去则立即执行，并以此作为第一次任务开始时间
        // 以第一次任务开始时间为基准，每隔 period 时长(ms)执行一次
        // PS：如任务执行时间超过period时长，则后一个任务会在前一个任务执行结束后再立即执行
//        timer.schedule(task, format.parse("2019-04-01 14:43:20:500"), 5000);

        // 3-1. 与 2-1 一致
//        timer.scheduleAtFixedRate(task, 3000, 5000);

        // 3-2. 与 2-2 差异
        // 如指定时间已经过去，计算历次执行的开始时间，执行时间已经过去的任务立即顺序执行，然后按计划执行后续任务
//        timer.scheduleAtFixedRate(task, format.parse("2019-04-01 14:53:20:500"), 5000);

        printCurrentTime(format, "timer end");

    }

    public static void printCurrentTime(DateFormat format, String tag) {
        System.out.println(Thread.currentThread().getName() + "\t: " + format.format(new Date()) + "\t: " + tag);
    }

}
