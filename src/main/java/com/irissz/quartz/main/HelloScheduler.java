package com.irissz.quartz.main;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.irissz.quartz.job.HelloJob;

/**
 * @author Administrator
 *
 */
public class HelloScheduler {

	/**
	 * ִ������main����
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 1.��������Scheduler�����ӹ����л�ȡ����ʵ��
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		// 2.����ʵ����JobDetail��
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)// ���������࣬��HelloJob(ʵ��Job�ӿ�)��ɰ�.
				.withIdentity("job1", "group1")// ����1 ��������ƣ�Ψһʵ����������2 �����������
				.usingJobData("day", 0)
				.build();
		// 3.������(Trigger)
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")// ����1���������ƣ�����2 ������������ơ�
				.startNow()// ��������������
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatSecondlyForever(5))// ÿ5���ظ�ִ��һ��
				.build();
		// 4.�õ�������������ʹ��������������ܰ��մ��������������ִ������
		scheduler.scheduleJob(jobDetail, trigger);
		// 5����
		scheduler.start();
	}
}
