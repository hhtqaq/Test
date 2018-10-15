package com.irissz.quartz.job;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * ����������
 * @author Administrator
 *
 */
@PersistJobDataAfterExecution     //��ε���Jobʱ�����Job���г־û�������һ�����ݵ���Ϣ
public class HelloJob implements Job{
	
	private int day;
	public void setDay(int day) {
		this.day=day;
	}
	public HelloJob() {
		System.out.println("����HelloJob  ���췽��");
	}
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("�����갵��Ů��һ�������ؿ�  ������ĵ�"+day+"��"+new Date());
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
	}
}
