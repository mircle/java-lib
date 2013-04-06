package org.quickbundle.modules.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.tools.support.buffer.CommonBuffer;

import org.quickbundle.modules.log.rmlog.service.IRmLogService;
import org.quickbundle.modules.log.rmlog.util.IRmLogConstants;
import org.quickbundle.modules.log.rmlog.vo.RmLogVo;

public class Action2DbLogBuffer extends CommonBuffer<RmLogVo> {
	/**
	 * @return 缓冲器名称
	 */
	public String getName() {
		return "业务日志缓冲器";
	}
	
	private volatile IRmLogService logService = null;
	public IRmLogService getLogService() {
		if(logService == null) {
			synchronized (this) {
				if(logService == null) {
					logService = (IRmLogService)RmBeanFactory.getBean(IRmLogConstants.SERVICE_KEY);
				}
			}
		}
		return logService;
	}
	@Override
	protected void flush(Queue<RmLogVo> buf) {
		try {
			List<RmLogVo> lvo = new ArrayList<RmLogVo>();
			RmLogVo vo;
			while((vo=buf.poll()) != null) {
				lvo.add(vo);
			}
			RmLogVo[] vos = lvo.toArray(new RmLogVo[0]);
			getLogService().insert(vos);
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("flush(): " + e.toString());
		}
	}
}