package com.phiz.common.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {

	public static void append(String fileName, String content) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File(fileName);
			if(!f.exists()){
			    f.createNewFile();
            }
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			append("e://time.txt", i + "=====我");

		}

	}

    public static void writeFile(String soapData, String fileName) {
		try {
			FileWriter fr = new FileWriter("f:/" + fileName);
			fr.write(soapData);
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
