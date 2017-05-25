package com.wangfei.test;

import com.wangfei.tools.ZipUtil;

import java.io.File;
import java.util.Scanner;

;

/**
 * 压缩文件
 *
 * @param filePath 待压缩的文件路径
 * @return 压缩后的文件
 */
public class ZipTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input the file path:");
		String filePath = scan.nextLine();
		File zipFile = ZipUtil.zip(filePath);
		scan.close();
	}
}
