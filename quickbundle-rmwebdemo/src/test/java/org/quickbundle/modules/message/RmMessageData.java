package org.quickbundle.modules.message;

import org.quickbundle.modules.message.vo.RmMessageVo;

/**
 * Task相关实体测试数据生成.
 * 
 * @author calvin
 */
public class RmMessageData {

	public static RmMessageVo randomTask() {
		RmMessageVo message = new RmMessageVo();
		message.setBiz_keyword("hello");
		return message;
	}
}
