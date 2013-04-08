package org.quickbundle.project.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;

import org.logicalcobwebs.proxool.ProxoolFacade;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.cache.RmCacheHandler;
import org.quickbundle.base.web.servlet.RmHolderServlet;
import org.quickbundle.project.init.RmWebApplicationInit;
import org.quickbundle.tools.support.buffer.FlushQueueThread;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.quickbundle.tools.support.path.RmPathHelper;
import org.slf4j.Logger;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 集成自Spring的监听器
 */
public class RmContextLoaderListener extends ContextLoaderListener {
	static Logger log = RmLogHelper.getLogger(RmContextLoaderListener.class);
	
	/*
	 * 启动服务时，初始化
	 *  (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		//系统初始化时，缓存第一个ServletContext对象
		RmHolderServlet.setDefaultServletContext(event.getServletContext());
		//更新RmConfig配置
		RmWebApplicationInit.initRmConfig();
		
		log.info(RmPathHelper.initWarRoot());
		synchronized (RmBeanFactory.lockInitFactory) {
			RmBeanFactory.setInitBeanStarted(true); //通知RmBeanFactory已开始启动BeanFactory
			super.contextInitialized(event);
			RmBeanFactory.setBeanFactory(WebApplicationContextUtils.getWebApplicationContext(event.getServletContext()));
		}
	}

	/* 
	 * 停止服务时，关闭资源
	 * (non-Javadoc)
	 * @see org.springframework.web.context.ContextLoaderListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		//quartz begin
		try {
			org.quartz.Scheduler scheduler = org.quartz.impl.StdSchedulerFactory.getDefaultScheduler();
			scheduler.shutdown(true);
			//等待quartz线程安全停止
			//doWaitForClearThread(10000, "org\\.quartz\\.simpl\\.SimpleThreadPool.*");
		} catch (Exception e) {
			log.error("scheduler.shutdown():" + e.toString());
		}
		//quartz end
		
		try {
			FlushQueueThread.getSingleton().shutdown();
			//等待RM-FlushQueue线程安全停止
			//doWaitForClearThread(10000, "RM-FlushQueue.*");
		} catch (Exception e) {
			log.error("FlushQueueThread.getSingleton().shutdown():" + e.toString());
		}
		
		try {
			RmCacheHandler.getSingleton().showdown();
			//等待RM-CacheHandler线程安全停止
			//doWaitForClearThread(5000, "RM-CacheHandler.*");
		} catch (Exception e) {
			log.error("RmCacheHandler.getSingleton().showdown():" + e.toString());
		}
		
		try {
			ProxoolFacade.shutdown(10000);
			//等待proxool连接池线程安全停止
			//doWaitForClearThread(10000, "org\\.logicalcobwebs\\.proxool\\.admin\\.StatsRoller.*");
		} catch (Exception e) {
			log.error("ProxoolFacade.shutdown():" + e.toString());
		}
		
		//deregisterJdbcDriver();
		super.contextDestroyed(event);
		printThreads();
	}
	
	/**
	 * 卸载jdbc驱动
	 */
	void deregisterJdbcDriver() {
		// This manually deregisters JDBC driver, which prevents Tomcat 7 from complaining about memory leaks wrto this class
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log.info(String.format("deregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                log.error(String.format("Error deregistering driver %s", driver), e);
            }

        }
	}
	
	/**
	 * 等待项目中的线程安全关闭，避免内存漏洞
	 */
	void doWaitForClearThread(long maxWaitTime, String patternThreadName) {
		if(maxWaitTime < 0) {
			maxWaitTime = 2000;
		}
		long waitTime = 0;
		try {
			while(waitTime < maxWaitTime) {
				String[] threadInfo = findMatchThread(patternThreadName);
				if(threadInfo != null) {
					if(waitTime > 0) {
						log.info("wait " + threadInfo[0] + "(" + threadInfo[1] + ") for shutdown perfectly, elapsed time: " + waitTime + " ms");
					}
					Thread.sleep(1000);
					waitTime += 1000;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			log.error("doWaitForClearThread():", e);
		}
	}
	
	/**
	 * 检查是否存在未安全关闭的线程
	 * @return
	 */
	String[] findMatchThread(String patternThreadName) {
		Thread[] threads = getThreads();
		for(Thread thread : threads) {
			if(thread == null) {
				continue;
			}
			try {
				if(thread.getName().matches(patternThreadName) || thread.getClass().getName().matches(patternThreadName)) {
					return new String[]{thread.getName(), thread.getClass().getName()};
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void printThreads() {
		Thread[] threads = getThreads();
		System.out.println("Threads in server:");
		int count = 0;
		for(Thread t : threads) {
			if(t != null) {
				System.out.println(t.getId() + "|" + t.getClass().getName() + "|" + t.getName() + "|" + (t.getContextClassLoader() != null ? t.getContextClassLoader().getClass().getName() : ""));
				count ++;
			}
		}
		System.out.println("count:[" + count + "]");
	}
	
    /*
     * Get the set of current threads as an array.
     */
   private Thread[] getThreads() {
       // Get the current thread group 
       ThreadGroup tg = Thread.currentThread( ).getThreadGroup( );
       // Find the root thread group
       while (tg.getParent() != null) {
           tg = tg.getParent();
       }
       
       int threadCountGuess = tg.activeCount() + 50;
       Thread[] threads = new Thread[threadCountGuess];
       int threadCountActual = tg.enumerate(threads);
       // Make sure we don't miss any threads
       while (threadCountActual == threadCountGuess) {
           threadCountGuess *=2;
           threads = new Thread[threadCountGuess];
           // Note tg.enumerate(Thread[]) silently ignores any threads that
           // can't fit into the array 
           threadCountActual = tg.enumerate(threads);
       }
       
       return threads;
   }
}