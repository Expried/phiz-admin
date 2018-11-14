package com.phiz.common.utils;

import java.util.UUID;

public class UuidUtil {

	public static String getUuid(){
		String uuidStr = UUID.randomUUID().toString();
		return uuidStr.substring(0, 36);
	}
	public static void main(String[] args) {
		String uuid = getUuid();
		System.out.println(uuid);
	}
}
