//代码生成时,文件路径: E:/platform/myProject/qbrm/code/QbRmWebDemo2/src/main/java/org/quickbundle/modules/quickquery/rmquickquery/vo/RmQuickQueryVo.java
//代码生成时,系统时间: 2011-10-10 14:24:13
//代码生成时,操作系统用户: qb

/*
 * 系统名称:单表模板 --> QbRmWebDemo2
 * 
 * 文件名称: org.quickbundle.modules.quickquery.rmquickquery.vo --> RmQuickQueryVo.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2011-10-10 14:24:13 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.quickquery.rmquickquery.vo;


import java.sql.Timestamp;

import org.dom4j.Document;
import org.quickbundle.base.vo.RmValueObject;
import org.quickbundle.project.IGlobalConstants;
import org.quickbundle.modules.quickquery.rmquickquerydata.util.IRmQuickQueryDataConstants;
import org.quickbundle.tools.helper.xml.RmXmlHelper;
import org.quickbundle.tools.support.log.RmLogHelper;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public class RmQuickQueryVo extends RmValueObject{
    
    //开始rm_code_type的属性
    
	/**
     * id 表示: 主键
	 * 数据库注释: 
     */
    private String id;
	/**
     * name 表示: 名称
	 * 数据库注释: 
     */
    private String name;
	/**
     * bs_keyword 表示: 业务关键字
	 * 数据库注释: 
     */
    private String bs_keyword;
	/**
     * is_alone_table 表示: 是否单独建表
	 * 数据库注释: $RM_YES_NOT=是、否{ 0=否, 1=是 }
     */
    private String is_alone_table;
	/**
     * qqdata_table_name 表示: 快搜数据表名
	 * 数据库注释: 
     */
    private String qqdata_table_name;
	/**
     * qqdata_index_table_name 表示: 快搜数据索引表名
	 * 数据库注释: 
     */
    private String qqdata_index_table_name;
	/**
     * old_resource_id_type 表示: 资源原始ID类型
	 * 数据库注释: $RM_OLD_RESOURCE_ID_TYPE=资源原始ID类型{ 1=字符, 2=数字 }
     */
    private String old_resource_id_type;
	/**
     * custom_code 表示: 定制代码
	 * 数据库注释: 
     */
    private String custom_code;
	/**
     * description 表示: 描述
	 * 数据库注释: 
     */
    private String description;
	/**
     * usable_status 表示: 数据可用状态
	 * 数据库注释: 
     */
    private String usable_status;
	/**
     * modify_date 表示: 修改日期
	 * 数据库注释: 
     */
    private Timestamp modify_date;
	/**
     * modify_ip 表示: 修改IP
	 * 数据库注释: 
     */
    private String modify_ip;
	/**
     * modify_user_id 表示: 修改用户ID
	 * 数据库注释: 
     */
    private String modify_user_id;        
    //结束rm_code_type的属性
        
        
    //开始rm_code_type的setter和getter方法
    
    /**
     * 获得主键
     * 
     * @return 主键
     */
	public String getId(){
		return id;
	}
	
    /**
     * 设置主键
     * 
     * @param id 主键
     */
	public void setId(String id){
		this.id = id;
	}
	
    /**
     * 获得名称
     * 
     * @return 名称
     */
	public String getName(){
		return name;
	}
	
    /**
     * 设置名称
     * 
     * @param name 名称
     */
	public void setName(String name){
		this.name = name;
	}
	
    /**
     * 获得业务关键字
     * 
     * @return 业务关键字
     */
	public String getBs_keyword(){
		return bs_keyword;
	}
	
    /**
     * 设置业务关键字
     * 
     * @param bs_keyword 业务关键字
     */
	public void setBs_keyword(String bs_keyword){
		this.bs_keyword = bs_keyword;
	}
	
    /**
     * 获得是否单独建表
     * 数据库注释: $RM_YES_NOT=是、否{ 0=否, 1=是 }
     * @return 是否单独建表
     */
	public String getIs_alone_table(){
		return is_alone_table;
	}
	
    /**
     * 设置是否单独建表
     * 数据库注释: $RM_YES_NOT=是、否{ 0=否, 1=是 }
     * @param is_alone_table 是否单独建表
     */
	public void setIs_alone_table(String is_alone_table){
		this.is_alone_table = is_alone_table;
	}
	
    /**
     * 获得快搜数据表名
     * 
     * @return 快搜数据表名
     */
	public String getQqdata_table_name(){
		return qqdata_table_name;
	}
	
    /**
     * 设置快搜数据表名
     * 
     * @param qqdata_table_name 快搜数据表名
     */
	public void setQqdata_table_name(String qqdata_table_name){
		this.qqdata_table_name = qqdata_table_name;
	}
	
    /**
     * 获得快搜数据索引表名
     * 
     * @return 快搜数据索引表名
     */
	public String getQqdata_index_table_name(){
		return qqdata_index_table_name;
	}
	
    /**
     * 设置快搜数据索引表名
     * 
     * @param qqdata_index_table_name 快搜数据索引表名
     */
	public void setQqdata_index_table_name(String qqdata_index_table_name){
		this.qqdata_index_table_name = qqdata_index_table_name;
	}
	
    /**
     * 获得资源原始ID类型
     * 数据库注释: $RM_OLD_RESOURCE_ID_TYPE=资源原始ID类型{ 1=字符, 2=数字 }
     * @return 资源原始ID类型
     */
	public String getOld_resource_id_type(){
		return old_resource_id_type;
	}
	
    /**
     * 设置资源原始ID类型
     * 数据库注释: $RM_OLD_RESOURCE_ID_TYPE=资源原始ID类型{ 1=字符, 2=数字 }
     * @param old_resource_id_type 资源原始ID类型
     */
	public void setOld_resource_id_type(String old_resource_id_type){
		this.old_resource_id_type = old_resource_id_type;
	}
	
    /**
     * 获得定制代码
     * 
     * @return 定制代码
     */
	public String getCustom_code(){
		return custom_code;
	}
	
    /**
     * 设置定制代码
     * 
     * @param custom_code 定制代码
     */
	public void setCustom_code(String custom_code){
		this.custom_code = custom_code;
	}
	
    /**
     * 获得描述
     * 
     * @return 描述
     */
	public String getDescription(){
		return description;
	}
	
    /**
     * 设置描述
     * 
     * @param description 描述
     */
	public void setDescription(String description){
		this.description = description;
	}
	
    /**
     * 获得数据可用状态
     * 
     * @return 数据可用状态
     */
	public String getUsable_status(){
		return usable_status;
	}
	
    /**
     * 设置数据可用状态
     * 
     * @param usable_status 数据可用状态
     */
	public void setUsable_status(String usable_status){
		this.usable_status = usable_status;
	}
	
    /**
     * 获得修改日期
     * 
     * @return 修改日期
     */
	public Timestamp getModify_date(){
		return modify_date;
	}
	
    /**
     * 设置修改日期
     * 
     * @param modify_date 修改日期
     */
	public void setModify_date(Timestamp modify_date){
		this.modify_date = modify_date;
	}
	
    /**
     * 获得修改IP
     * 
     * @return 修改IP
     */
	public String getModify_ip(){
		return modify_ip;
	}
	
    /**
     * 设置修改IP
     * 
     * @param modify_ip 修改IP
     */
	public void setModify_ip(String modify_ip){
		this.modify_ip = modify_ip;
	}
	
    /**
     * 获得修改用户ID
     * 
     * @return 修改用户ID
     */
	public String getModify_user_id(){
		return modify_user_id;
	}
	
    /**
     * 设置修改用户ID
     * 
     * @param modify_user_id 修改用户ID
     */
	public void setModify_user_id(String modify_user_id){
		this.modify_user_id = modify_user_id;
	}
	
    //结束rm_code_type的setter和getter方法
    
	/**
	 * 
<qq sql_after_from="RM_CODE_TYPE" join_table="RM_CODE_TYPE" join_table_column="ID" join_table_column_full="" join_table_column_pk="ID" join_table_column_key="NAME" sql_after_where="">
  <consumer>
    <table sql_after_from="RM_CODE_TYPE" join_table="RM_CODE_TYPE" join_table_column="ID" old_resource_id_full=""/>
  </consumer>
</qq>
	 * 
	 * 获得规则
	 * @return
	 */
	public Document getRuleCustomCode() {
		if(custom_code == null || custom_code.trim().length() == 0) {
			return null;
		}
		try {
			return RmXmlHelper.getDocumentFromString(custom_code);
		} catch (Exception e) {
			RmLogHelper.getLogger(this.getClass()).warn("getRuleCustomCode():" + e.toString() + " cause:" + e.getCause());
			return null;
		}
	}
	
	/**
	 * @return RM_QUICK_QUERY_DATA的表名
	 */
	public String getTableName_qqdata() {
		if(IGlobalConstants.RM_YES.equals(is_alone_table)) {
			return qqdata_table_name;
		} else {
			return IRmQuickQueryDataConstants.TABLE_NAME;
		}
	}
	
	/**
	 * 得到OLD_RESOURCE_ID的列名
	 * @return
	 */
	public String getColumnName_oldResourceId() {
    	if("1".equals(old_resource_id_type)) {
    		return "OLD_RESOURCE_ID_CHAR";
    	} else {
    		return "OLD_RESOURCE_ID_NUMBER";
    	}
	}
	
	
	public String buildSqlQqp(String q) {
		StringBuilder sql = new StringBuilder();
		Document rule = getRuleCustomCode();
		sql.append("select ");
		sql.append(rule.valueOf("/qq/@join_table"));
		sql.append(".");
		sql.append(rule.valueOf("/qq/@join_table_column_key"));
		sql.append(" rm_key_column from ");
		sql.append(rule.valueOf("/qq/@sql_after_from"));
		sql.append(" where ");
		sql.append(rule.valueOf("/qq/@join_table"));
		sql.append(".");
		sql.append(rule.valueOf("/qq/@join_table_column"));
		sql.append("='");
		sql.append(q);
		sql.append("'");
		return sql.toString();
	}
	
	public String buildSqlQqs(String q, String sqlAfterFrom) {
		StringBuilder sql = new StringBuilder();
		Document rule = getRuleCustomCode();
		sql.append("select distinct RM_QUICK_QUERY_DATA.");
		sql.append(getColumnName_oldResourceId());
		sql.append(", ");
		sql.append(rule.valueOf("/qq/@join_table"));
		sql.append(".");
		sql.append(rule.valueOf("/qq/@join_table_column_key"));
		sql.append(" from ");
		sql.append(rule.valueOf("/qq/@sql_after_from"));
		sql.append(" join ");
		sql.append(getTableName_qqdata());
		sql.append(" RM_QUICK_QUERY_DATA on RM_QUICK_QUERY_DATA.QUICK_QUERY_ID=");
		sql.append(id);
		sql.append(" and RM_QUICK_QUERY_DATA.");
		sql.append(getColumnName_oldResourceId());
		sql.append("=");
		sql.append(rule.valueOf("/qq/@join_table"));
		sql.append(".");
		sql.append(rule.valueOf("/qq/@join_table_column"));
		sql.append(" where RM_QUICK_QUERY_DATA.INDEX_VALUE like '");
		sql.append(q);
		sql.append("%' or RM_QUICK_QUERY_DATA.OLD_RESOURCE_NAME like '");
		sql.append(q);
		sql.append("%'");
		return sql.toString();
	}
}