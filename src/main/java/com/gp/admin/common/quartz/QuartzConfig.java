package com.gp.admin.common.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;


//@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail testQuartz1() {
		return JobBuilder.newJob(TestTask1.class).withIdentity("testTask1").usingJobData("testJobData1", "testJobData1")
				.storeDurably().build();

	}

	@Bean
	public Trigger testQuartzTrigger1() {
		// 5秒执行一次
		SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5)
				.repeatForever();
		return TriggerBuilder.newTrigger().forJob(testQuartz1()).withIdentity("testTask1").withSchedule(scheduleBuilder)
				.build();
	}

	@Bean
	public JobDetail testQuartz2() {
		return JobBuilder.newJob(TestTask2.class).withIdentity("testTask2").storeDurably().build();
	}

	@Bean
	public Trigger testQuartzTrigger2() {
		// cron方式，每隔5秒执行一次
		return TriggerBuilder.newTrigger().forJob(testQuartz2()).withIdentity("testTask2")
				.withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?")).build();
	}
	
	//	抛异常 SchedulerException: Jobs added with no trigger must be durable.
//	意思是没有给job添加触发器，你可以在配置文件中加上durable属性即可。// durable, 指明任务就算没有绑定Trigger仍保留在Quartz的JobStore中
	
}