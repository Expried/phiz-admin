package com.phiz.common.utils;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class GenerateIdUtil {


	/**
	 * 时间格式生成序列
	 * 
	 * @return String
	 */
	public static synchronized String generateSequenceNo() {
		FieldPosition HELPER_POSITION = new FieldPosition(0);
		Format dateFormat = new SimpleDateFormat("YYMMddHHmmssSSS");
		NumberFormat numberFormat = new DecimalFormat("0000");
		
		Calendar rightNow = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();
		dateFormat.format(rightNow.getTime(), sb, HELPER_POSITION);
		numberFormat.format(new Random().nextInt(9999), sb, HELPER_POSITION);

		return sb.toString();
	}
	public static void main(String[] args) {
		String id = generateSequenceNo();
		System.out.println(id);
	}
}
