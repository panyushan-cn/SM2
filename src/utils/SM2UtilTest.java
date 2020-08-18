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
			Reader reader=new FileReader("E:/Desktop/新建文件夹/in.txt");
			int ch=reader.read();
			while(ch!=-1){ //读取成功
				buffer.append((char)ch);
				ch=reader.read();
			}
			System.out.println(buffer.toString());
			//3、关闭流
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("要读取的文件不存在："+e.getMessage());
		} catch (IOException e) {
			System.out.println("文件读取错误："+e.getMessage());
		}
		String M=buffer.toString();
		System.out.println("待加密:M="+M);
		SM2Util sm2 = new SM2Util();
		SM2KeyPair keyPair = sm2.generateKeyPair();


		ECPoint publicKey = keyPair.getPublicKey();
		BigInteger privateKey = keyPair.getPrivateKey();

		String publictext= publicKey.toString();
		String privatetext=privateKey.toString();

		try {
				//1、打开流
				Writer w=new FileWriter("E:/Desktop/新建文件夹/publicKey.txt",true);
				//2、写入内容
				w.write(publictext);
				//3、关闭流
				w.close();
			} catch (IOException e) {
				System.out.println("文件写入错误："+e.getMessage());
			}
		try {
			//1、打开流
			Writer w=new FileWriter("E:/Desktop/新建文件夹/privateKey.txt",true);
			//2、写入内容
			w.write(privatetext);
			//3、关闭流
			w.close();
		} catch (IOException e) {
			System.out.println("文件写入错误："+e.getMessage());
		}

		byte[] data = sm2.encrypt(M,keyPair.getPublicKey());

		String encrypttext=Arrays.toString(data);
		try {
			//1、打开流
			Writer w=new FileWriter("E:/Desktop/新建文件夹/encrypt.txt",true);
			//2、写入内容
			w.write(encrypttext);
			//3、关闭流
			w.close();
		} catch (IOException e) {
			System.out.println("文件写入错误："+e.getMessage());
		}



		System.out.println("data is:"+Arrays.toString(data));
		
		System.out.println("keyPair.getPublicKey() is:"+keyPair.getPublicKey());
		System.out.println("keyPair.getPrivateKey() is:"+keyPair.getPrivateKey());
		
		sm2.decrypt(data, keyPair.getPrivateKey());

		}
	
	}

