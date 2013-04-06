//代码生成时,文件路径: E:/platform/myProject/qbrm/code/QbRmWebDemo2/src/main/java/org/quickbundle/modules/quickquery/rmquickquerydata/util/exception/RmQuickQueryDataException.java
//代码生成时,系统时间: 2011-10-10 14:24:15
//代码生成时,操作系统用户: qb

/*
 * 系统名称:单表模板 --> QbRmWebDemo2
 * 
 * 文件名称: org.quickbundle.modules.quickquery.rmquickquerydata.util.exception --> RmQuickQueryDataException.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2011-10-10 14:24:15 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.quickquery.rmquickquerydata.util;

import org.quickbundle.base.exception.RmRuntimeException;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public class RmQuickQueryDataException extends RmRuntimeException {
	/**
	 * 构造函数
	 */
	public RmQuickQueryDataException() {
		super();
	}
    /**
     * 构造函数:
     * @param msg
     */
    public RmQuickQueryDataException(String msg) {
        super(msg);
    }
    
    /**
     * 构造函数:
     * @param t
     */
    public RmQuickQueryDataException(Throwable t) {
        super(t);
    }

    /**
     * 构造函数:
     * @param msg
     * @param t
     */
    public RmQuickQueryDataException(String msg, Throwable t) {
        super(msg, t);
    }
    
	/**
     * 构造函数:
     * @param msg
     * @param t
     * @param returnObj
     */
    public RmQuickQueryDataException(String msg, Throwable t, Object returnObj) {
        super(msg, t, returnObj);
    }
}