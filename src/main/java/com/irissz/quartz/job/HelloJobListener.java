package com.irissz.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import com.irissz.quartz.listener.MyJobListener;

public class HelloJobListener implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
			System.out.println("����ִ������"+new Date());
	}

	public static void main(String[] args) throws Exception{
		//����������
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//������
		JobDetail jobDetail = JobBuilder.newJob(HelloJobListener.class)
		.withIdentity("job1", "group1")
		.build();
		// ����������
		SimpleTrigger trigger = TriggerBuilder.newTrigger()
		.startNow()
		.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(2))
		.build();
		//������ע��һ��ȫ�ֵ�JobListener
		scheduler.getListenerManager().addJobListener(new MyJobListener(),EverythingMatcher.allJobs());
		//�����ֲ���������
		scheduler.getListenerManager().addJobListener(new MyJobListener(),KeyMatcher.keyEquals(JobKey.jobKey("job1", "group1")));
		//�󶨴���������
		scheduler.scheduleJob(jobDetail, trigger);
		//��ʼ
		scheduler.start();
	}
	
}
