package com.wangfei.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
	private ZipUtil() {

    }

    /**
     * ѹ���ļ�
     *
     * @param filePath ��ѹ�����ļ�·��
     * @return ѹ������ļ�
     */
    public static File zip(String filePath) {
        File target = null;
        File source = new File(filePath);
        if (source.exists()) {
            String sourceName = source.getName();
            String zipName = sourceName.contains(".") ? sourceName.substring(0, sourceName.indexOf(".")) : sourceName;
            System.out.println("ѹ�������ƣ�" + zipName);
            target = new File(source.getParent(), zipName + ".rar");
            if (target.exists()) {
                boolean delete = target.delete();//ɾ���ɵ�ѹ����
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(target);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));

                addEntry(null, source, zos);  //��Ӷ�Ӧ���ļ�Entry
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtil.closeQuietly(zos, fos);
            }
        }
        return target;
    }

    /**
     * ɨ������ļ�Entry
     *
     * @param base   ��·��
     * @param source Դ�ļ�
     * @param zos    Zip�ļ������
     * @throws IOException
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException {
        String entry = (base != null) ? base + source.getName() : source.getName(); //��Ŀ¼�ּ������磺aaa/bbb.txt
        System.out.println("entry:" + entry);
        if (source.isDirectory()) {
            File[] files = source.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    addEntry(entry + "/", file, zos);// �ݹ��г�Ŀ¼�µ������ļ�������ļ� Entry
                }
            }
        } else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read;
                zos.putNextEntry(new ZipEntry(entry)); //���ֻ���뽫�ļ����µ������ļ�ѹ����������Ҫѹ����Ŀ¼,Լ���ļ������� entry.substring(length)
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            } finally {
                IOUtil.closeQuietly(bis, fis);
            }
        }
    }

    /**
     * ��ѹ�ļ�
     *
     * @param filePath ѹ���ļ�·��
     */
    public static void unzip(String filePath) {
        File source = new File(filePath);
        if (source.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(source));
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                    File target = new File(source.getParent(), entry.getName());
                    if (!target.getParentFile().exists()) {  
                        target.getParentFile().mkdirs();
                    }
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                IOUtil.closeQuietly(zis, bos);
            }
        }
    }
}
