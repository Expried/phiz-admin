/**
 * <pre>
 * <b>Project:hzfare-core</b>
 * <b>FiledName:com.hz.fare.common.base.BaseEntity.java</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午9:05:42</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午9:05:42   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
package com.phiz.common.base;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.base.BaseEntity</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 上午9:05:42</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 上午9:05:42   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 
	 * 正常
	*/ 
	public static final short STATUS_NORMAL = 0;
	
	/** 
	 * 删除
	*/ 
	public static final short STATUS_DEL = 1;	
	
	/** 
	 * 停用
	*/ 
	public static final short STATUS_DISABLE = 2;
	
	/** 
	 * 主键编号
	*/ 
	private Long id;
	
    /** 
     * 状态
    */ 
    private Short status;

    /** 
     * 创建者
    */ 
    private String creator;

    /** 
     * 创建时间
    */ 
    private Date createTime;

    /** 
     * 修改时间
    */ 
    private Date modifyTime;

    /** 
     * 修改者
    */ 
    private String modifier;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
