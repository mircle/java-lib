//代码生成时,文件路径: E:/platform/myProject/qbrm/code/QbRmWebDemo2/src/main/java/org/quickbundle/modules/quickquery/rmquickquerydata/util/IRmQuickQueryDataConstants.java
//代码生成时,系统时间: 2011-10-10 14:24:15
//代码生成时,操作系统用户: qb

/*
 * 系统名称:单表模板 --> QbRmWebDemo2
 * 
 * 文件名称: org.quickbundle.modules.quickquery.rmquickquerydata.util --> IRmQuickQueryDataConstants.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2011-10-10 14:24:15 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.quickquery.rmquickquerydata.util;

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

public interface IRmQuickQueryDataConstants extends IGlobalConstants {

    //Service的规范化名称
    public final static String SERVICE_KEY = "IRmQuickQueryDataService";

    //Sql语句
    public final static String AFTER_SELECT_ALL   = "RM_QUICK_QUERY_DATA.ID, RM_QUICK_QUERY_DATA.QUICK_QUERY_ID, " +
    		"(select NAME from RM_QUICK_QUERY where RM_QUICK_QUERY.ID=RM_QUICK_QUERY_DATA.QUICK_QUERY_ID) QUICK_QUERY_ID_NAME, " +
    		"RM_QUICK_QUERY_DATA.OLD_RESOURCE_NAME, RM_QUICK_QUERY_DATA.OLD_RESOURCE_CODE, RM_QUICK_QUERY_DATA.OLD_RESOURCE_ID_NUMBER, RM_QUICK_QUERY_DATA.OLD_RESOURCE_ID_CHAR, RM_QUICK_QUERY_DATA.USABLE_STATUS, RM_QUICK_QUERY_DATA.MODIFY_DATE, RM_QUICK_QUERY_DATA.MODIFY_IP, RM_QUICK_QUERY_DATA.MODIFY_USER_ID";
    public final static String AFTER_SELECT_SHORT = "RM_QUICK_QUERY_DATA.ID, RM_QUICK_QUERY_DATA.QUICK_QUERY_ID, " +
    		"(select NAME from RM_QUICK_QUERY where RM_QUICK_QUERY.ID=RM_QUICK_QUERY_DATA.QUICK_QUERY_ID) QUICK_QUERY_ID_NAME, " +
    		"RM_QUICK_QUERY_DATA.OLD_RESOURCE_NAME, RM_QUICK_QUERY_DATA.OLD_RESOURCE_CODE, RM_QUICK_QUERY_DATA.OLD_RESOURCE_ID_NUMBER, RM_QUICK_QUERY_DATA.OLD_RESOURCE_ID_CHAR";

    public final static String SQL_INSERT = "insert into RM_QUICK_QUERY_DATA ( ID, QUICK_QUERY_ID, OLD_RESOURCE_NAME, OLD_RESOURCE_CODE, OLD_RESOURCE_ID_NUMBER, OLD_RESOURCE_ID_CHAR, USABLE_STATUS, MODIFY_DATE, MODIFY_IP, MODIFY_USER_ID) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    
    public final static String SQL_DELETE_BY_ID = "delete from RM_QUICK_QUERY_DATA where ID=?";
    
    public final static String SQL_DELETE_MULTI_BY_IDS = "delete from RM_QUICK_QUERY_DATA ";

    public final static String SQL_FIND_BY_ID = "select " + AFTER_SELECT_ALL + " from RM_QUICK_QUERY_DATA where RM_QUICK_QUERY_DATA.ID=?";

    public final static String SQL_UPDATE_BY_ID = "update RM_QUICK_QUERY_DATA set QUICK_QUERY_ID=?, OLD_RESOURCE_NAME=?, OLD_RESOURCE_CODE=?, OLD_RESOURCE_ID_NUMBER=?, OLD_RESOURCE_ID_CHAR=?, USABLE_STATUS=?, MODIFY_DATE=?, MODIFY_IP=?, MODIFY_USER_ID=?  where ID=?";
    
    public final static String SQL_COUNT = "select count(RM_QUICK_QUERY_DATA.ID) from RM_QUICK_QUERY_DATA";
    
    public final static String SQL_QUERY_ALL = "select " + AFTER_SELECT_SHORT + " from RM_QUICK_QUERY_DATA";

	public final static String SQL_QUERY_ALL_EXPORT = "select " + AFTER_SELECT_ALL + " from RM_QUICK_QUERY_DATA";
    
    //表名
    public final static String TABLE_NAME = "RM_QUICK_QUERY_DATA";
    
    //表名汉化
    public final static String TABLE_NAME_CHINESE = "快搜数据";
    
    //列名汉化
    @SuppressWarnings("unchecked")
    public final static Map<String, String> TABLE_COLUMN_CHINESE = new CaseInsensitiveMap(){{
		
		put("id","主键");
		put("quick_query_id","快搜类别ID");
		put("old_resource_name","资源原始名称");
		put("old_resource_code","资源原始编码");
		put("old_resource_id_number","资源原始ID数值");
		put("old_resource_id_char","资源原始ID字符");
		put("usable_status","数据可用状态");
		put("modify_date","修改日期");
		put("modify_ip","修改IP");
		put("modify_user_id","修改用户ID");
    }};
    
    //日志类型名称
    public final static String TABLE_LOG_TYPE_NAME = TABLE_NAME_CHINESE + "管理";
    
    //默认启用的查询条件
    public final static String DEFAULT_SQL_WHERE_USABLE = ""; //" where " + DESC_USABLE_STATUS_EVALUATE_ENABLE
    
    public final static String DEFAULT_SQL_CONTACT_KEYWORD = " where ";
    
    //默认的排序字段
    public final static String DEFAULT_ORDER_BY_CODE = ""; //ORDER_BY_SYMBOL + DESC_ORDER_CODE
    
    //子表查询条件，[0]作为默认条件查询字段
    public final static String[] DEFAULT_CONDITION_KEY_ARRAY = new String[]{"quick_query_id", "quick_query_id_name" };
}