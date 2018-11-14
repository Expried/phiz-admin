package com.phiz.common.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * <pre>
 * <b>.</b>

 * <b>Project:{}hzfare</b>
 * <b>ClassName:{}com.hz.common.config.DruidConfig</b>
 * <b>Description:{}数据源配置</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:{}</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:{}</b> <b>2018年5月29日 下午2:{}34:{}36</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:{}</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月29日 下午2:{}34:{}36   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Configuration
public class DruidConfigurer {
	
	private static final Logger LOG = LoggerFactory.getLogger("druidconfig");
    
    @Value("${spring.datasource.url}")    
    private String dbUrl;    
        
    @Value("${spring.datasource.username}")    
    private String username;    
        
    @Value("${spring.datasource.password}")    
    private String password;    
        
    @Value("${spring.datasource.driverClassName}")    
    private String driverClassName;    
        
    @Value("${spring.datasource.initialSize}")    
    private int initialSize;    
        
    @Value("${spring.datasource.minIdle}")    
    private int minIdle;    
        
    @Value("${spring.datasource.maxActive}")    
    private int maxActive;    
        
    @Value("${spring.datasource.maxWait}")    
    private int maxWait;    
        
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")    
    private int timeBetweenEvictionRunsMillis;    
        
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")    
    private int minEvictableIdleTimeMillis;    
        
    @Value("${spring.datasource.validationQuery}")    
    private String validationQuery;    
        
    @Value("${spring.datasource.testWhileIdle}")    
    private boolean testWhileIdle;    
        
    @Value("${spring.datasource.testOnBorrow}")    
    private boolean testOnBorrow;    
        
    @Value("${spring.datasource.testOnReturn}")    
    private boolean testOnReturn;    
        
    @Value("${spring.datasource.poolPreparedStatements}")    
    private boolean poolPreparedStatements;    
        
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")    
    private int maxPoolPreparedStatementPerConnectionSize;    
        
    @Value("${spring.datasource.filters}")    
    private String filters;    
        
    @Value("{spring.datasource.connectionProperties}")    
    private String connectionProperties;    
        
    @Value("${spring.datasource.useGlobalDataSourceStat}")  
    private boolean useGlobalDataSourceStat;
    
    @Bean     //声明其为Bean实例    
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource    
    public DataSource dataSource(){    
    	 initLog();
    	 DruidDataSource datasource = new DruidDataSource();  
         datasource.setUrl(this.dbUrl);  
         datasource.setUsername(username);  
         datasource.setPassword(password);  
         datasource.setDriverClassName(driverClassName);  
         datasource.setInitialSize(initialSize);  
         datasource.setMinIdle(minIdle);  
         datasource.setMaxActive(maxActive);  
         datasource.setMaxWait(maxWait);  
         datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);  
         datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);  
         datasource.setValidationQuery(validationQuery);  
         datasource.setTestWhileIdle(testWhileIdle);  
         datasource.setTestOnBorrow(testOnBorrow);  
         datasource.setTestOnReturn(testOnReturn);  
         datasource.setPoolPreparedStatements(poolPreparedStatements);  
         datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);  
         datasource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);  
         try {  
             datasource.setFilters(filters);  
         } catch (SQLException e) {  
        	 LOG.error("druid configuration initialization filter:{} ",e);
         }  
         datasource.setConnectionProperties(connectionProperties);  
         return datasource;   
    }   
    
    @Bean
    @Primary
    public DruidStatInterceptor statInterceptor() {
    	DruidStatInterceptor interceptor = new DruidStatInterceptor();
    	return interceptor;
    }
    
    @Bean
    @Primary
    public JdkRegexpMethodPointcut regexPointcut() {
    	JdkRegexpMethodPointcut point = new JdkRegexpMethodPointcut();
    	point.setPatterns("com.hz.mapper.*");
    	return point;
    }
    
    private  void initLog() {
    	 LOG.info("------------------------------------------------------------------");
    	 LOG.info(" DataSource Init ........");
    	 LOG.info(" driverClassName:{}",this.driverClassName);
    	 LOG.info(" dbUrl:{}",this.dbUrl);
    	 LOG.info(" username:{}",this.username);
    	 LOG.info(" password:{}",this.password);
    	 LOG.info(" initialSize:{}",this.initialSize);
    	 LOG.info(" minIdle:{}",this.minIdle);
    	 LOG.info(" maxActive:{}",this.maxActive);
    	 LOG.info(" maxWait:{}",this.maxWait);
    	 LOG.info(" timeBetweenEvictionRunsMillis:{}",this.timeBetweenEvictionRunsMillis);
    	 LOG.info(" minEvictableIdleTimeMillis:{}",this.minEvictableIdleTimeMillis);
    	 LOG.info(" validationQuery:{}",this.validationQuery);
    	 LOG.info(" testWhileIdle:{}",this.testWhileIdle);
    	 LOG.info(" testOnBorrow:{}",this.testOnBorrow);
    	 LOG.info(" testOnReturn:{}",this.testOnReturn);
    	 LOG.info(" poolPreparedStatements:{}",this.poolPreparedStatements);
    	 LOG.info(" maxPoolPreparedStatementPerConnectionSize:{}",this.maxPoolPreparedStatementPerConnectionSize);
    	 LOG.info(" filters:{}",this.filters);
    	 LOG.info(" connectionProperties:{}",this.connectionProperties);
    	 LOG.info(" useGlobalDataSourceStat:{}",this.useGlobalDataSourceStat);
    	 LOG.info("------------------------------------------------------------------");
    }
}
