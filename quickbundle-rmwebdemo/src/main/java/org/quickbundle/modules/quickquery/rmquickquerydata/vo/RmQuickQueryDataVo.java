//代码生成时,文件路径: E:/platform/myProject/qbrm/code/QbRmWebDemo2/src/main/java/org/quickbundle/modules/quickquery/rmquickquerydata/vo/RmQuickQueryDataVo.java
//代码生成时,系统时间: 2011-10-10 14:24:15
//代码生成时,操作系统用户: qb

/*
 * 系统名称:单表模板 --> QbRmWebDemo2
 * 
 * 文件名称: org.quickbundle.modules.quickquery.rmquickquerydata.vo --> RmQuickQueryDataVo.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2011-10-10 14:24:15 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.quickquery.rmquickquerydata.vo;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.quickbundle.base.vo.RmValueObject;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public class RmQuickQueryDataVo extends RmValueObject{
    
    //开始rm_code_type的属性
	
	private List<String> index_values = new ArrayList<String>();
	public List<String> getIndex_values() {
		return index_values;
	}
	public void addIndex_value(String index_value) {
		index_values.add(index_value);
	}

	/**
     * id 表示: 主键
	 * 数据库注释: 
     */
    private String id;
	/**
     * quick_query_id 表示: 快搜类别ID
	 * 数据库注释: 
     */
    private String quick_query_id;
    private String quick_query_id_name;
	public String getQuick_query_id_name() {
		return quick_query_id_name;
	}
	public void setQuick_query_id_name(String quick_query_id_name) {
		this.quick_query_id_name = quick_query_id_name;
	}

	/**
     * old_resource_name 表示: 资源原始名称
	 * 数据库注释: 
     */
    private String old_resource_name;
	/**
     * old_resource_code 表示: 资源原始编码
	 * 数据库注释: 
     */
    private String old_resource_code;
	/**
     * old_resource_id_number 表示: 资源原始ID数值
	 * 数据库注释: 
     */
    private String old_resource_id_number;
	/**
     * old_resource_id_char 表示: 资源原始ID字符
	 * 数据库注释: 
     */
    private String old_resource_id_char;
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
     * 获得快搜类别ID
     * 
     * @return 快搜类别ID
     */
	public String getQuick_query_id(){
		return quick_query_id;
	}
	
    /**
     * 设置快搜类别ID
     * 
     * @param quick_query_id 快搜类别ID
     */
	public void setQuick_query_id(String quick_query_id){
		this.quick_query_id = quick_query_id;
	}
	
    /**
     * 获得资源原始名称
     * 
     * @return 资源原始名称
     */
	public String getOld_resource_name(){
		return old_resource_name;
	}
	
    /**
     * 设置资源原始名称
     * 
     * @param old_resource_name 资源原始名称
     */
	public void setOld_resource_name(String old_resource_name){
		this.old_resource_name = old_resource_name;
	}
	
    /**
     * 获得资源原始编码
     * 
     * @return 资源原始编码
     */
	public String getOld_resource_code(){
		return old_resource_code;
	}
	
    /**
     * 设置资源原始编码
     * 
     * @param old_resource_code 资源原始编码
     */
	public void setOld_resource_code(String old_resource_code){
		this.old_resource_code = old_resource_code;
	}
	
    /**
     * 获得资源原始ID数值
     * 
     * @return 资源原始ID数值
     */
	public String getOld_resource_id_number(){
		return old_resource_id_number;
	}
	
    /**
     * 设置资源原始ID数值
     * 
     * @param old_resource_id_number 资源原始ID数值
     */
	public void setOld_resource_id_number(String old_resource_id_number){
		this.old_resource_id_number = old_resource_id_number;
	}
	
    /**
     * 获得资源原始ID字符
     * 
     * @return 资源原始ID字符
     */
	public String getOld_resource_id_char(){
		return old_resource_id_char;
	}
	
    /**
     * 设置资源原始ID字符
     * 
     * @param old_resource_id_char 资源原始ID字符
     */
	public void setOld_resource_id_char(String old_resource_id_char){
		this.old_resource_id_char = old_resource_id_char;
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
     * 智能获得资源原始ID
     * @return 资源原始ID
     */
	public String getOld_resource_id() {
		if(old_resource_id_char != null && old_resource_id_char.length() > 0) {
			return old_resource_id_char;
		} else {
			return old_resource_id_number;
		}
	}
	
	private String old_resource_id_name;
    /**
     * @return 资源原始ID名称
     */
	public String getOld_resource_id_name() {
		return old_resource_id_name;
	}
	public void setOld_resource_id_name(String old_resource_id_name) {
		this.old_resource_id_name = old_resource_id_name;
	}
}