package com.irissz.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.quartz.impl.matchers.KeyMatcher;

import com.irissz.quartz.listener.MyJobListener;
import com.irissz.quartz.listener.MyTriggerListener;

public class HelloJobTrigger implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
			System.out.println("����ִ������"+new Date());
	}

	public static void main(String[] args) throws Exception{
		//����������
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//������
		JobDetail jobDetail = JobBuilder.newJob(HelloJobTrigger.class)
		.withIdentity("job1", "group1")
		.build();
		// ����������
		SimpleTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1")
		.startNow()
		.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(2))
		.build();
		//������ע��һ��ȫ�ֵĴ�����TriggerListener��
		//1����һ��ȫ�ֵļ���������
		scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(),EverythingMatcher.allTriggers());
		//������ע��һ���ֲ��Ĵ�����TriggerListener��
		scheduler.getListenerManager().addTriggerListener(new MyTriggerListener(),KeyMatcher.keyEquals(TriggerKey.triggerKey("trigger1")));
		//�󶨴���������
		scheduler.scheduleJob(jobDetail, trigger);
		//��ʼ
		scheduler.start();
	}
	
}
