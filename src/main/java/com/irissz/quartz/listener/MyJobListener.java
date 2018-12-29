package com.irissz.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobListener implements JobListener{

	@Override
	public String getName() {
		String name = getClass().getSimpleName();
		System.out.println("��ȡ�������ƣ�"+name);
		return name;//������뷵��һ��ֵ������ᱨ��JobListener name cannot be empty.�쳣
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		String jobname=context.getJobDetail().getKey().getName();
		System.out.println(jobname+"     Scheduler��jobDetail��Ҫ��ִ�еķ�����");
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobname=context.getJobDetail().getKey().getName();
		System.out.println(jobname+"    Scheduler��JobDetail������ִ�У����ֱ�TriggerListener���ʱ����ø÷�����	");
		
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobname=context.getJobDetail().getKey().getName();
		System.out.println(jobname+"    Scheduler��JobDetail��ִ��֮����õķ���");
		
	}

}
