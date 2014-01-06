package com.sps.ps.utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public final class Encryptor {
	
	/**
	 * MD5加密算法
	 */
	public final static String ENCR_MD5 = "MD5";
	/**
	 * SHA-1 加密算法
	 */
	public final static String ENCR_SHA1 = "SHA-1";
	
	

	/**
	 * 使用指定的key用DES算法加密数据，
	 * @param src 要加密的内容的字节码
	 * @param key 密钥
	 * @return 加密后的字节码
	 * @throws Exception 加密出错时
	 */
	public static byte[] DESEncrypt(byte[] src, SecretKey key) throws Exception {
		Cipher c = Cipher.getInstance("DES");
		c.init(Cipher.ENCRYPT_MODE, key);
		return(c.doFinal(src));
	}

	/**
	 * 使用指定的key解密用DES算法加密的数据
	 * @param src 加密的字节码
	 * @param key 密钥
	 * @return 解密的后的字节码
	 * @throws Exception
	 */
	public static byte[] DESDecrypt(byte[] src, SecretKey key) throws Exception {
		Cipher c = Cipher.getInstance("DES");
		c.init(Cipher.DECRYPT_MODE, key);
		return(c.doFinal(src));
	}

	/**
	 * 使用MD5算法生成消息摘要，
	 * @param src 要加密的内容的字节码
	 * @return 返回加密后的字节数组
	 * @throws Exception
	 */
	public static byte[] MD5(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("MD5");
		alg.update(src);
		return alg.digest();
	}

	/**
	 * 使用MD5算法生成消息摘要，
	 * @param src 要加密的字节码
	 * @return 返回加密后的16进制字符串
	 * @throws Exception
	 */
	public static String MD5String(byte[] src) throws Exception {
		return byte2hex(MD5(src));
	}
	
	/**
	 * 使用MD5算法生成消息摘要，
	 * @param src 要加密的字符串
	 * @return 返回加密后的16进制字符串
	 * @throws Exception
	 */
	public static String MD5String(String src) throws Exception {
		return byte2hex(MD5(src.getBytes()));
	}	

	/**
	 * 使用SHA-1算法生成消息摘要，
	 * @param src 要加密的字节码
	 * @return 返回加密后的字节数组
	 * @throws Exception
	 */
	public static byte[] SHA1(byte[] src) throws Exception {
		MessageDigest alg = MessageDigest.getInstance("SHA-1");
		alg.update(src);
		return alg.digest();
	}

	/**
	 * 使用SHA1算法生成消息摘要，
	 * @param src 要加密的字节码
	 * @return 返回加密后的16进制字符串
	 * @throws Exception 
	 */
	public static String SHA1String(byte[] src) throws Exception {
		return byte2hex(SHA1(src));
	}
	
	/**
	 * 使用SHA1算法生成消息摘要，
	 * @param src 要加密的字符串
	 * @return 返回加密后的16进制字符串
	 * @throws Exception 
	 */
	public static String SHA1String(String src) throws Exception {
		return byte2hex(SHA1(src.getBytes()));
	}	

	/**
	 * 判断两个摘要是否相同，实际上就是判断a、b两个字节数组是否相同
	 * @param a 第一个摘要
	 * @param b 第二个摘要
	 * @return
	 */
	public static boolean isEqualDigest(byte[] a, byte[] b) {
		return MessageDigest.isEqual(a, b);
	}
	
	/**
	 * 判断是源内容是否正确,用于判定MD5 或 SHA1 加密的内容
	 * @param src 源内容,未加密的
	 * @param key 加密后的内容
	 * @param type 加密的算法 :支持两种<br>
	 * 			{@link #ENCR_MD5}(不包括""), md5加密<br>
	 * 			{@link #ENCR_SHA1}(不包括""), SHA-1加密
	 * @return 如果三个参数任一参数为空,都返回false,如果不正确,返回false,否则返回true
	 */
	public static boolean isEqualDigest(String src, String key,String type) {
		if(type== null || src == null || key == null) {
			return false;
		}
		String tmkey = null;
		try {
			if(ENCR_MD5 == type) {
				tmkey = MD5String(src);
			} else if (ENCR_SHA1 == type) {
				tmkey = SHA1String(src);
			}
		} catch(Exception e) {
			return false;
		}
		return key.equals(tmkey);
	}	

	/**
	 * 创建一个指定长度的密钥
	 * @param algorithm 加密算法 "DES"
	 * @param keysize  密钥的长度
	 * @return 返回密钥对象
	 * @throws Exception
	 */
	public static SecretKey generateKey(String algorithm, int keysize) throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		kg.init(keysize);
		return(kg.generateKey());
	}

	/**
	 * 创建一个密钥,用于加密
	 * @param algorithm 加密算法 "DES"
	 * @return  返回密钥对象
	 * @throws Exception
	 */
	public static SecretKey generateKey(String algorithm) throws Exception{
		KeyGenerator kg = KeyGenerator.getInstance(algorithm);
		return(kg.generateKey());
	}
	
	/**
	 * 从密钥字节数组中恢复一个密钥对象,用于解密
	 * @param key 密钥字节数组
	 * @param algorithm 加密算法 "DES"
	 * @return 返回密钥对象
	 * @throws Exception
	 */
	public static SecretKey restoreKey(byte[] key, String algorithm) throws Exception{
		SecretKeySpec ks=new SecretKeySpec(key, algorithm);
		return(ks);
	}

	/**
	 * 二进制转字符串
	 * @param b 要转换的二进制数组
	 * @return 返回一个由16进制数字组合而成的字符串
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}
	
	/**
	 * Base64编码
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] encodeBase64(byte[] b) {
		return Base64.encodeBase64(b);
	}
	
	/**
	 * 解码Base64
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] decodeBase64(byte[] b) {
		return Base64.decodeBase64(b);
	}
	
	/**
	 * Base64编码
	 * 
	 * @param str 要编码的字串
	 * @return 如果str为null, 则返回 null
	 */
	public static String encodeBase64(String str) {
		if (str == null) {
			return null;
		}
		return new String(encodeBase64(str.getBytes()));
	}

	/**
	 * 解码Base64
	 * 
	 * @param str 要解码的base64编码字符串
	 * @return 如果str为null,则返回null
	 */
	public static String decodeBase64(String str) {
		if (str == null) {
			return null;
		}		
		return new String(decodeBase64(str.getBytes()));
	}
}
