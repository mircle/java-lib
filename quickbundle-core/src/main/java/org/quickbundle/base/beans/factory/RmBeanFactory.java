package org.quickbundle.base.beans.factory;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.quickbundle.base.web.servlet.RmHolderServlet;
import org.quickbundle.project.init.RmConfig;
import org.quickbundle.tools.helper.RmStringHelper;
import org.quickbundle.tools.helper.xml.RmXmlHelper;
import org.quickbundle.tools.support.log.RmLogHelper;
import org.quickbundle.tools.support.path.RmPathHelper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class RmBeanFactory {
	private static Logger log = RmLogHelper.getLogger(RmBeanFactory.class);
    /**
     * 全局单例的BeanFactory
     */
    private static BeanFactory beanFactory = null;

    /**
     * 已经开始初始化过程
     */
    private static volatile boolean initBeanStarted = false;
    public static void setInitBeanStarted(boolean initBeanStarted_) {
		initBeanStarted = initBeanStarted_;
	}
    
	/**
	 * 初始化过程完毕
	 */
	private static volatile boolean isInitBean = false;
	
    /**
     * 初始化时的锁，用于将调用getBean的消费者排队等待
     */
    public static Object lockInitFactory = new Object();
    
    /**
     * 自执行初始化BeanFactory
     */
    private static void defaultInitBeanFactory() {
		try {
			long startTime = System.currentTimeMillis();
			log.info("start init bean......");
			log.info(RmPathHelper.initWarRoot());
			String springPath = "/WEB-INF/config/spring/*.xml";
			if (RmHolderServlet.getDefaultServletContext() != null) {
				springPath = RmHolderServlet.getDefaultServletContext().getInitParameter("contextConfigLocation");
			}
			if(springPath.indexOf(",") > -1) {
				List<String> lPath = new ArrayList<String>();
				String[] paths = springPath.split(",");
				for(String path : paths) {
					if(path.trim().length() == 0) {
						continue;
					}
					lPath.add(RmXmlHelper.formatToUrl(RmPathHelper.getWarDir() + path.trim()));
				}
				beanFactory = new FileSystemXmlApplicationContext(lPath.toArray(new String[0]));
			} else {
				beanFactory = new FileSystemXmlApplicationContext(RmXmlHelper.formatToUrl(RmPathHelper.getWarDir() + springPath));
			}
			log.info("end init bean, time=" + (System.currentTimeMillis() - startTime) + " milliseconds");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("defaultInitBeanFactory fail", e);
		}
    }
    
    /**
     * 返回BeanFactory
     * 
     * @return
     */
    public static BeanFactory getBeanFactory() {
        if(!isInitBean) {
    		synchronized(lockInitFactory) { //lock and init
    			if(!isInitBean) {
    		    	setInitBeanStarted(true);
    				defaultInitBeanFactory();
    				isInitBean = true;
    			}
			}
        }
        return beanFactory;
    }
    
    /**
     * 手动设置BeanFactory，一般由Web.xml配置启动的Listener调用
     * 
     * @param bf
     */
    public static void setBeanFactory(BeanFactory bf) {
    	if(bf == null) {
    		throw new RuntimeException("BeanFactory can not be null!");
    	}
    	beanFactory = bf;
    	isInitBean = true;
    }
    
    /**
     * get bean from spring container|从Spring容器中获取对象
     * @param beanName
     * @return
     */
    public static Object getBean(String beanName) {
    	if(!isInitBean && initBeanStarted) { //如果未启动完毕，但已经开始启动，则不走getBeanFactory()防止触发创建第2个BeanFactory
    		if(beanFactory != null) {
    			return beanFactory.getBean(beanName);
    		}
			synchronized(lockInitFactory) { //wait for process that initing beanFactory
				//nothing
			}
			if(!isInitBean) {
				StringBuilder warnInfo = new StringBuilder("RmBeanFactory.getBean(\"");
				warnInfo.append(beanName);
				warnInfo.append("\") return null, StackTrace:");
				if(RmConfig.systemDebugMode()) {
					warnInfo.append("\n");
					warnInfo.append(RmStringHelper.getStackTrace(10000));
				}
				log.warn(warnInfo.toString());
				return null;
			}
    	}
    	return getBeanFactory().getBean(beanName);
    }
}