package com.cloud.jarbase.util;

import org.apache.commons.text.RandomStringGenerator;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Random;
import java.util.regex.Pattern;


public class StringUtils {
//	public static final Gson gson = new Gson();
	public static final String AMOUNT1 = "^[1-9]{1}[0-9]{0,9}(\\.(\\d){1,2})?$|^[0]{1}(\\.(\\d){1,2})?$|(^$)";

	private static final RandomStringGenerator generator = new RandomStringGenerator.Builder()
			.withinRange('0', '}').build();
	
	private static final RandomStringGenerator generator2 = new RandomStringGenerator.Builder()
	.withinRange('0', '9').build();	

	public static RandomStringGenerator getGenerator() {
		return generator;
	}
	
	public static RandomStringGenerator getGeneratorNumber() {
		return generator2;
	}

	  /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

	public static boolean isEmpty(String info) {
		if (info == null || "".equals(info))
			return true;
		return false;
	}

	/**
	 * 检查是否全是全角
	 * 
	 * @param info
	 * @return
	 */
	public static boolean checkAllFull(String info) throws Exception {
		if (isEmpty(info))
			throw new Exception("输入项不能为空");
		if (info.getBytes().length == info.length() * 2) {
			return true;
		}
		return false;
	}

	/**
	 * 检查是否全是半角
	 * 
	 * @param info
	 * @return
	 */
	public static boolean checkAllHalf(String info) throws Exception {
		if (isEmpty(info))
			throw new Exception("输入项不能为空");
		if (info.getBytes().length == info.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 混合全角半角
	 * 
	 * @param info
	 * @return
	 */
	public static boolean checkFullAndHalf(String info) throws Exception {
		if (isEmpty(info))
			throw new Exception("输入项不能为空");
		if (info.getBytes().length > info.length()
				&& info.getBytes().length != info.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 如果字串有中文或其他，获取字节数
	 * 
	 * @param s
	 * @return
	 */
	public static int getlength(String s) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			boolean b = (s.charAt(i) + "").matches("[\u4e00-\u9faf]");
			if (b) {
				count = count + 3;
			} else {
				count++;
			}
		}
		return count;

	}

	/**
	 * 金额校验
	 * 
	 * @param beginAmount
	 * @param endAmount
	 * @throws Exception
	 */
	public static void regexAmount(String beginAmount, String endAmount)
			throws Exception {
		String amountreg = AMOUNT1;
		Pattern amountP = Pattern.compile(amountreg);
		String error = "金额为最多10位正整数和2位小数的数字";
		if (!"".equals(beginAmount)) {
			beginAmount = beginAmount.replaceAll(",", "");
			if (!amountP.matcher(beginAmount).matches()) {
				throw new Exception(error);
			}
		}
		if (!"".equals(endAmount)) {
			endAmount = endAmount.replaceAll(",", "");
			if (!amountP.matcher(endAmount).matches()) {
				throw new Exception(error);
			}
		}
		if (!"".equals(beginAmount) && !"".equals(endAmount)) {
			BigDecimal beginA = new BigDecimal(beginAmount);
			if (new BigDecimal(endAmount).compareTo(beginA) < 0) {
				// throw new Exception("终止金额不能小于起始金额");
				throw new Exception("FYMADM04");
			}
		}
	}

	public static void regexAmount(String beginAmount, String endAmount,
			String msg) throws Exception {
		String amountreg = AMOUNT1;
		Pattern amountP = Pattern.compile(amountreg);
		String error = "金额为最多10位正整数和2位小数的数字";
		if (!"".equals(beginAmount)) {
			beginAmount = beginAmount.replaceAll(",", "");
			if (!amountP.matcher(beginAmount).matches()) {
				throw new Exception(error);
			}
		}
		if (!"".equals(endAmount)) {
			endAmount = endAmount.replaceAll(",", "");
			if (!amountP.matcher(endAmount).matches()) {
				throw new Exception(error);
			}
		}
		if (!"".equals(beginAmount) && !"".equals(endAmount)) {
			BigDecimal beginA = new BigDecimal(beginAmount);
			if (new BigDecimal(endAmount).compareTo(beginA) < 0) {
				throw new Exception(msg);
			}
		}
	}

	/**
	 * byte数组转化为16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			String strHex = Integer.toHexString(bytes[i]);
			if (strHex.length() > 3) {
				sb.append(strHex.substring(6));
			} else {
				if (strHex.length() < 2) {
					sb.append("0" + strHex);
				} else {
					sb.append(strHex);
				}
			}
		}
		return sb.toString();
	}


	/**
	 * 当字符串长度超过len，截断，后面补...
	 * 
	 * @param str
	 * @param len
	 * @return
	 */
	public final static String truncate(String str, int len) {
		if (str.length() > len) {
			return str.substring(0, len) + "...";
		} else {
			return str;
		}
	}

	public final static String qianfen(int num) {
		NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
		return numberFormat1.format(num);
	}
	
	public final static String qianfen2(BigDecimal num) {
		NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
		return numberFormat1.format(num);
	}

	/**
	 * 判断字符串是否为URL
	 * 
	 * @param urls
	 *            用户头像key
	 * @return true:是URL、false:不是URL
	 */
	public static boolean isHttpUrl(String urls) {
		// localhost:8080/callback没法验证，所以暂时注释掉
		// boolean isurl = false;
		// String regex = "(((https|http)?://)?([a-z0-9]+[.])|(www.))"
		// +
		// "\\w+[.|\\/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)";//设置正则表达式
		//
		// Pattern pat = Pattern.compile(regex.trim());//比对
		// Matcher mat = pat.matcher(urls.trim());
		// isurl = mat.matches();//判断是否匹配
		// if (isurl) {
		// isurl = true;
		// }

		return true;
	}
	
	public static String trim(String strValue) {
        String strResult = "";

        for (int i = 0; i < strValue.length(); i++) {
            char charTemp = strValue.charAt(i);
            if (charTemp != ' ' && charTemp != '　' && charTemp != '\t') {
                strResult = strValue.substring(i);
                break;
            }
        }

        for (int i = strResult.length() - 1; i >= 0; i--) {
            char charTemp = strResult.charAt(i);
            if (charTemp != ' ' && charTemp != '　' && charTemp != '\t') {
                strResult = strResult.substring(0, i + 1);
                break;
            }
        }

        return strResult;
    } 
	
	    
	
}
