//代码生成时,文件路径: E:/platform/myProject/qbrm/code/QbRmWebDemo2/src/main/java/org/quickbundle/modules/quickquery/rmquickquerydata/service/impl/RmQuickQueryDataService.java
//代码生成时,系统时间: 2011-10-10 14:24:14
//代码生成时,操作系统用户: qb

/*
 * 系统名称:单表模板 --> QbRmWebDemo2
 * 
 * 文件名称: org.quickbundle.modules.quickquery.rmquickquerydata.service.impl --> RmQuickQueryDataService.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2011-10-10 14:24:14 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.modules.quickquery.rmquickquerydata.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.quickbundle.base.cache.RmSqlCountCache;
import org.quickbundle.base.service.RmService;
import org.quickbundle.modules.quickquery.rmquickquery.vo.RmQuickQueryVo;
import org.quickbundle.modules.quickquery.rmquickquerydata.dao.IRmQuickQueryDataDao;
import org.quickbundle.modules.quickquery.rmquickquerydata.service.IRmQuickQueryDataService;
import org.quickbundle.modules.quickquery.rmquickquerydata.util.IRmQuickQueryDataConstants;
import org.quickbundle.modules.quickquery.rmquickquerydata.vo.RmQuickQueryDataVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.listener.RmRequestMonitor;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmStringHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.jdbc.core.RowMapper;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public class RmQuickQueryDataService extends RmService implements IRmQuickQueryDataService, IRmQuickQueryDataConstants {
    
    /**
     * dao 表示: 数据访问层的实例
     */
    private IRmQuickQueryDataDao dao = null;

    /**
     * 设置数据访问接口
     * 
     * @return
     */
    public IRmQuickQueryDataDao getDao() {
        return dao;
    }

    /**
     * 获取数据访问接口
     * 
     * @param dao
     */
    public void setDao(IRmQuickQueryDataDao dao) {
        this.dao = dao;
    }


    /**
     * 插入单条记录
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的Oid
     */
    public String insert(RmQuickQueryDataVo vo) {
        String id = getDao().insert(vo);
        List<String[]> lIndex = new ArrayList<String[]>();
    	for(String value : vo.getIndex_values()) {
    		lIndex.add(new String[]{value, vo.getId()});
    	}
    	if(lIndex.size() > 0) {
    		RmProjectHelper.getCommonServiceInstance().doUpdateBatch("insert into RM_QUICK_QUERY_DATA_INDEX(INDEX_VALUE, QUICK_QUERY_DATA_ID) values(?, ?)", lIndex.toArray(new String[0][0]));
    	}
        //RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "插入了1条记录,id=" + String.valueOf(id));
        RmSqlCountCache.clearCount(TABLE_NAME);  //清除count记录数缓存
		return id;
    }
    
    /**
     * 插入多条记录
     *
     * @param vos 用于添加的VO对象数组
     * @return 返回新生成的id数组
     */
    public String[] insert(RmQuickQueryDataVo[] vos) {
        String[] aId = getDao().insert(vos);
        List<String[]> lIndex = new ArrayList<String[]>();
        for(RmQuickQueryDataVo vo : vos) {
        	for(String value : vo.getIndex_values()) {
        		lIndex.add(new String[]{value, vo.getId()});
        	}
        }
        if(lIndex.size() > 0) {
        	RmProjectHelper.getCommonServiceInstance().doUpdateBatch("insert into RM_QUICK_QUERY_DATA_INDEX(INDEX_VALUE, QUICK_QUERY_DATA_ID) values(?, ?)", lIndex.toArray(new String[0][0]));
        }
        //RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "插入了" + vos.length + "条记录,id=" + RmStringHelper.ArrayToString(aId, ","));
        RmSqlCountCache.clearCount(TABLE_NAME);  //清除count记录数缓存
        return aId;
    }

    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
    	RmProjectHelper.getCommonServiceInstance().doUpdate("delete from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID=" + id);
		int sum = getDao().delete(id);
		//RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "删除了" + sum + "条记录,id=" + String.valueOf(id));
		RmSqlCountCache.clearCount(TABLE_NAME);  //清除count记录数缓存
		return sum;
    }

    /**
     * 删除多条记录
     * 
     * @param ids 用于删除的记录的ids
     * @return 成功删除的记录数
     */
    public int delete(String ids[]) {
    	RmProjectHelper.getCommonServiceInstance().doUpdate("delete from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID in(" + RmStringHelper.parseToSQLString(ids) + ")");
		int sum = getDao().delete(ids);
        //RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "删除了" + sum + "条记录,id=" + RmStringHelper.ArrayToString(ids, ","));
        RmSqlCountCache.clearCount(TABLE_NAME);  //清除count记录数缓存
		return sum;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public RmQuickQueryDataVo find(String id) {
		final RmQuickQueryDataVo vo = getDao().find(id);
		//填充index_value
		RmProjectHelper.getCommonServiceInstance().doQuery("select INDEX_VALUE, QUICK_QUERY_DATA_ID from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID=" + id, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String indexValue = rs.getString("INDEX_VALUE");
				if(!vo.getIndex_values().contains(indexValue)) {
					vo.addIndex_value(indexValue);
				}
				return null;
			}
		});
        //RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "察看了1条记录,id=" + id);
		return vo;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(RmQuickQueryDataVo vo) {
		int sum = getDao().update(vo);
		RmProjectHelper.getCommonServiceInstance().doUpdate("delete from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID=" + vo.getId());
		List<String[]> lIndex = new ArrayList<String[]>();
		for (String value : vo.getIndex_values()) {
			lIndex.add(new String[] { value, vo.getId() });
		}
		if (lIndex.size() > 0) {
			RmProjectHelper.getCommonServiceInstance().doUpdateBatch("insert into RM_QUICK_QUERY_DATA_INDEX(INDEX_VALUE, QUICK_QUERY_DATA_ID) values(?, ?)", lIndex.toArray(new String[0][0]));
		}
        //RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "更新了" + sum + "条记录,id=" + String.valueOf(vo.getId()));
        RmSqlCountCache.clearCount(TABLE_NAME);  //清除count记录数缓存
		return sum;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 更新的VO对象数组
     * @return 成功更新的记录最终数量
     */
	public int update(RmQuickQueryDataVo[] vos) {
		int[] sum = getDao().update(vos);
		int finalSum = 0;
		for (int i = 0; i < sum.length; i++) {
			finalSum += sum[i];
		}
		List<String> lId = new ArrayList<String>();
		for(RmQuickQueryDataVo vo : vos) {
			lId.add(vo.getId());
		}
		RmProjectHelper.getCommonServiceInstance().doUpdate("delete from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID in(" + RmStringHelper.parseToSQLString(lId.toArray(new String[0])) + ")");
        List<String[]> lIndex = new ArrayList<String[]>();
        for(RmQuickQueryDataVo vo : vos) {
        	for(String value : vo.getIndex_values()) {
        		lIndex.add(new String[]{value, vo.getId()});
        	}
        }
        if(lIndex.size() > 0) {
        	RmProjectHelper.getCommonServiceInstance().doUpdateBatch("insert into RM_QUICK_QUERY_DATA_INDEX(INDEX_VALUE, QUICK_QUERY_DATA_ID) values(?, ?)", lIndex.toArray(new String[0][0]));
        }
		//RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "批量更新了" + finalSum + "条记录);
        RmSqlCountCache.clearCount(TABLE_NAME);  //清除count记录数缓存
		return finalSum;
	}
	
	/**
	 * 批量保存，没有主键的insert，有主键的update
	 * 
	 * @param vos 更新的VO对象数组
	 * @return new int[2]{insert的记录数, update的记录数}	
	 */
	public int[] insertUpdateBatch(RmQuickQueryDataVo[] vos) {
		int[] sum_insert_update = new int[2];
		List<RmQuickQueryDataVo> lInsert = new ArrayList<RmQuickQueryDataVo>();
		List<RmQuickQueryDataVo> lUpdate = new ArrayList<RmQuickQueryDataVo>();
		for (int i = 0; i < vos.length; i++) {
			if(vos[i].getId() != null && vos[i].getId().trim().length() > 0) {
				lUpdate.add(vos[i]);
			} else {
				lInsert.add(vos[i]);
			}
		}
		if(lInsert.size() > 0) {
			sum_insert_update[0] = insert(lInsert.toArray(new RmQuickQueryDataVo[0])).length;
		}
		if(lUpdate.size() > 0) {
			sum_insert_update[1] = update(lUpdate.toArray(new RmQuickQueryDataVo[0]));
		}
		return sum_insert_update;
	}

    /**
     * 查询总记录数，带查询条件
     * 
     * @param queryCondition 查询条件
     * @return 总记录数
     */
    public int getRecordCount(String queryCondition) {
		int sum = getDao().getRecordCount(queryCondition);
		//RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "查询到了总记录数,count=" + sum + ", queryCondition=" + queryCondition);
		return sum;
    }

    /**
     * 功能: 通过查询条件获得所有的VO对象列表，不带翻页查全部，只查询必要的字段
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字段
     * @return 查询到的VO列表
     */
    public List<RmQuickQueryDataVo> queryByCondition(String queryCondition, String orderStr) {
        return queryByCondition(queryCondition, orderStr, -1, -1);
    }

    /**
     * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，只查询必要的字段
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @return 查询到的VO列表
     */
    public List<RmQuickQueryDataVo> queryByCondition(String queryCondition, String orderStr, int startIndex, int size) {
        return queryByCondition(queryCondition, orderStr, startIndex, size, false);
    }
    
    /**
     * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @param selectAllClumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<RmQuickQueryDataVo> queryByCondition(String queryCondition, String orderStr, int startIndex, int size, boolean selectAllClumn) {
        List<RmQuickQueryDataVo> lResult = getDao().queryByCondition(queryCondition, orderStr, startIndex, size, selectAllClumn);
		//填充index_value
        final Map<String, RmQuickQueryDataVo> mResult = new HashMap<String, RmQuickQueryDataVo>();
        for(RmQuickQueryDataVo vo : lResult) {
        	mResult.put(vo.getId(), vo);
        }
		RmProjectHelper.getCommonServiceInstance().doQuery("select INDEX_VALUE, QUICK_QUERY_DATA_ID from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID in(" + RmStringHelper.parseToSQLString(mResult.keySet().toArray(new String[0])) + ")", new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String indexValue = rs.getString("INDEX_VALUE");
				String quickQueryDataId = rs.getString("QUICK_QUERY_DATA_ID");
				RmQuickQueryDataVo vo = mResult.get(quickQueryDataId);
				if(vo != null) {
					if(!vo.getIndex_values().contains(indexValue)) {
						vo.addIndex_value(indexValue);
					}
				}
				return null;
			}
		});
        //RmProjectHelper.log(TABLE_LOG_TYPE_NAME, "按条件查询了多条记录,recordCount=" + lResult.size() + ", startIndex=" + startIndex + ", size=" + size + ", queryCondition=" + queryCondition + ", orderStr=" + orderStr + ", selectAllClumn=" + selectAllClumn);
        return lResult;
    }
    
    private String buildSql(RmQuickQueryVo quickQuery, String selectPhase, String queryCondition, boolean withNull) {
    	StringBuilder sql = new StringBuilder();
		Document authRule = quickQuery.getRuleCustomCode();
		String sqlAfterFrom = authRule.valueOf("/qq/@sql_after_from");
		String joinTable = authRule.valueOf("/qq/@join_table");
		String joinTableColumn = authRule.valueOf("/qq/@join_table_column");
		String join_table_column_full = authRule.valueOf("/qq/@join_table_column_full");
		String sqlAfterWhere = authRule.valueOf("/qq/@sql_after_where");
		sql.append("select ");
		sql.append(selectPhase);
		sql.append(" from ");
		sql.append(sqlAfterFrom);
		sql.append(" left join ");
		sql.append(quickQuery.getTableName_qqdata());
		sql.append(" RM_QUICK_QUERY_DATA on ");
		if(join_table_column_full != null && join_table_column_full.length() > 0) {
			sql.append(join_table_column_full);
		} else {
			sql.append(joinTable);
			sql.append(".");
			sql.append(joinTableColumn);
		}
		sql.append("=RM_QUICK_QUERY_DATA.");
		sql.append(quickQuery.getColumnName_oldResourceId());
		//where
		if(sqlAfterWhere.length() > 0 || (queryCondition != null && queryCondition.length() > 0)) {
			sql.append(" where ");
		}
		if(queryCondition != null && queryCondition.length() > 0) {
			sql.append("(");
			sql.append(queryCondition);
			if(withNull) {
				sql.append(" or RM_QUICK_QUERY_DATA.");
				sql.append(quickQuery.getColumnName_oldResourceId());
				sql.append(" is null");
			}
			sql.append(")");
		}
		if(sqlAfterWhere.length() > 0) {
			if(!sql.toString().trim().endsWith("where")) {
				sql.append(" and ");
			}
			sql.append(sqlAfterWhere);
		}
    	return sql.toString();
    }
    
    //构建查询列表的select部分
    private String buildSelectPhase(RmQuickQueryVo quickQuery) {
    	StringBuilder selectPhase = new StringBuilder();
		Document rule = quickQuery.getRuleCustomCode();
		String joinTable = rule.valueOf("/qq/@join_table");
		String joinTableColumn = rule.valueOf("/qq/@join_table_column");
		String joinTableColumnKey = rule.valueOf("/qq/@join_table_column_key");
		selectPhase.append(joinTable);
		selectPhase.append(".");
		selectPhase.append(joinTableColumnKey);
		selectPhase.append(" rm_key_column, ");
		selectPhase.append(joinTable);
		selectPhase.append(".");
		selectPhase.append(joinTableColumn);
		selectPhase.append(" rm_join_column");
		selectPhase.append(", ");
		selectPhase.append(" RM_QUICK_QUERY_DATA.ID, RM_QUICK_QUERY_DATA.QUICK_QUERY_ID, RM_QUICK_QUERY_DATA.OLD_RESOURCE_NAME, RM_QUICK_QUERY_DATA.OLD_RESOURCE_CODE, RM_QUICK_QUERY_DATA.");
		selectPhase.append(quickQuery.getColumnName_oldResourceId());
		return selectPhase.toString();
    }
    
    //得到join_table.join_table_column部分
    private String buildJoinTableColumnFull(RmQuickQueryVo quickQuery) {
    	StringBuilder joinTableColumnFull = new StringBuilder();
    	Document rule = quickQuery.getRuleCustomCode();
		String joinTable = rule.valueOf("/qq/@join_table");
		String joinTableColumn = rule.valueOf("/qq/@join_table_column");
		String join_table_column_full = rule.valueOf("/qq/@join_table_column_full");

		if(join_table_column_full != null && join_table_column_full.length() > 0) {
			joinTableColumnFull.append(join_table_column_full);
		} else {
			joinTableColumnFull.append(joinTable);
			joinTableColumnFull.append(".");
			joinTableColumnFull.append(joinTableColumn);
		}
		return joinTableColumnFull.toString();
    }
    
    /**
     * 通过查询条件和参照索引对象获得总数
     * 
     * @param queryCondition
     * @param QuickQuery
     * @return
     */
    public int getRecordCount(String queryCondition, RmQuickQueryVo quickQuery) {
    	String sql = buildSql(quickQuery, "count(*)", queryCondition, true);
		return RmProjectHelper.getCommonServiceInstance().doQueryForInt(sql);
    }
    
    /**
     * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符，根据selectAllClumn判断是否查询所有字段
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @param selectAllClumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @param QuickQuery 参照索引对象
     * @return 查询到的VO列表
     */
    public List<RmQuickQueryDataVo> queryByCondition(String queryCondition, String orderStr, int startIndex, int size, boolean selectAllClumn, final RmQuickQueryVo quickQuery) {
    	String sql = buildSql(quickQuery, buildSelectPhase(quickQuery), queryCondition, true);
    	final Map<String, RmQuickQueryDataVo> mResult = new HashMap<String, RmQuickQueryDataVo>();
    	final List<RmQuickQueryDataVo> result = new ArrayList<RmQuickQueryDataVo>();
		RmProjectHelper.getCommonServiceInstance().doQueryStartIndex(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				RmQuickQueryDataVo vo = new RmQuickQueryDataVo();
				RmPopulateHelper.populate(vo, rs);
				if(vo.getOld_resource_id_name() == null) {
					vo.setOld_resource_id_name(rs.getString("rm_key_column"));
				}
		    	if("1".equals(quickQuery.getOld_resource_id_type())) {
					if(vo.getOld_resource_id_char() == null) {
						vo.setOld_resource_id_char(rs.getString("rm_join_column"));
					}
		    	} else {
					if(vo.getOld_resource_id_number() == null) {
						vo.setOld_resource_id_number(rs.getString("rm_join_column"));
					}
		    	}

				result.add(vo);
				mResult.put(vo.getId(), vo);
				return null;
			}
		}, startIndex, size);
		
		//填充index_value
		RmProjectHelper.getCommonServiceInstance().doQuery("select INDEX_VALUE, QUICK_QUERY_DATA_ID from RM_QUICK_QUERY_DATA_INDEX where QUICK_QUERY_DATA_ID in(" + RmStringHelper.parseToSQLString(mResult.keySet().toArray(new String[0])) + ")", new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				String indexValue = rs.getString("INDEX_VALUE");
				String quickQueryDataId = rs.getString("QUICK_QUERY_DATA_ID");
				RmQuickQueryDataVo vo = mResult.get(quickQueryDataId);
				if(vo != null) {
					if(!vo.getIndex_values().contains(indexValue)) {
						vo.addIndex_value(indexValue);
					}
				}
				return null;
			}
		});
		return result;
    }

	/**
	 * 自动初始化索引数据
	 * 
	 * @param QuickQuery
	 * @param ids
	 * @return
	 */
	public int executeInitData(final RmQuickQueryVo quickQuery, String[] ids) {
		if(ids == null || ids.length == 0) {
			return 0;
		}
    	String sql = buildSql(quickQuery, buildSelectPhase(quickQuery), buildJoinTableColumnFull(quickQuery) + " in(" + RmStringHelper.parseToSQLString(ids) + ")", false);
    	final List<RmQuickQueryDataVo> lvo = new ArrayList<RmQuickQueryDataVo>();
		RmProjectHelper.getCommonServiceInstance().doQuery(sql, new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				RmQuickQueryDataVo vo = new RmQuickQueryDataVo();
				RmPopulateHelper.populate(vo, rs);
				if(vo.getOld_resource_id_name() == null) {
					vo.setOld_resource_id_name(rs.getString("rm_key_column"));
				}
		    	if("1".equals(quickQuery.getOld_resource_id_type())) {
					if(vo.getOld_resource_id_char() == null) {
						vo.setOld_resource_id_char(rs.getString("rm_join_column"));
					}
		    	} else {
					if(vo.getOld_resource_id_number() == null) {
						vo.setOld_resource_id_number(rs.getString("rm_join_column"));
					}
		    	}

				lvo.add(vo);
				return null;
			}
		});
		List<RmQuickQueryDataVo> lInsertData = new ArrayList<RmQuickQueryDataVo>();
		for(RmQuickQueryDataVo vo : lvo) {
			if(vo.getQuick_query_id() != null && vo.getQuick_query_id().length() > 0) {
				continue;
			}
			vo.setQuick_query_id(quickQuery.getId());
			vo.setOld_resource_name(vo.getOld_resource_id_name());
			String firstSpell = RmStringHelper.getFirstSpellCollection(vo.getOld_resource_id_name());
			if(firstSpell != null) {
				String finalFirstSpell = firstSpell.toLowerCase().replaceAll("[_、-]", "");
				if(finalFirstSpell.length() > 0) {
					vo.addIndex_value(finalFirstSpell);
				}
			}
			if(vo.getIndex_values().size() == 0){
				vo.addIndex_value(vo.getOld_resource_id_name().toLowerCase());
			}
			RmVoHelper.markCreateStamp(RmRequestMonitor.getCurrentThreadRequest(), vo);
			lInsertData.add(vo);
		}
		return insert(lInsertData.toArray(new RmQuickQueryDataVo[0])).length;
	}
}