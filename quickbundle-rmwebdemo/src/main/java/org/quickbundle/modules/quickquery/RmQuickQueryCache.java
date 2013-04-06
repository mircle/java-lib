package org.quickbundle.modules.quickquery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.cache.RmAbstractCache;
import org.quickbundle.modules.quickquery.rmquickquery.service.IRmQuickQueryService;
import org.quickbundle.modules.quickquery.rmquickquery.util.IRmQuickQueryConstants;
import org.quickbundle.modules.quickquery.rmquickquery.vo.RmQuickQueryVo;
import org.quickbundle.tools.support.log.RmLogHelper;


public class RmQuickQueryCache extends RmAbstractCache {
    /**
     * 存放参照索引规则的Map，key是bs_keyword
     */
    private Map<String, RmQuickQueryVo> mQuickQueryByBs_keyword;
    
    /**
     * 存放参照索引规则的Map，key是id
     */
    private Map<String, RmQuickQueryVo> mQuickQueryById;

    /**
     * 功能: 获取参照索引规则
     *
     * @param bs_keyword
     * @return
     */
    public RmQuickQueryVo getQuickQueryByBs_keyword(String bs_keyword) {
    	init();
        return mQuickQueryByBs_keyword.get(bs_keyword);
    }
    
    public RmQuickQueryVo getQuickQueryById(String id) {
    	init();
        return mQuickQueryById.get(id);
    }
    
    private void init() {
        if(!isInit) {
            synchronized (RmQuickQueryCache.class) {
                if(!isInit) {
                	mQuickQueryByBs_keyword = new HashMap<String, RmQuickQueryVo>();
                	mQuickQueryById = new HashMap<String, RmQuickQueryVo>();
                    IRmQuickQueryService riService = (IRmQuickQueryService)RmBeanFactory.getBean(IRmQuickQueryConstants.SERVICE_KEY);
                    List<RmQuickQueryVo> lQuickQuery =  riService.queryByCondition(null, null, -1, -1, true);
                    for (RmQuickQueryVo vo : lQuickQuery) {
                    	mQuickQueryByBs_keyword.put(vo.getBs_keyword(),vo);
                    	mQuickQueryById.put(vo.getId(),vo);
                    }
                    RmLogHelper.getLogger(RmQuickQueryCache.class).info("init ok, mAuthorize.size()=" + mQuickQueryByBs_keyword.size());
                    isInit = true;
                }
            }
        }
    }
    
    /**
     * 全局单例
     */
    private static RmQuickQueryCache singleton = new RmQuickQueryCache();
    
	public static RmQuickQueryCache getSingleton() {
		return singleton;
	}
}
