/**
 * <pre>
 * <b>Project:hzfare-core</b>
 * <b>FiledName:com.hz.fare.common.base.BaseServiceImpl.java</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午10:06:40</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午10:06:40   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
package com.phiz.common.base;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phiz.common.exception.HzBaseException;
import com.phiz.common.utils.IdGen;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.base.BaseServiceImpl</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b>
 * <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> 
 * <b>2018年5月30日 上午10:06:40</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午10:06:40   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

	
    protected BaseDao<T> dao;

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseServiceImpl</b>
	 * <b>@param dao</b>
	 * <b>@return:</b>
	  
	 * <b>@Description:</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午10:08:19</b>    
	 *
	 */
	public BaseServiceImpl(BaseDao<T> dao) {
		this.dao = dao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Throwable.class)
	public int deleteByPrimaryKey(long id) {
		return dao.deleteByPrimaryKey(id);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Throwable.class)
	public int deleteByIds(String[] ids) {
		return dao.deleteByIds(ids);
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Throwable.class)
	public int insert(T record) throws HzBaseException {
		genId(record);
		record.setCreateTime(new Date());
		return dao.insert(record);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Throwable.class)
	public int insertSelective(T record) throws HzBaseException {
		genId(record);
		record.setCreateTime(new Date());
		return dao.insertSelective(record);
	}

	@Override
	public T selectByPrimaryKey(Long id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Throwable.class)
	public int updateByPrimaryKeySelective(T record) throws HzBaseException {
		record.setModifyTime(new Date());
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Throwable.class)
	public int updateByPrimaryKey(T record) {
		record.setModifyTime(new Date());
		return dao.updateByPrimaryKey(record);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}
	@Override
	public PageInfo<T> findPage(int pageSize, int pageNum, T t) {
		PageHelper.startPage(pageNum, pageSize);
		List<T> data = findList(t);
		PageInfo<T> page = new PageInfo<>(data);
		return page;
	}

	@Override
	public List<T> findList(T t) {
		return dao.findList(t);
	}

	/**
	 * 
	 * <b>@Title:com.hz.fare.common.base .BaseServiceImpl</b>
	 * <b>@param t
	 * <b>@param workerId  机器实例的生产者编号
	 * <b>@param datacenterId 数据中心编号</b>
	 * <b>@Description: 生成主键</b>
	 * <b>@Author:淳峰    1569812004@qq.com </b> 
	 * <b>@Time:2018年5月30日上午10:38:42</b>    
	 *
	 */
	protected void genId(T t) {
		if (t.getId() == null) {
		    IdGen flakeIdFactory  = new IdGen(1,1);
			long id = flakeIdFactory.nextId();
			t.setId(id);
		}
	}




	@Override
	public boolean deleteIds(String[] ids) {
		for (String id : ids) {
			long _id = Long.parseLong(id);
			deleteByPrimaryKey(_id);
		}
		return true;
	}

	@Override
	public boolean insertBatch(List<T> datas) {
		for (T data : datas) {
			genId(data);
			data.setCreateTime(new Date());
			data.setModifyTime(new Date());
		}
		int totl = dao.insertBatch(datas);
		return totl > 0;
	}

	

	
	
}
