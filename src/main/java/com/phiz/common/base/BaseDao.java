package com.phiz.common.base;

import java.util.List;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.base.BaseDao</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午10:03:50</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午10:03:50   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public interface BaseDao<T> {
	
    int deleteByPrimaryKey(long id);
    
    int deleteByIds(String[] ids);
    
    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
    
    List<T> findAll();
   
    List<T> findList(T t);
    
    int insertBatch(List<T> list);
   
}
