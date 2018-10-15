package com.irissz.quartz.job;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ����������
 * 
 * @author Administrator
 *
 */
@PersistJobDataAfterExecution // ��ε���Jobʱ�����Job���г־û�������һ�����ݵ���Ϣ
public class HelloJobCronTrigger implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		System.out.println("nihao");
	}

	public static void main(String[] args) throws SchedulerException {
		
		SchedulerFactory factory=new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		// 1.��������Scheduler�����ӹ����л�ȡ����ʵ��
		Scheduler scheduler2 = StdSchedulerFactory.getDefaultScheduler();
		// 2.����ʵ����JobDetail��
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)// ���������࣬��HelloJob(ʵ��Job�ӿ�)��ɰ�.
				.withIdentity("job1", "group1")// ����1 ��������ƣ�Ψһʵ����������2 �����������
				.usingJobData("day", 0).build();
		// 3.������(Trigger)
		
		//����һ����������ÿ��һ���ӣ�ÿ������8��������5��֮�䣺
		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")// ����1���������ƣ�����2 ������������ơ�
				.withSchedule(CronScheduleBuilder.cronSchedule("* * 8-17 * * ?"))
				.build();
		Trigger trigger4 = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")// ����1���������ƣ�����2 ������������ơ�
				.withSchedule(CronScheduleBuilder.cronSchedule("0 42 10 * * ?"))
				.build();
		Trigger trigger2 = TriggerBuilder.newTrigger()
				.withIdentity("trigger2", "group2")// ����1���������ƣ�����2 ������������ơ�
				.startAt(DateBuilder.futureDate(5, IntervalUnit.MINUTE))//����ʹ��DateBuilder������һ��δ��ʱ��
				.build();
		
		//����������ÿ��5����ִ��һ�Σ�ֱ��22:00��
		Trigger trigger3 = TriggerBuilder.newTrigger()
				.withIdentity("trigger3", "group3")// ����1���������ƣ�����2 ������������ơ�
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(5).repeatForever())
				.endAt(DateBuilder.dateOf(22, 0, 0))
				.build();
		// 4.�õ�������������ʹ��������������ܰ��մ��������������ִ������
		System.out.println("��������ʼʱ���ǣ�"+scheduler.scheduleJob(jobDetail, trigger));
		// 5����
		scheduler.start();
		scheduler.standby();
		scheduler.shutdown(true);//��ʾ�ȴ���������ִ�е�jobִ�����֮���ڹر�Scheduler
		scheduler.shutdown(false);//��ʾǿ�ƹر�Scheduler
	}
}
