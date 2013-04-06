package org.quickbundle.third.quartz.sample;        

import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import org.quickbundle.third.quartz.util.ISchedulerConstants;
/**
 * @author liujia
 */
public class CleanJob implements Job {
	private final org.apache.log4j.Logger log=Logger.getLogger(getClass());
	private static int cleanCount=0;
	int test; 
	public void execute(JobExecutionContext context)
			throws JobExecutionException { 
		log.info("Clean my disk...");
		try {			
			System.out.print("MyJob Sleep No"+cleanCount+"...");
			cleanCount++;
			try {
				printJobs(context.getScheduler());
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.sleep(30000);
			context.setResult(ISchedulerConstants.Result.OK.value());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	public void printJobs(Scheduler scheduler) throws SchedulerException{
		List<JobExecutionContext> jobs= scheduler.getCurrentlyExecutingJobs();
		for(JobExecutionContext job:jobs){		
			System.out.println("Job-"+job.getFireTime());
		}
	}
}
