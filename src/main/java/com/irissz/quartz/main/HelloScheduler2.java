package com.irissz.quartz.main;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.irissz.quartz.job.HelloJob2;

public class HelloScheduler2 {

	public static void main(String[] args) throws Exception {
		//������ȡScheduler����
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//��������ʵ�� ����������ɰ�
		JobDetail jobDetail = JobBuilder.newJob(HelloJob2.class)
				.withIdentity("job1", "group1")
				.usingJobData("message", "ss")
				.usingJobData("hh", 1)
				.build();
		
		//����������
		Trigger trigger=TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(1))
				.usingJobData("message", "���")
				.startNow()
				.build();
		//������ʵ�� �� ������
		scheduler.scheduleJob(jobDetail, trigger);
		//��ʼ
		scheduler.start();
	}
}
