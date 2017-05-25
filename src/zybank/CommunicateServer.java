package zybank;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CommunicateServer {
	public String recvData(String args){
		ServerSocket serverSocket = null;
		
		try{
			serverSocket = new ServerSocket(80);
			Socket socket = serverSocket.accept();
			InputStream is = new DataInputStream(socket.getInputStream());
			byte[] datalen = new byte[4];
			is.read(datalen);
			int length = byteArrayToInt(datalen);
			byte[] data = new byte[length];
			is.read(data);
			String recvMsg = new String(data);//���������תΪ�ַ�������
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		InputStream is = new DataInputStream(socket.getInputStream());
//		int datalen = is.readInt();//��ȡǰ��λ�ֽڲ�����int��������
//		byte[] data = new byte[datalen];
//		is.readFully(data);
//		//ʹ�������ķ�ʽ��ȡ������datalen���ȵ�����
//		String recvMsg = new String(data);//���������תΪ�ַ�������
		return null;
	}
	
	public static int byteArrayToInt(byte[] b){
	    return b[3]&0xFF | (b[2]&0xFF) << 8 | (b[1]&0xFF) << 16 | (b[0]&0xFF) << 24; 
	}
}
