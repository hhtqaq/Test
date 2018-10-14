package com.irissz.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

/**
 * ����������
 * @author Administrator
 *
 */
@PersistJobDataAfterExecution     //��ε���Jobʱ�����Job���г־û�������һ�����ݵ���Ϣ
public class HelloJob3 implements Job{
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//��ȡJobKey
		JobKey key = context.getJobDetail().getKey();
		//��ȡ��������ʼʱ��
		System.out.println("��������ʼʱ��"+context.getTrigger().getStartTime());
		System.out.println("����������ʱ��"+context.getTrigger().getEndTime());
		
	}
	public static void main(String[] args) throws Exception {
		//�������������
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		//����Jobʵ��
		JobDetail jobDetail = JobBuilder.newJob(HelloJob3.class)
				.withIdentity("job1", "group1")
				.build();
		//����������
		Trigger trigger = TriggerBuilder
				.newTrigger()
				.endAt(new Date())
				.build();
		//����
		scheduler.scheduleJob(jobDetail, trigger);
		//��ʼ
		scheduler.start();
		
	}  
}
