package com.phiz.common.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.config.MybatisConfigurer</b>
 * <b>Description:Mybatis 配置</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月30日 下午4:11:10</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月30日 下午4:11:10   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Configuration
public class MybatisConfigurer {

	private static final Logger LOG = LoggerFactory.getLogger(MybatisConfigurer.class);
	
	
	@Resource
    private DataSource dataSource;

	@Value("${pagehelper.reasonable}")
	private Boolean reasonable;
	
	@Value("${pagehelper.methodsarguments}")
	private Boolean supportMethodsArguments;
	
	@Value("${pagehelper.returnPageInfo}")
	private String returnPageInfo;

	@Value("${pagehelper.params}")
	private String params;
	
	@Value("${mybatis.mapperLocations}")
	private String mapperLocations;
	
	@Value("${mybatis.type-aliases-package}")
	private String aliases;
	
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
    	SqlSessionFactoryBean bean = new PackagesSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(aliases);
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", reasonable.toString());
        properties.setProperty("supportMethodsArguments", supportMethodsArguments.toString());
        properties.setProperty("returnPageInfo",returnPageInfo);
        properties.setProperty("params",params);
        pageHelper.setProperties(properties);
        PageInterceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        //添加插件
        bean.setPlugins(new Interceptor[]{interceptor});
        
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        org.springframework.core.io.Resource [] temp =  resolver.getResources(mapperLocations);
        LOG.info("load resource size:{}",temp.length);
        for (org.springframework.core.io.Resource resource : temp) {
//        	String [] temps = resource.getURL().toString().split("/");
//        	System.out.println(this.getClass().getResourceAsStream("/mapper/com/hz/fare/business/base/dao/mapper/"+temps[temps.length-1]));
//        	java.io.InputStream inputStream =   this.getClass().getClassLoader().getResourceAsStream(temps[temps.length-1]);
//        	this.getClass().getClassLoader().getResourceAsStream(name)
//            ClassPathResource _resource = new ClassPathResource("application.yml");
        	LOG.info("load mapper:{}",resource.getURL());
		}
        bean.setMapperLocations(resolver.getResources(mapperLocations));
        return bean.getObject();
    }

    
    
    
}
