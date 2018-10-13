package com.irissz.quartz.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class HelloJob2 implements Job {

	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
		System.out.println("������Ϣ��"+jobDetail.getJobDataMap().get("message"));
		System.out.println(jobDetail.getJobDataMap().get("hh"));
		Trigger trigger = context.getTrigger();
		System.out.println(message);
		System.out.println(trigger.getJobDataMap().get("message"));
		System.out.println("��ǰ�����ִ��ʱ�䣺"+context.getFireTime());
		System.out.println("��һ�������ִ��ʱ�䣺"+context.getNextFireTime());
	}

}
