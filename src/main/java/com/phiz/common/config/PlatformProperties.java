package com.phiz.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix="platform",ignoreUnknownFields = false)
@PropertySource("classpath:/platform.properties")
@Component
public class PlatformProperties {
	
	private String intlTB_dd_url;
	private String intlTB_dd_appkey;
	private String intlTB_dd_secret;
	private String intlTB_dd_sessionKey;
	private String intlHZTB_dd_appkey;
	private String intlHZTB_dd_secret;
	private String intlHZTB_dd_sessionKey;
	
	public String getIntlTB_dd_url() {
		return intlTB_dd_url;
	}
	public void setIntlTB_dd_url(String intlTB_dd_url) {
		this.intlTB_dd_url = intlTB_dd_url;
	}
	public String getIntlTB_dd_appkey() {
		return intlTB_dd_appkey;
	}
	public void setIntlTB_dd_appkey(String intlTB_dd_appkey) {
		this.intlTB_dd_appkey = intlTB_dd_appkey;
	}
	public String getIntlTB_dd_secret() {
		return intlTB_dd_secret;
	}
	public void setIntlTB_dd_secret(String intlTB_dd_secret) {
		this.intlTB_dd_secret = intlTB_dd_secret;
	}
	public String getIntlTB_dd_sessionKey() {
		return intlTB_dd_sessionKey;
	}
	public void setIntlTB_dd_sessionKey(String intlTB_dd_sessionKey) {
		this.intlTB_dd_sessionKey = intlTB_dd_sessionKey;
	}
	public String getIntlHZTB_dd_appkey() {
		return intlHZTB_dd_appkey;
	}
	public void setIntlHZTB_dd_appkey(String intlHZTB_dd_appkey) {
		this.intlHZTB_dd_appkey = intlHZTB_dd_appkey;
	}
	public String getIntlHZTB_dd_secret() {
		return intlHZTB_dd_secret;
	}
	public void setIntlHZTB_dd_secret(String intlHZTB_dd_secret) {
		this.intlHZTB_dd_secret = intlHZTB_dd_secret;
	}
	public String getIntlHZTB_dd_sessionKey() {
		return intlHZTB_dd_sessionKey;
	}
	public void setIntlHZTB_dd_sessionKey(String intlHZTB_dd_sessionKey) {
		this.intlHZTB_dd_sessionKey = intlHZTB_dd_sessionKey;
	}
	
		
}
