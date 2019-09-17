package com.simshine.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * MD5加密
 * @author zero
 *
 */
public class Md5Util {

	private static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Logger log = LoggerFactory.getLogger(Md5Util.class);
	/**
	 * 获取任意的随机字符串
	 */
	public static final String getRandomNum(int size){
		StringBuffer strbuf = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<size;i++){
			strbuf.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
		}
		return strbuf.toString();
	}

	/**
	 * md5加密（ITS）
	 * @param
	 */
	@SuppressWarnings("unused")
	public synchronized static final String getMd5Str(String str){

		MessageDigest messageDigest = null;
		String charset = "UTF-8";
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();

			/**
			 *  String.getByte("utf-8")和new String(byte[],"utf-8")各是什么意思？
			 *	1. 返回String解码为utf-8的字节序列；
			 *	2. 使用指定字符集解码的字节数组构造一个新的String。
			 */
			messageDigest.update(str.getBytes(charset));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			log.error("md5 error:"+e.getMessage(),e);
		}
		byte[] byteArray ;
		if (null==messageDigest){
			throw new RuntimeException("MessageDigest is null");
		} else {
			byteArray = messageDigest.digest();
		}
		StringBuffer md5Strbuf = new StringBuffer();

		for(int i=0;i<byteArray.length;i++){
			/**
			 * java代码中Integer.toHexString(b&0xff)
			 * 括号中为什么要写b&0xff
			 * 把整数转换成16进制字符串
			 */
			if(Integer.toHexString(0xFF&byteArray[i]).length()==1){
				md5Strbuf.append("0").append(Integer.toHexString(0xFF&byteArray[i]));
			}else{
				md5Strbuf.append(Integer.toHexString(0xFF&byteArray[i]));
			}
		}
		return md5Strbuf.toString();
	}


	/**
	 * 获取加密后的字符串
	 * @param
	 * @return
	 */
	public static String stringMd5(String pw) {
		try {

			// 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
			MessageDigest messageDigest =MessageDigest.getInstance("MD5");
			// 输入的字符串转换成字节数组
			byte[] inputByteArray=null;
			try {
				/* 没有加编码格式：byte[] inputByteArray = pw.getBytes();*/
				inputByteArray = pw.getBytes("UTF-8");
			}  catch (Exception e){
				e.printStackTrace();
			}
			if (null!=inputByteArray) {
				// inputByteArray是输入字符串转换得到的字节数组
				messageDigest.update(inputByteArray);
				// 转换并返回结果，也是字节数组，包含16个元素
				byte[] resultByteArray = messageDigest.digest();
				// 字符数组转换成字符串返回
				return byteArrayToHex(resultByteArray);
			}

		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return null;
	}

	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray =new char[byteArray.length * 2];
		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b& 0xf];
		}
		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}



	//测试结果
//	public static void main(String[] args) {
////		String str = getRandomNum(5);
////		StaticFile.LOGGER.info("\n"+str);
//		//汉字编码格式影响MD5密码生成结果
////		String pwd = "12345";
//		//String pwd = "ckey=57fa47e53172c3034aa7e017ac29edb4&client=C01&orderAmount=3.0&orderDetail=[{\"bar_code\":\"123456\",\"goods_id\":\"1\",\"goods_name\":\"百事可乐500ml\",\"quantity\":1,\"retail_price\":3.0}]&paymentChannel=zhifubao&storeId=1&time=2017-08-2423:24:22";
////		String md5 = getMD5Str("178u16531381WX_YY53022");
//		//String md5 = getMD5Str("17ap19322286WX_YY53022");
//		/*Constant.LOGGER.info("加密前\n"+pwd);
//		Constant.LOGGER.info("加密后\n"+md5)*/;
//		System.out.println(getMD5Str("d91bc5ef986c999f3fcbca8e2020bf86"));
//
//
//
//	}
	
	
}
