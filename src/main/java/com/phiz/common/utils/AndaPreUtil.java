package com.phiz.common.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AndaPreUtil {
    // test
//	private static final String USERNAME = "apipartner";
//	private static final String PASSWORD = "^3HgmzGk=!";
//	private static final String ENDPOINT = "http://apidev.lotustours.hk";

//    // 正式接口
//    private static final String USERNAME = "szhuazheng";
//    private static final String PASSWORD = "pwd@27!40?44*";
    private static final String ENDPOINT = "http://airapi2.lotus-tours.com.hk";

    private static final String USERNAME = "szhuazheng";
    private static final String PASSWORD = "pwd@27!40?44*";
    //private static final String ENDPOINT = "http://119.23.125.122:8072/anda";

	public String getUrl(String methodName) {
		return ENDPOINT + "/TransactionalWS/CrsTransactional.svc/rest/" + methodName + "/" + USERNAME + "/"+ getAuthentication();
	}

	public String getAuthentication() {

		long timeInSeconds = System.currentTimeMillis() / 1000;
		String res = Md5Util.encode(USERNAME + PASSWORD + timeInSeconds);
		return res;
	}

	public static void main(String[] args) {
		System.out.println(new Date(System.currentTimeMillis()));
	}
}
