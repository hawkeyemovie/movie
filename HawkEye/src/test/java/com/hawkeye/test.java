package com.hawkeye;

import com.alibaba.fastjson.JSON;
import com.hawkeye.elpojo.ElCinema;
import com.hawkeye.quartz.job.HelloJob;
import org.apache.logging.log4j.util.Chars;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.ArrayList;
import java.util.List;

public class test {

//    public static void main(String[] args) throws SchedulerException {
//        //调度器
//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//        //任务实例
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job1","group1").build();
//
//        //触发器
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1","group1")
//                .startNow()//马上启动
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(5)).build();
//        scheduler.scheduleJob(jobDetail,trigger);
//
//        //启动
//        scheduler.start();
//    }

    @Test
    public void join() throws SchedulerException {
        List<String > list = new ArrayList<>();
        list.add("1");
        System.out.println(JSON.toJSONString(list));
    }


    void test(ElCinema elCinema){
        elCinema = new ElCinema();
        elCinema.setId(2);
        System.out.println(elCinema.getId());
    }


    @Test
    public void remove(){
        String str = "2-2-2-2-2-2-2-2-1-1-1-1-0-2-2-1-1-1-1-1-1-1-1-1-1-0-1-1-1-1-1-1-1-1-1-1-1-2-2-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-1-1-1-1-1-1-1-1-1-1-0-0-1-2-1-1-1-1-1-1-1-1-2-1";

        String a ="";
        String [] strings = str.split("-");
        for (int i = 0; i < strings.length; i++) {
            a+=strings[i];
        }
        System.out.println(a);
    }

}
