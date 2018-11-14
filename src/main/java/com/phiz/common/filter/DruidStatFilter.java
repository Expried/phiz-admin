/**
 * <pre>
 * <b>Project:hzfare</b>
 * <b>FiledName:com.hz.common.filter.DruidStatFilter.java</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午2:39:31</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午2:39:31   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
package com.phiz.common.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare</b>
 * <b>ClassName:com.hz.common.filter.DruidStatFilter</b>
 * <b>Description:</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月29日 下午2:39:31</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午2:39:31   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",    
initParams={    
    @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源    
}    
) 
public class DruidStatFilter extends WebStatFilter {

}
