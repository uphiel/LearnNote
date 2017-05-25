package com.wangfei.test;

import com.wangfei.tools.file.ReadFromFile;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] arr = {"18675822504","18576063160"};
		System.out.println(Arrays.toString(arr));
		String arrayStr = "[\"";
		for(String str : arr){
			arrayStr = arrayStr + str + "\",\"";
		}
		String newStr = arrayStr.substring(0, arrayStr.length()-2) + "]";
		System.out.println(newStr);

		ReadFromFile.readFileBytes("src/com/wangfei/test/luohe.txt");
	}

}
