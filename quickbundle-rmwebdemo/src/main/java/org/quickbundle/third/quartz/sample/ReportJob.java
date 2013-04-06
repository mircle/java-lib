package org.quickbundle.third.quartz.sample;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ReportJob implements Job {
	private final static Logger log = Logger.getLogger(ReportJob.class);
	public void execute(JobExecutionContext context)
			throws JobExecutionException {	
		log.info("开始报表处理");
		try {
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			Thread.sleep(6000);
			context.setResult("OK!");
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("报表生成在"+context.getMergedJobDataMap().getString("src")+"目录");
	}

}
