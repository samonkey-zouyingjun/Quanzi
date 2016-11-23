package com.fz.utils;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class StringUtils {

    private final static Pattern PATTERN_EMAIL = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
    private final static Pattern PATTERN_PHONE = Pattern
            .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
	
	
	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 * 
	 * @param origin
	 *            原始字符串
	 * @param begin
	 *            截取开始位置(一个汉字长度按2算的)
	 * @param end
	 *            截取结束位置(一个汉字长度按2算的)
	 * @param appendStr
	 *            待添加字符串
	 * @param encoding
	 *            编码格式,默认GBK
	 * @return 返回的字符串
	 */
	public static String cutString(String origin, int begin, int end,
			String appendStr, String encoding) {
		if (origin == null || origin.equals("")) {
			return appendStr;
		}
		if (begin < 0) {
			begin = 0;
		}
		if (end < 0) {
			return "";
		}
		if (begin > end) {
			return "";
		}
		if (begin == end) {
			return "";
		}
		if (begin > length(origin)) {
			return "";
		}
		if (end > length(origin)) {
			end = length(origin);
		}
		if (end < length(origin)) {
			end = end - 3;
		}
		if ("".equals(encoding)) {
			encoding = "GBK";
		}
		int len = end - begin;
		byte[] strByte = new byte[len];
		try {
			System.arraycopy(origin.getBytes(encoding), begin, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			if (length(origin) > end) {
				return new String(strByte, 0, len, encoding) + appendStr;
			} else {
				return new String(strByte, 0, len, encoding);
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (StringIndexOutOfBoundsException ex) {
			return appendStr;
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 * 
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null) {
			return 0;
		}
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			// 如果为汉，日，韩，则多加一位
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 * 
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * JsonStr字符串BOM头处理
	 * 
	 * @param data
	 * @return
	 */
	public static final String removeBOM(String data) {

		if (TextUtils.isEmpty(data)) {
			return data;
		}

		if (data.startsWith("ufeff")) {
			return data.substring(1);
		} else {
			return data;
		}
	}

	/**
	 * 提供精确的小数位四舍五入处理。 * 
	 * @param v  需要四舍五入的数字 
	 * @param scale  小数点后保留几位 
	 * @return
	 * 四舍五入后的结果
	 */
	public static double round(Double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b = null == v ? new BigDecimal("0.0") : new BigDecimal(
				Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	/**
	 * 将非法字符转化为转码后的字符 如&转为&amp;
	 * 
	 * @param des
	 * @return
	 */
	public static String transToXml(String des) {
		des = des.replace("&", "&amp;");
		des = des.replace("<", "&lt;");
		des = des.replace(">", "&gt;");
		des = des.replace("'", "&apos;");
		des = des.replace("\"", "&quot;");
		return des;
	}
	
	 /**
     * 判断给定字符串是否空白串 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(CharSequence input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     */
    public static boolean isEmail(CharSequence email) {
        if (isEmpty(email))
            return false;
        return PATTERN_EMAIL.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     */
    public static boolean isPhone(CharSequence phoneNum) {
        if (isEmpty(phoneNum))
            return false;
        return PATTERN_PHONE.matcher(phoneNum).matches();
    }

    /**
     * 字符串转整数
     * 
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 对象转整
     * 
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(Object obj) {
        if (obj == null)
            return 0;
        return toInt(obj.toString(), 0);
    }

    /**
     * String转long
     * 
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * String转double
     * 
     * @param obj
     * @return 转换异常返回 0
     */
    public static double toDouble(String obj) {
        try {
            return Double.parseDouble(obj);
        } catch (Exception e) {
        }
        return 0D;
    }

    /**
     * 字符串转布尔
     * 
     * @param b
     * @return 转换异常返回 false
     */
    public static boolean toBool(String b) {
        try {
            return Boolean.parseBoolean(b);
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 判断一个字符串是不是数字
     */
    public static boolean isNumber(CharSequence str) {
        try {
            Integer.parseInt(str.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * byte[]数组转换为16进制的字符串。
     * 
     * @param data
     *            要转换的字节数组。
     * @return 转换后的结果。
     */
    public static final String byteArrayToHexString(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        for (byte b : data) {
            int v = b & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.getDefault());
    }

    /**
     * 16进制表示的字符串转换为字节数组。
     * 
     * @param s
     *            16进制表示的字符串
     * @return byte[] 字节数组
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] d = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
            d[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
                    .digit(s.charAt(i + 1), 16));
        }
        return d;
    }
}
