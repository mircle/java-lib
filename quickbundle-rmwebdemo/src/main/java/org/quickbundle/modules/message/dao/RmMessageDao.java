package org.quickbundle.modules.message.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.modules.message.IRmMessageConstants;
import org.quickbundle.modules.message.vo.RmMessageVo;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.project.test.RmWebTestCase;
import org.quickbundle.third.mybatis.RmSqlSessionDaoSupport;
import org.quickbundle.tools.context.RmBeanHelper;
import org.springframework.stereotype.Component;

@Component
public class RmMessageDao extends RmSqlSessionDaoSupport implements IRmMessageConstants {

    /**
     * 插入单条记录，用id作主键，把null全替换为""
     * 
     * @param vo 用于添加的VO对象
     * @return 若添加成功，返回新生成的id
     */
    public String insert(RmMessageVo vo) {
    	return null;
    }

    /**
     * 批更新插入多条记录，用id作主键，把null全替换为""
     * 
     * @param vos 添加的VO对象数组
     * @return 若添加成功，返回新生成的id数组
     */
    public String[] insert(RmMessageVo[] vos) {
    	return null;
    }
    
    /**
     * 删除单条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id) {
    	return 0;
    }

    /**
     * 删除多条记录
     * 
     * @param id 用于删除的记录的id
     * @return 成功删除的记录数
     */
    public int delete(String id[]) {
    	return 0;
    }

    /**
     * 根据Id进行查询
     * 
     * @param id 用于查找的id
     * @return 查询到的VO对象
     */
    public RmMessageVo find(String id) {
    	return null;
    }

    /**
     * 更新单条记录
     * 
     * @param vo 用于更新的VO对象
     * @return 成功更新的记录数
     */
    public int update(RmMessageVo vo) {
    	return 0;
    }

    /**
     * 批量更新修改多条记录
     * 
     * @param vos 添加的VO对象数组
     * @return 成功更新的记录数组
     */
	public int[] update(RmMessageVo[] vos) {
		return null;
	}

    /**
     * 查询总记录数，带查询条件
     * 
     * @param queryCondition 查询条件
     * @return 总记录数
     */
    public int getRecordCount(String queryCondition) {
    	return 0;
    }
    
    /**
     * 功能: 通过查询条件获得所有的VO对象列表，带翻页，带排序字符
     *
     * @param queryCondition 查询条件, queryCondition等于null或""时查询全部
     * @param orderStr 排序字符
     * @param startIndex 开始位置(第一条是1，第二条是2...)
     * @param size 查询多少条记录(size小于等于0时,忽略翻页查询全部)
     * @param selectAllClumn 是否查询所有列，即 SELECT * FROM ...(适用于导出)
     * @return 查询到的VO列表
     */
    public List<RmMessageVo> list(String queryCondition, String orderStr, int startIndex, int size, boolean allClumn) {
		return getSqlSession().selectList("org.quickbundle.modules.message.dao.RmMessageDao.list", queryCondition, new RowBounds(startIndex-1, size));
	}
    
    public static void main(String[] args) {
    	RmWebTestCase.init();
    	RmBeanFactory.getBeanFactory().getBean(org.quickbundle.modules.message.dao.RmMessageDao.class).list("", null, 1, 5, true);
	}
}
