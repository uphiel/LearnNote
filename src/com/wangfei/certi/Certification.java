package com.wangfei.certi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Enumeration;

public class Certification {
	public static KeyStore loadKetStore(String pfxFilepath, String pfxPassword)
			   throws Exception {
			  KeyStore keyStore = null;
			  File fPkcs12 = null;
			  if (pfxFilepath != null) {
			   fPkcs12 = new File(pfxFilepath);
			  }
			  FileInputStream fis = new FileInputStream(fPkcs12);
			  try {
			   keyStore = KeyStore.getInstance("PKCS12");
			  } catch (KeyStoreException ex) {
			   throw new Exception("不能正确解释pfx文件! ");
			  }
			  try {
			   keyStore.load(fis, pfxPassword.toCharArray());
			  } catch (CertificateException ex) {
			   throw new Exception(" 证书格式问题! ");
			  } catch (NoSuchAlgorithmException ex) {
			   throw new Exception(" 算法不支持! ");
			  } catch (FileNotFoundException ex) {
			   throw new Exception(" pfx文件没找到 ");
			  } catch (IOException ex) {
			   throw new Exception(" 读取pfx有误! ");
			  }
			  return keyStore;
			 }
	
	public static String getKeyAlias(KeyStore keyStore) {
		  String keyAlias = "";
		  try {
		   Enumeration enums = keyStore.aliases();
		   while (enums.hasMoreElements()) {
		    keyAlias = (String) enums.nextElement();
		   }
		  } catch (KeyStoreException e) {
		   e.printStackTrace();
		  }
		  return keyAlias;
		 }
	

}
