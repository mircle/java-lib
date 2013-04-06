//代码生成时,文件路径: E:/platform/myProject/qbrm/code/QbRmWebDemo2/src/main/java/org/quickbundle/modules/quickquery/rmquickquery/util/IRmQuickQueryConstants.java
//代码生成时,系统时间: 2011-10-10 14:24:13
//代码生成时,操作系统用户: qb

/*
 * 系统名称:单表模板 --> QbRmWebDemo2
 * 
 * 文件名称: org.quickbundle.modules.quickquery.rmquickquery.util --> IRmQuickQueryConstants.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2011-10-10 14:24:13 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.quickquery.rmquickquery.util;

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

public interface IRmQuickQueryConstants extends IGlobalConstants {

    //Service的规范化名称
    public final static String SERVICE_KEY = "IRmQuickQueryService";

    //Sql语句
    public final static String AFTER_SELECT_ALL   = "RM_QUICK_QUERY.ID, RM_QUICK_QUERY.NAME, RM_QUICK_QUERY.BS_KEYWORD, RM_QUICK_QUERY.IS_ALONE_TABLE, RM_QUICK_QUERY.QQDATA_TABLE_NAME, RM_QUICK_QUERY.QQDATA_INDEX_TABLE_NAME, RM_QUICK_QUERY.OLD_RESOURCE_ID_TYPE, RM_QUICK_QUERY.CUSTOM_CODE, RM_QUICK_QUERY.DESCRIPTION, RM_QUICK_QUERY.USABLE_STATUS, RM_QUICK_QUERY.MODIFY_DATE, RM_QUICK_QUERY.MODIFY_IP, RM_QUICK_QUERY.MODIFY_USER_ID";
    public final static String AFTER_SELECT_SHORT = "RM_QUICK_QUERY.ID, RM_QUICK_QUERY.NAME, RM_QUICK_QUERY.BS_KEYWORD, RM_QUICK_QUERY.IS_ALONE_TABLE, RM_QUICK_QUERY.QQDATA_TABLE_NAME, RM_QUICK_QUERY.QQDATA_INDEX_TABLE_NAME, RM_QUICK_QUERY.OLD_RESOURCE_ID_TYPE, RM_QUICK_QUERY.CUSTOM_CODE, RM_QUICK_QUERY.DESCRIPTION";

    public final static String SQL_INSERT = "insert into RM_QUICK_QUERY ( ID, NAME, BS_KEYWORD, IS_ALONE_TABLE, QQDATA_TABLE_NAME, QQDATA_INDEX_TABLE_NAME, OLD_RESOURCE_ID_TYPE, CUSTOM_CODE, DESCRIPTION, USABLE_STATUS, MODIFY_DATE, MODIFY_IP, MODIFY_USER_ID) values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
    
    public final static String SQL_DELETE_BY_ID = "delete from RM_QUICK_QUERY where ID=?";
    
    public final static String SQL_DELETE_MULTI_BY_IDS = "delete from RM_QUICK_QUERY ";

    public final static String SQL_FIND_BY_ID = "select " + AFTER_SELECT_ALL + " from RM_QUICK_QUERY where RM_QUICK_QUERY.ID=?";

    public final static String SQL_UPDATE_BY_ID = "update RM_QUICK_QUERY set NAME=?, BS_KEYWORD=?, IS_ALONE_TABLE=?, QQDATA_TABLE_NAME=?, QQDATA_INDEX_TABLE_NAME=?, OLD_RESOURCE_ID_TYPE=?, CUSTOM_CODE=?, DESCRIPTION=?, USABLE_STATUS=?, MODIFY_DATE=?, MODIFY_IP=?, MODIFY_USER_ID=?  where ID=?";
    
    public final static String SQL_COUNT = "select count(RM_QUICK_QUERY.ID) from RM_QUICK_QUERY";
    
    public final static String SQL_QUERY_ALL = "select " + AFTER_SELECT_SHORT + " from RM_QUICK_QUERY";

	public final static String SQL_QUERY_ALL_EXPORT = "select " + AFTER_SELECT_ALL + " from RM_QUICK_QUERY";
    
    //表名
    public final static String TABLE_NAME = "RM_QUICK_QUERY";
    
    //表名汉化
    public final static String TABLE_NAME_CHINESE = "快搜类别";
    
    //列名汉化
    @SuppressWarnings("unchecked")
    public final static Map<String, String> TABLE_COLUMN_CHINESE = new CaseInsensitiveMap(){{
		
		put("id","主键");
		put("name","名称");
		put("bs_keyword","业务关键字");
		put("is_alone_table","是否单独建表");
		put("qqdata_table_name","快搜数据表名");
		put("qqdata_index_table_name","快搜数据索引表名");
		put("old_resource_id_type","资源原始ID类型");
		put("custom_code","定制代码");
		put("description","描述");
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
    public final static String[] DEFAULT_CONDITION_KEY_ARRAY = new String[]{"id" };
}