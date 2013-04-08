//代码生成时,文件路径: E:/platform/myProject/navinfo/code/nifl/src/main/java/org/quickbundle/modules/affix/rmaffix/util/IRmAffixConstants.java
//代码生成时,系统时间: 2010-07-26 01:03:42
//代码生成时,操作系统用户: Administrator

/*
 * 系统名称:单表模板 --> nifl
 * 
 * 文件名称: org.quickbundle.modules.affix.rmaffix.util --> IRmAffixConstants.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2010-07-26 01:03:42 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.message;

import java.util.Map;

import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.quickbundle.project.IGlobalConstants;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public interface IRmMessageConstants extends IGlobalConstants {

    //Service的规范化名称
    public final static String SERVICE_KEY = "IRmMessageService";

    //表名、显示名
    public final static String TABLE_NAME = "RM_MESSAGE";
    public final static String TABLE_NAME_DISPLAY = "消息";

    //列名汉化
    @SuppressWarnings({ "unchecked", "serial" })
    public final static Map<String, String> TABLE_COLUMN_DISPLAY = new CaseInsensitiveMap(){{
		
		put("id","主键");
		put("biz_keyword","业务关键字");
		put("sender_id","发送人ID");
		put("parent_message_id","父消息ID");
		put("owner_org_id","所属组织ID");
		put("template_id","模板ID");
		put("is_affix","有无附件");
		put("record_id","主记录ID");
		put("message_xml_context","消息XML内容");
		put("usable_status","数据可用状态");
		put("modify_date","修改日期");
		put("modify_ip","修改IP");
		put("modify_user_id","修改用户ID");
    }};
    
    //日志类型名称
    public final static String LOG_TYPE_NAME = TABLE_NAME_DISPLAY + "管理";
}