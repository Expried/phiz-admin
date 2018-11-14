package com.phiz.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.xxl.job.core.executor.XxlJobExecutor;


/**
 * <pre>
 * <b>.</b>

 * <b>Project:hzfare-core</b>
 * <b>ClassName:com.hz.fare.common.config.JobConfig</b>
 * <b>Description:任务调度配置</b> 
 *   ----------------------------------------------------------------------
 * <b>Author:</b> <b>淳峰    1569812004@qq.com</b>
 * <b>Date:</b> <b>2018年5月31日 下午3:36:10</b>
 *   ----------------------------------------------------------------------
 * <b>Changelog:</b>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年5月31日 下午3:36:10   <b>淳峰    1569812004@qq.com</b>
 *         new file.
 * </pre>
 */
@Component
@ComponentScan(basePackages = "com.*task")
public class JobConfig {
	
	private Logger LOG = LoggerFactory.getLogger("job");

    @Value("${xxl.job.admin.addresses}")
    private String addresses;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logpath;

    @Value("${xxl.job.accessToken}")
    private String accessToken;
    
    @Value("${xxl.job.hold}")
    private boolean hold;
    
    

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobExecutor xxlJobExecutor() {
    	if (hold) {
    		initLog();
            XxlJobExecutor xxlJobExecutor = new XxlJobExecutor();
            xxlJobExecutor.setIp(ip);
            xxlJobExecutor.setPort(port);
            xxlJobExecutor.setAppName(appname);
            xxlJobExecutor.setAdminAddresses(addresses);
            xxlJobExecutor.setLogPath(logpath);
            xxlJobExecutor.setAccessToken(accessToken);
            return xxlJobExecutor;
		}
    	return null;
    }
    
    private  void initLog() {
      	 LOG.info("------------------------------------------------------------------");
      	 LOG.info(" Job Config Init ........");
      	 LOG.info(" addresses:{}",this.addresses);
      	 LOG.info(" appname:{}",this.appname);
      	 LOG.info(" ip:{}",this.ip);
    	 LOG.info(" port:{}",this.port);
    	 LOG.info(" logpath:{}",this.logpath);
    	 LOG.info(" accessToken:{}",this.accessToken);
      	 LOG.info("------------------------------------------------------------------");
      }
}
