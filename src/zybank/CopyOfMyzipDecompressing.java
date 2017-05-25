package zybank;

import java.io.*;
import java.util.zip.*;

/**
 * ����ʵ����ZIPѹ��������Ϊ2���� �� ѹ����compression�����ѹ��decompression��
 * <p>
 * ���¹��ܰ������˶�̬���ݹ��JAVA���ļ��������ԶԵ����ļ������⼶���ļ��н���ѹ���ͽ�ѹ�� ���ڴ������Զ���Դ����·����Ŀ�����·����
 * <p>
 * �ڱ��δ����У�ʵ�ֵ��ǽ�ѹ���֣�ѹ�����ּ�������compression���֡�
 * 
 * @author HAN
 * 
 */
public class CopyOfMyzipDecompressing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.currentTimeMillis();
		try {
			ZipInputStream Zin = new ZipInputStream(
					new FileInputStream("C:\\Users\\HAN\\Desktop\\stock\\SpectreCompressed.zip"));// ����Դzip·��
			BufferedInputStream Bin = new BufferedInputStream(Zin);
			String Parent = "C:\\Users\\HAN\\Desktop"; // ���·�����ļ���Ŀ¼��
			File Fout = null;
			ZipEntry entry;
			try {
				while ((entry = Zin.getNextEntry()) != null && !entry.isDirectory()) {
					Fout = new File(Parent, entry.getName());
					if (!Fout.exists()) {
						(new File(Fout.getParent())).mkdirs();
					}
					FileOutputStream out = new FileOutputStream(Fout);
					BufferedOutputStream Bout = new BufferedOutputStream(out);
					int b;
					while ((b = Bin.read()) != -1) {
						Bout.write(b);
					}
					Bout.close();
					out.close();
					System.out.println(Fout + "��ѹ�ɹ�");
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("�ķ�ʱ�䣺 " + (endTime - startTime) + " ms");
	}

}
