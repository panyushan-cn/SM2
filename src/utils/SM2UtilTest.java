package utils;


import java.io.*;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;




public class SM2UtilTest {

	public static void main(String[] args) {
		StringBuffer buffer=new StringBuffer();
		try {
			Reader reader=new FileReader("E:/Desktop/�½��ļ���/in.txt");
			int ch=reader.read();
			while(ch!=-1){ //��ȡ�ɹ�
				buffer.append((char)ch);
				ch=reader.read();
			}
			System.out.println(buffer.toString());
			//3���ر���
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ҫ��ȡ���ļ������ڣ�"+e.getMessage());
		} catch (IOException e) {
			System.out.println("�ļ���ȡ����"+e.getMessage());
		}
		String M=buffer.toString();
		System.out.println("������:M="+M);
		SM2Util sm2 = new SM2Util();
		SM2KeyPair keyPair = sm2.generateKeyPair();


		ECPoint publicKey = keyPair.getPublicKey();
		BigInteger privateKey = keyPair.getPrivateKey();

		String publictext= publicKey.toString();
		String privatetext=privateKey.toString();

		try {
				//1������
				Writer w=new FileWriter("E:/Desktop/�½��ļ���/publicKey.txt",true);
				//2��д������
				w.write(publictext);
				//3���ر���
				w.close();
			} catch (IOException e) {
				System.out.println("�ļ�д�����"+e.getMessage());
			}
		try {
			//1������
			Writer w=new FileWriter("E:/Desktop/�½��ļ���/privateKey.txt",true);
			//2��д������
			w.write(privatetext);
			//3���ر���
			w.close();
		} catch (IOException e) {
			System.out.println("�ļ�д�����"+e.getMessage());
		}

		byte[] data = sm2.encrypt(M,keyPair.getPublicKey());

		String encrypttext=Arrays.toString(data);
		try {
			//1������
			Writer w=new FileWriter("E:/Desktop/�½��ļ���/encrypt.txt",true);
			//2��д������
			w.write(encrypttext);
			//3���ر���
			w.close();
		} catch (IOException e) {
			System.out.println("�ļ�д�����"+e.getMessage());
		}



		System.out.println("data is:"+Arrays.toString(data));
		
		System.out.println("keyPair.getPublicKey() is:"+keyPair.getPublicKey());
		System.out.println("keyPair.getPrivateKey() is:"+keyPair.getPrivateKey());
		
		sm2.decrypt(data, keyPair.getPrivateKey());

		}
	}
