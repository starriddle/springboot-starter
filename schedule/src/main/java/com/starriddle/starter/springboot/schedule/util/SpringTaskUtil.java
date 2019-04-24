package com.starriddle.starter.springboot.schedule.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Spring Task：Spring3.0以后自带的task
 *
 * 可以将它看成一个轻量级的Quartz，而且使用起来比Quartz简单许多。
 *
 * 任务出现异常不会影响所有任务的下次执行 <-- spring捕获了该异常，因此不会导致线程中止
 *
 * 单线程
 * 同Timer 一样，由于只有一个线程，某次任务的卡死，会导致后续所有任务无法执行
 *
 * 多线程 @Async
 * 在定时任务的类或者方法上添加@Async，则类下所有方法或添加注解的方法开启异步事件支持
 * 同 ScheduledExecutor 一样，任务是并发执行，不同任务互不影响，同一个任务的多次执行也可能会使用多线程
 * 同 ScheduledExecutor 不一样，理论上上一次的卡死不会影响下一次执行
 * 但实际上，因为线程池中线程的创建、释放、调用机制，会导致任务实际开始执行时间比计划时间延迟
 * 线程池中实际空闲线程足够的情况下，延迟情况概率较低
 * 因此 线程池中线程数=同一时间执行的任务最大可能次数 时，任务执行延迟最低
 *
 * @author CYL
 * @date 2019-04-01
 */
@Slf4j
@Component
public class SpringTaskUtil {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    /**
     * 注解 @Scheduled 的 cron 配置 无法指定年份，只能配置前6个参数，如需要指定年份，需要使用完整功能的quartz
     */
    @Async
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        task(" =====>>>>> 使用cron");
    }

    @Async
    @Scheduled(fixedRate = 5000)
    public void scheduled1() {
        task(" =====>>>>> fixedRate");
    }

    @Async
    @Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        task(" =====>>>>> fixedDelay");
    }

    private void task(String tag) {
        int random = new Random().nextInt(20);
        printCurrentTime(format, "task start " + random + tag);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (random%3 == 0) {
            printCurrentTime(format, "task error " + random + tag);
//            throw new RuntimeException("task1 error");
        } else {
            printCurrentTime(format, "task e n d " + random +tag);
        }
    }

    private void printCurrentTime(DateFormat format, String tag) {
        log.info(Thread.currentThread().getName() + "\t: " + format.format(new Date()) + "\t: " + tag);
    }

}
