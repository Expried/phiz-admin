package com.phiz.common.base;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.phiz.common.exception.HzBaseException;

/**
 * <pre>
 * <b>.</br>

 * <b>Project:hzfare-core</br>
 * <b>ClassName:com.hz.fare.common.base.BaseService</br>
 * <b>Description:</br> 
 *   ----------------------------------------------------------------------
 * <b>Author:</br> <b>淳峰    1569812004@qq.com</br>
 * <b>Date:</br> <b>2018年5月30日 上午9:05:10</br>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</br>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午9:05:10   <b>淳峰    1569812004@qq.com</br>
 *         new file.
 * </pre>
 */
public interface BaseService<T extends BaseEntity> {
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param id 主键参数
	 * <b>@return: 受影响的行数</br>
	 * <b>@Description:通过主键删除数据</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:23:59</br>    
	 *
	 */
	int deleteByPrimaryKey(long id);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int deleteByIds(String [] ids);

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param record
	 * <b>@return:受影响的行数</br>
	 * <b>@Description:全量插入数据库</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:24:34</br>    
	 * @throws HzBaseException 
	 *
	 */
	int insert(T record) throws HzBaseException;

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param record
	 * <b>@return:受影响的行数</br>
	 * <b>@Description:选择性插入数据（属性不为空的）</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:25:13</br>    
	 * @throws Exception 
	 * @throws HzBaseException 
	 *
	 */
	int insertSelective(T record) throws HzBaseException;

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param id 主键编号
	 * <b>@return:</br>
	 * <b>@Description: 通过主键编号查询数据</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:25:53</br>    
	 *
	 */
	T selectByPrimaryKey(Long id);

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param record
	 * <b>@return:</br>
	 * <b>@Description:通过主键选择性修改数据(不为空的属性)</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:26:20</br>    
	 * @throws HzBaseException 
	 *
	 */
	int updateByPrimaryKeySelective(T record) throws HzBaseException;

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param record
	 * <b>@return:通过主键全量更新数据 </br>
	 * <b>@Description:</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:27:23</br>    
	 *
	 */
	int updateByPrimaryKey(T record);
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@return</br>
	 * <b>@return:</br>
	 * <b>@Description:查询所有记录</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:27:51</br>    
	 *
	 */
	List<T> findAll();
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param pageSize  每页的数量
	 * <b>@param pageNum  当前页
	 * <b>@param t  查询参数
	 * <b>@return:分页对象</br>
	 * <b>@Description: 分页查询数据</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:28:06</br>    
	 *
	 */
	PageInfo<T> findPage(int pageSize,int pageNum,T t);
	
	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseService</br>
	 * <b>@param t
	 * <b>@return:</br>
	 * <b>@Description:查询数据列表</br>
	 * <b>@Author:淳峰    1569812004@qq.com </br> 
	 * <b>@Time:2018年5月30日上午10:29:19</br>    
	 *
	 */
	List<T> findList(T t);
	
	
	/**
	 * 
	 * <br>@param ids
	 * <br>@return:</br>
	 * <br>@Description: 多行删除</br>
	 * <br>@Author:淳峰    1569812004@qq.com </br> 
	 * <br>@Time:2018年8月8日上午9:09:52</br>    
	 *
	 */
	boolean deleteIds(String [] ids);

	/**
	 * 
	 * <br>@param ids
	 * <br>@return:</br>
	 * <br>@Description: 批量插入</br>
	 * <br>@Author:淳峰    1569812004@qq.com </br> 
	 * <br>@Time:2018年8月8日上午9:09:52</br>    
	 *
	 */
	boolean insertBatch(List<T> t);
}
