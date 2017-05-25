package com.wangfei.test;

import java.util.Scanner;

public class Test13 {
	public static void main(String[] args){
		//Scanner���ռ�������Ĺ���
		Scanner scan = new Scanner(System.in);
		
		System.out.println("�����뵥�ۣ�");
		Double price = scan.nextDouble();
		
		System.out.println("������������");
		int numbers = scan.nextInt();
		
		System.out.println("�����븶���");
		Double amt = scan.nextDouble();
		//Scanner��������Ҫ�ص�
		scan.close();
		
		//��Ʒ��ֵ������
		Double amtCorrect = price * numbers;
		//�������
		Double changes = amt - amtCorrect;
		System.out.println("Ӧ�ս��Ϊ����" + amtCorrect + "������Ϊ����" + changes);
	}
}
